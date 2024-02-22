package com.github.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wcl
 */
public class StreamTest {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.removeIf(s -> "1".equals(s));
        System.out.println(list);
    }
}
