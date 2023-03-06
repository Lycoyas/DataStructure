package lc202211.lc891;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-11-18 21:06
 */
public class Solution {
    public int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums); // 排序
        int mod = (int)1e9 + 7, n = nums.length;
        long result = 0;
        long[] pow = new long[n];
        pow[0] = 1;
        for (int i = 1; i < n; i++)
            pow[i] = (pow[i - 1] << 1) % mod; // 初始化2^n的值

        for (int i = 0; i < n; i++)
            result = (result + (pow[i] - pow[n-i-1]) * nums[i] % mod) % mod; // 计算总和
        return (int)result;
    }



}
