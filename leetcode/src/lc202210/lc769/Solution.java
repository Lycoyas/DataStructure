package lc202210.lc769;

/**
 * @author Lycoyas
 * @create 2022-10-13 10:32
 */
public class Solution {
    public int maxChunksToSorted(int[] arr) {

        int res = 0;
        int[] dp = new int[arr.length];//i开始最小的
        int[] dp2 = new int[arr.length];//i以前最小的
        dp[arr.length - 1] = arr[arr.length - 1];
        dp2[0] = arr[0];

        for (int i = arr.length-2; i >0; i--) {
            dp[i] = Math.min(dp[i + 1], arr[i]);
            dp2[arr.length - i - 1] = Math.max(dp2[arr.length - i - 2], arr[arr.length - i - 1]);
        }



        for (int i = 1; i < arr.length; i++) {

            if (dp[i] > dp2[i-1]) {
                res++;
            }

        }
        return res;

    }
}
