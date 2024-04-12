package com.github.face.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import cn.afterturn.easypoi.handler.inter.IExcelExportServer;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.StringUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 文件导出
 *
 * @author sxl
 */
public class ExportUtil {

    public static final String REDIS_EXPORT_KEY = "police:export:look";

    public static final Integer EXPORT_MAX_SIZE = 5000;

    /**
     * 模板下载
     *
     * @param response
     * @param map       数据
     * @param templates 模板位置
     * @param fileName  文件名称
     * @throws Exception
     */
    public static void downloadExcel(HttpServletResponse response, Map<String, Object> map, String templates,
                                     String fileName, Boolean scanAllsheet) throws Exception {
        makeResponse(response, fileName);
        TemplateExportParams params = new TemplateExportParams(templates, scanAllsheet);
        params.setColForEach(true);
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        ServletOutputStream output = response.getOutputStream();
        BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
        workbook.write(bufferedOutPut);
        bufferedOutPut.flush();
        bufferedOutPut.close();
        output.close();
    }

    /**
     * 模板下载(.xlsx格式)
     *
     * @param response
     * @param map       数据
     * @param templates 模板位置
     * @param fileName  文件名称
     * @throws Exception
     */
    public static void downloadExcelXlsx(HttpServletResponse response, Map<String, Object> map, String templates,
                                         String fileName, Boolean scanAllsheet) throws Exception {
        makeResponse(response, fileName);

        // 模板导出参数设置
        TemplateExportParams params = new TemplateExportParams(templates, scanAllsheet);
        params.setColForEach(true);

        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        ServletOutputStream output = response.getOutputStream();
        BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
        workbook.write(bufferedOutPut);
        bufferedOutPut.flush();
        bufferedOutPut.close();
        output.close();
    }

    /**
     * 对象导出
     *
     * @param response
     * @param list
     * @param fileName
     * @param title
     * @param sheetName
     * @throws Exception
     */
    public static void downloadExcelEntity(HttpServletResponse response, List<?> list, Class<?> pojoClass,
                                           String fileName, String title, String sheetName) throws Exception {
        makeResponse(response, fileName);
        ExportParams exportParams;
        if (StringUtils.hasText(title)) {
            exportParams = new ExportParams(title, sheetName);
        } else {
            exportParams = new ExportParams();
            exportParams.setSheetName(sheetName);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        ServletOutputStream output = response.getOutputStream();
        BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
        workbook.write(bufferedOutPut);
        bufferedOutPut.flush();
        bufferedOutPut.close();
        output.close();
    }

    /**
     * 大数据量对象导出
     *
     * @param response
     * @param list
     * @param fileName
     * @param title
     * @param sheetName
     * @throws Exception
     */
    public static void downloadBigExcelEntity(HttpServletResponse response, List<?> list, Class<?> pojoClass,
                                              String fileName, String title, String sheetName) throws Exception {
        makeResponse(response, fileName);
        ExportParams exportParams;
        if (StringUtils.hasText(title)) {
            exportParams = new ExportParams(title, sheetName);
        } else {
            exportParams = new ExportParams();
            exportParams.setSheetName(sheetName);
        }
        int talpage = (list.size() / 10000) + 1;
        Workbook workbook = ExcelExportUtil.exportBigExcel(exportParams, pojoClass, new IExcelExportServer() {
            @Override
            //obj 总页数 page 第几页
            public List<Object> selectListForExcelExport(Object obj, int page) {
                System.out.println("第几页：" + page);
                //page每次加一，当等于obj的值时返回空，代码结束；
                if (page > ((int) obj)) {
                    return null;
                }
                int start = -1;
                if (page == 1) {
                    start = 0;
                } else {
                    start = (page - 1) * 10000;
                }
                List<Object> list1 = new ArrayList<Object>();
                for (int i = start; i < (start + 10000); i++) {
                    if (i < list.size()) {
                        list1.add(list.get(i));
                    } else {
                        break;
                    }
                }
                System.out.println("导出条数：" + list1.size());
                return list1;
            }
        }, talpage);
        ServletOutputStream output = response.getOutputStream();
        BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
        workbook.write(bufferedOutPut);
        bufferedOutPut.flush();
        bufferedOutPut.close();
        output.close();
    }

    /**
     * 添加请求头
     *
     * @param response
     * @param fileName
     * @throws Exception
     */
    public static void makeResponse(HttpServletResponse response, String fileName) {
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", StandardCharsets.UTF_8));
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
    }

    public static void downloadExcelEntityNew(HttpServletResponse response, List<?> list, Class<?> pojoClass,
                                              String fileName, String title, String sheetName) throws Exception {
        makeResponse(response, fileName);
        ExportParams exportParams;
        if (StringUtils.hasText(title)) {
            exportParams = new ExportParams(title, sheetName);
        } else {
            exportParams = new ExportParams();
            exportParams.setSheetName(sheetName);
        }
        Workbook workbook = ExcelExportUtil.exportExcel(exportParams, pojoClass, list);
        ServletOutputStream output = response.getOutputStream();
        BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
        workbook.write(bufferedOutPut);
        bufferedOutPut.flush();
        bufferedOutPut.close();
        output.close();
    }

}
