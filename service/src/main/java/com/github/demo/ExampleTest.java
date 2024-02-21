//package com.wangcl.demo;
//
//import com.wangcl.demo.entiy.TestDO;
//import com.wangcl.utils.ExampleKit;
//import tk.mybatis.mapper.entity.Example;
//
///**
// * @author wcl
// */
//public class ExampleTest {
//
//    public static void main(String[] args) {
//        Example example = new Example(TestDO.class);
//        example.and()
//                .andEqualTo("id", 1);
//        example.orderBy("name").desc();
//
//        Example example1 = ExampleKit.copyExample(example, TestDO.class);
//        String orderByClause = example1.getOrderByClause();
//        System.out.println(orderByClause);
//    }
//}
