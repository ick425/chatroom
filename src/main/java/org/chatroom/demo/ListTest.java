package org.chatroom.demo;

import java.util.ArrayList;

/**
 * final修饰的对象不可改变，final修饰的map、list，内容可以改变，引用地址不可变
 *
 * @author wo
 */
public class ListTest {
    public static void main(String[] args) {
        final ArrayList<String> list = new ArrayList<>(10);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        // list=new ArrayList<>();
        System.out.println(list);
    }
}
