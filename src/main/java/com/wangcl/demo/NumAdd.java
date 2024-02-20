package com.wangcl.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NumAdd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double target = Double.parseDouble(scanner.nextLine());
        double[] nums = {9.9, 1, 2, 3, 4, 5, 1.1, 10, 10.1, 1.2, 10.5, 20.5, 20.1, 20, 30, 40, 50, 70, 100, 120};
        List<Double> doubles = combinationSum(nums, target);
        System.out.println(doubles);
    }

    public static List<Double> combinationSum(double[] numbers, double target) {
        Arrays.sort(numbers);
        Pair minSum = new Pair(Double.POSITIVE_INFINITY, new ArrayList<>());
        backtrack(numbers, 0, new ArrayList<>(), target, 0, minSum);
        return minSum.list;
    }

    private static void backtrack(double[] numbers, int start, List<Double> path, double target, int count,
                                  Pair minSum) {
        if (target == 0) {
            if (count < minSum.count) {
                minSum.count = count;
                minSum.list.clear();
                minSum.list.addAll(path);
            }
            return;
        }
        if (count >= minSum.count) {
            return;
        }
        for (int i = start; i < numbers.length; i++) {
            if (numbers[i] > target) {
                break;
            }
            path.add(numbers[i]);
            backtrack(numbers, i, path, target - numbers[i], count + 1, minSum);
            path.remove(path.size() - 1);
        }
    }

    private static class Pair {
        double count;
        List<Double> list;

        Pair(double count, List<Double> list) {
            this.count = count;
            this.list = list;
        }
    }
}