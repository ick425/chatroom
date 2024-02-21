package com.github.demo;

import java.time.LocalDateTime;

public class WorkDay {

    public static void main(String[] args) {
        compareDate();

    }


    private static void compareDate() {
        // 最迟签收时间
        LocalDateTime latestConfirmDate = LocalDateTime.of(2022, 9, 26, 19, 3, 21);
        // 当前时间
        LocalDateTime now  = LocalDateTime.of(2022, 9, 26, 18, 3, 52);
        if (latestConfirmDate.isBefore(now)) {
            System.out.println("1:超期");
        } else {
            System.out.println("0：未超期");
        }
    }


}
