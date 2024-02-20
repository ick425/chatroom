package com.wangcl.utils;

import lombok.extern.slf4j.Slf4j;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author wcl
 */

@Slf4j
public class ExampleKit {
    /**
     * 复制example查询条件
     *
     * @param source 源example
     * @param dClass class类
     * @return example
     */
    public static Example copyExample(Example source, Class<?> dClass) {
        Class<?> entityClass = source.getEntityClass();
        System.out.println("entityClass:{}" + entityClass.getName());
        Example example = new Example(dClass);
        List<Example.Criteria> oredCriteriaList = source.getOredCriteria();
        for (Example.Criteria oredCriterion : oredCriteriaList) {
            example.and(oredCriterion);
        }
        return example;
    }
}
