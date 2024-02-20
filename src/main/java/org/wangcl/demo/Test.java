package org.wangcl.demo;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import org.apache.commons.collections4.CollectionUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Test {


    public static void main(String[] args) {
        String natResultTime = "2022-11-14 16:00:00";
        LocalDateTime natTime = LocalDateTimeUtil.parse(natResultTime, "yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        long between = LocalDateTimeUtil.between(natTime, now, ChronoUnit.DAYS);
        System.out.println(between + "天");
    }

    private static void changeInt(Integer a, Integer b) {
        a = 120;
        b = 230;
        System.out.println(a + ",222" + b);
    }


    static void test3() {
        List<String> list = new ArrayList<>();
        System.out.println(list.contains("1"));

    }

    static void test2() {
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");

        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("c");
        list2.add("b");

        boolean equals = list1.equals(list2);
        System.out.println("equals:" + equals);

        Collection<String> disjunction = CollectionUtil.disjunction(list1, list2);
        Collection<String> disjunction1 = CollectionUtils.disjunction(list1, list2);
    }

    static void test1() {
        String workType = ",";
        List<Integer> collect = Arrays.stream(workType.split(","))
                .map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * <p>1480. 一维数组的动态和</p>
     *
     * <p>给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。</p>
     *
     * <p>请返回 nums 的动态和。</p>
     *
     * <p>来源：力扣（LeetCode）</p>
     * <p>链接：<a href="https://leetcode.cn/problems/running-sum-of-1d-array">https://leetcode.cn/problems/running-sum-of-1d-array</a></p>
     * <p>著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。</p>
     * <p>
     * <p>下标0：本次的值</p>
     * <p>下标>0：上次的累加值＋本次的值</p>
     * <p>下标从1开始，本次的累加值=上次累加+本次</p>
     */
    public static int[] runningSum() {
        int[] nums = new int[]{1, 2, 3, 4};
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }


    /**
     * <p>
     * 383.赎金信
     * <p>
     * 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
     * <p>
     * 如果可以，返回 true ；否则返回 false 。
     * <p>
     * magazine 中的每个字符只能在 ransomNote 中使用一次。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：<a href="https://leetcode.cn/problems/ransom-note">https://leetcode.cn/problems/ransom-note</a>
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @return
     */
    public static boolean canConstruct() {
        String ransomNote = "aa", magazine = "aab";
        char[] chars = magazine.toCharArray();
        List<Character> list = new ArrayList<>();
        for (char aChar : chars) {
            list.add(aChar);
        }
        boolean flag = false;
        for (int i = 0; i < ransomNote.length(); i++) {
            Character c = ransomNote.charAt(i);
            // 后面的字符串集合没数据，也是false
            if (list.isEmpty()) {
                flag = false;
            }
            for (int j = list.size() - 1; j >= 0; j--) {
                Character next = list.get(j);
                if (next.equals(c)) {
                    list.remove(j);
                    flag = true;
                    break;
                }
                flag = false;
            }
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    /**
     * 1.两数之和
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{i, map.get(target - nums[i])};
            }
            map.put(nums[i], i);
        }
        return null;
    }
}
