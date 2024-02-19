package org.example.test;

import java.util.*;

/**
 * hashCode
 *
 * @author wo
 */
public class HashCodeTest {

    public static void main(String[] args) {

        List<String> list1 = new ArrayList<>();

        List<String> list2 = new ArrayList<>();

        list1.add("apple");
        list1.add("banana");
        list1.add("orange");

        list2.add("apple");
        list2.add("banana");
        list2.add("orange");
        System.out.println("list1 hashCode: " + list1.hashCode());
        System.out.println("list2 hashCode: " + list2.hashCode());
        System.out.println("list1 equals list2: "+list1.equals(list2));

        List<String> list3 = new ArrayList<>();
        System.out.println("list1: "+list1);
        System.out.println("list1: "+ System.identityHashCode(list1));
    }
}
