package com.github.face.demo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import com.github.face.utils.ExcelPoiUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 导出测试类
 */
public class ExportTest {




    /**
     * 动态导出demo
     */
    @Data
    static class Demo implements Serializable {

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
        private List<ExcelPoiUtils.Demo.Type> types;

        /**
         * 评价类型及分数对象
         */
        @Data
        static class Type implements Serializable {

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
