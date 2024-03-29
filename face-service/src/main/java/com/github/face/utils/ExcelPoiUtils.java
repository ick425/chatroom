package com.github.face.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.hutool.core.annotation.AnnotationUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 使用easyPoi的动态列导出
 * <br/>固定列使用@Excel标识，动态列使用@ExcelCollection标识的List集合，手动传入集合内作为动态表头名称和值的字段名
 *
 * @author wangcl
 */
@Slf4j
public class ExcelPoiUtils {

    /**
     * 使用注解的动态列导出，实体类必须按照规定格式
     * <br/>通过反射实现，注意方法效率
     *
     * @param response    响应
     * @param fileName    文件名
     * @param title       标题
     * @param sheetName   sheet名称
     * @param dataList    导出数据
     * @param headerName  动态列标题
     * @param headerValue 动态列值
     */
    public static <T> void dynamicExport(HttpServletResponse response, String fileName, String title, String sheetName,
                                         List<T> dataList, String headerName, String headerValue) throws Exception {
        Assert.notEmpty(dataList, "没有需要导出的数据");
        List<ExcelExportEntity> entityList = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        for (T t : dataList) {
            // Step1：处理标题
            Field[] fields = t.getClass().getDeclaredFields();
            int index = 0;
            Map<String, Object> map = new HashMap<>();
            for (Field field : fields) {
                field.setAccessible(true);
                Excel excel = field.getAnnotation(Excel.class);
                ExcelCollection excelCollection = field.getAnnotation(ExcelCollection.class);
                // 固定导出列
                if (excel != null) {
                    Object name = AnnotationUtil.getAnnotationValue(field, Excel.class, "name");
                    // 转换注解修饰的对象
                    ExcelExportEntity entity = convert(field, name.toString(), field.getName(), index);
                    index++;
                    entityList.add(entity);
                }
                // 自定义导出列,含有@ExcelCollection并且是List
                else if (excelCollection != null && field.getType().getName().equals(List.class.getName())) {
                    Object object;
                    object = field.get(t);
                    List<?> dynamicColl = (List<?>) object;
                    for (Object arr : dynamicColl) {
                        String key = null;
                        String val = null;
                        Field[] typeFields = arr.getClass().getDeclaredFields();
                        for (Field typeField : typeFields) {
                            typeField.setAccessible(true);
                            String fieldName = typeField.getName();
                            Excel excelItem = typeField.getAnnotation(Excel.class);
                            if (excelItem != null) {
                                if (headerName.equals(fieldName)) {
                                    Object value;
                                    try {
                                        value = typeField.get(arr);
                                        key = value.toString();
                                    } catch (IllegalAccessException e) {
                                        throw new RuntimeException(e);
                                    }
                                    // 转换注解修饰的对象
                                    ExcelExportEntity entity = convert(typeField, value.toString(), value.toString(), index);
                                    index++;
                                    entityList.add(entity);
                                } else if (headerValue.equals(fieldName)) {
                                    Object value;
                                    try {
                                        value = typeField.get(arr);
                                        val = value.toString();
                                    } catch (IllegalAccessException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                        }
                        map.put(key, val);
                    }
                }
            }
            //  Step2：处理数据，将动态列添加到实体类
            Object object = ReflectKit.getObject(t, map);
            list.add(object);
        }
        log.info(JSONObject.toJSONString(list));
        entityList = entityList.stream().filter(distinctByKey(ExcelExportEntity::getName)).collect(Collectors.toList());
        downloadExcelEntityDynamic(response, entityList, list, fileName, title, sheetName);
    }

    /**
     * 根据指定字段去重
     */
    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    /**
     * 动态表头导出
     *
     * @param response   响应
     * @param entityList 表头列表
     * @param list       导出数据
     * @param fileName   文件名
     * @param title      标题
     * @param sheetName  sheet名称
     */
    public static void downloadExcelEntityDynamic(HttpServletResponse response, List<ExcelExportEntity> entityList,
                                                  Collection<?> list, String fileName, String title,
                                                  String sheetName) throws Exception {
        makeResponse(response, fileName);
        ExportParams exportParams;
        if (StringUtils.hasText(title)) {
            exportParams = new ExportParams(title, sheetName);
        } else {
            exportParams = new ExportParams();
            exportParams.setSheetName(sheetName);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, entityList, list);
        ServletOutputStream output = response.getOutputStream();
        BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
        workbook.write(bufferedOutPut);
        bufferedOutPut.flush();
        bufferedOutPut.close();
        output.close();
    }

    public static void makeResponse(HttpServletResponse response, String fileName) {
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", StandardCharsets.UTF_8));
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
    }

    /**
     * 将@Excel修饰的字段转为ExcelExportEntity
     */
    private static ExcelExportEntity convert(Field typeField, String name, String key, int index) {
        Map<String, Object> annotationValueMap = AnnotationUtil.getAnnotationValueMap(typeField, Excel.class);
        ExcelExportEntity entity = JSONObject.parseObject(JSONObject.toJSONBytes(annotationValueMap), ExcelExportEntity.class);
        // 字段名和@Excel的name一致，视为动态表头列
        entity.setName(name);
        entity.setKey(key);
        entity.setOrderNum(index);
        return entity;
    }

    /**
     * 动态导出demo
     */
    @Data
    public static class Demo implements Serializable {

        /**
         * 部门id
         */
        private Long departId;

        /**
         * 部门名称
         */
        @Excel(name = "部门", width = 20)
        private String departName;

        /**
         * 处理事件数
         */
        @Excel(name = "处理事件数", width = 20)
        private Integer dealCount = 0;

        /**
         * 全部评价数
         */
        @Excel(name = "全部评价数", width = 20)
        private Integer totalEvaCount = 0;

        /**
         * 有效评价数
         */
        @Excel(name = "有效评价数", width = 20)
        private Integer validEvaCount = 0;

        /**
         * 解决率
         */
        @Excel(name = "解决率", width = 20)
        private String resolutionRate;

        /**
         * 评价类型及分数
         */
        @ExcelCollection(name = "部门")
        private List<Type> types;

        /**
         * 评价类型及分数对象
         */
        @Data
        public static class Type implements Serializable {

            /**
             * 评价类型id
             */
            private Long typeId;

            /**
             * 评价类型名称（动态标题名称,此处 name = "typeName"可以随便填，以方法调用时传入的为准）
             */
            @Excel(name = "typeName", width = 20)
            private String typeName;

            /**
             * 评价类型对应分数（动态标题内容,此处 name = "score"可以随便填，以方法调用时传入的为准）
             */
            @Excel(name = "score", width = 20)
            private String score = "0.00";
        }
    }

}
