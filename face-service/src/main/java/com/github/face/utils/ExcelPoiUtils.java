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
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;
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

        StopWatch stopWatch = new StopWatch("自定义列导出");

        stopWatch.start("处理导出字段，使用cglib构建动态属性");
        List<ExcelExportEntity> entityList = new ArrayList<>();
        // 自定义表头列
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            T t = dataList.get(i);
            // Step1：处理标题
            Field[] fields = t.getClass().getDeclaredFields();

            Map<String, Object> map = new HashMap<>();
            for (Field field : fields) {
                field.setAccessible(true);
                Excel excel = field.getAnnotation(Excel.class);
                ExcelCollection excelCollection = field.getAnnotation(ExcelCollection.class);
                // 固定导出列
                if (excel != null) {
                    String excelName = AnnotationUtil.getAnnotationValue(field, Excel.class, "name");
                    // 转换注解修饰的对象
                    if (i == 0) {
                        ExcelExportEntity entity = convert(field, excelName, field.getName(), false, 0);
                        entityList.add(entity);
                    }
                }
                // 自定义导出列,含有@ExcelCollection并且是List
                else if (excelCollection != null && field.getType().getName().equals(List.class.getName())) {
                    Object object;
                    object = field.get(t);
                    List<?> dynamicColl = (List<?>) object;
                    if (CollectionUtils.isEmpty(dynamicColl)) {
                        continue;
                    }
                    // 唯一key，用于设置cglib的属性名和ExcelExportEntity的key,也可以使用别的唯一id（uuid，雪花id等）来代替
                    int indexKey = 0;
                    for (Object arr : dynamicColl) {
                        String key;
                        String val = null;
                        Field[] typeFields = arr.getClass().getDeclaredFields();
                        for (Field typeField : typeFields) {
                            typeField.setAccessible(true);
                            String fieldName = typeField.getName();
                            Excel excelItem = typeField.getAnnotation(Excel.class);
                            // 只处理@ExcelCollection修饰的List下的@Excel修饰字段，即自定义字段
                            if (excelItem != null) {
                                Object value;
                                if (!Arrays.asList(headerName, headerValue).contains(fieldName)) {
                                    continue;
                                }
                                // 表头字段
                                if (headerName.equals(fieldName)) {
                                    try {
                                        value = typeField.get(arr);
                                        if (value != null) {
                                            key = value.toString();
                                        } else {
                                            continue;
                                        }
                                    } catch (IllegalAccessException e) {
                                        throw new RuntimeException(e);
                                    }
                                    if (i == 0) {
                                        // 转换注解修饰的对象
                                        ExcelExportEntity entity = convert(typeField, key, key, true, indexKey);
                                        entityList.add(entity);
                                    }
                                }
                                // 表头对应的值字段
                                else if (headerValue.equals(fieldName)) {
                                    try {
                                        value = typeField.get(arr);
                                        if (value != null) {
                                            val = value.toString();
                                        }
                                    } catch (IllegalAccessException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                        }
                        map.put(String.valueOf(indexKey), val);
                        indexKey++;
                    }
                }
            }
            //  Step2：处理数据，如果有动态列的话，添加到实体类
            if (MapUtils.isNotEmpty(map)) {
                Object object = ReflectKit.getObject(t, map);
                list.add(object);
            } else {
                list.add(t);
            }
        }
        stopWatch.stop();
        log.debug("构建字段：{}", JSONObject.toJSONString(list));

        stopWatch.start("导出");
        //entityList = entityList.stream().filter(distinctByKey(ExcelExportEntity::getName)).collect(Collectors.toList());
        downloadExcelEntityDynamic(response, entityList, list, fileName, title, sheetName);
        stopWatch.stop();
        log.debug(stopWatch.prettyPrint());
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
     *
     * @param typeField 字段
     * @param name      列名
     * @param key       唯一表示key
     * @param dynamic   是否自定义导出字段
     * @param index     自定义字段的唯一key,dynamic=true才会使用
     * @return ExcelExportEntity
     */
    private static ExcelExportEntity convert(Field typeField, String name, String key, boolean dynamic, int index) {
        Map<String, Object> annotationValueMap = AnnotationUtil.getAnnotationValueMap(typeField, Excel.class);
        ExcelExportEntity entity = JSONObject.parseObject(JSONObject.toJSONBytes(annotationValueMap), ExcelExportEntity.class);
        // 字段名和@Excel的name一致，视为动态表头列
        entity.setName(name);
        // ！！！如果使用name作为key，而name中恰好含有英文的“ (,;”等特殊字符，cglib构建动态属性会报错，所以使用一个自定义的唯一值作为key
        if (dynamic) {
            entity.setKey(String.valueOf(index));
        } else {
            entity.setKey(key);
        }
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
