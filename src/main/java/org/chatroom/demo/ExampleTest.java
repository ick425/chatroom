package org.chatroom.demo;

import org.chatroom.entiy.TestDO;
import org.chatroom.utils.ExampleKit;
import tk.mybatis.mapper.entity.Example;

/**
 * @author wcl
 */
public class ExampleTest {

    public static void main(String[] args) {
        Example example = new Example(TestDO.class);
        example.and()
                .andEqualTo("id", 1);
        example.orderBy("name").desc();

        Example example1 = ExampleKit.copyExample(example, TestDO.class);
        String orderByClause = example1.getOrderByClause();
        System.out.println(orderByClause);
    }
}
