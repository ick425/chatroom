import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Test {


    public static void main(String[] args) {
        System.out.println(Arrays.toString(runningSum()));


    }

    /**
     * 1480. 一维数组的动态和
     * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
     * <p>
     * 请返回 nums 的动态和。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/running-sum-of-1d-array
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 下标0：本次的值
     * 下标>0：上次的累加值＋本次的值
     * 下标从1开始，本次的累加值=上次累加+本次
     *
     * @return
     */
    public static int[] runningSum() {
        int[] nums = new int[]{1, 2, 3, 4};
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }
}
