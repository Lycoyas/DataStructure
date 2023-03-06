package lc202209.lc_17_09;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Lycoyas
 * @create 2022-09-28 10:20
 */
public class Solution {
    public int getKthMagicNumber(int k) {

        int[] dp = new int[k + 1];
        dp[1] = 1;
        int p3 = 1, p5 = 1, p7 = 1;
        for (int i = 2; i <= k; i++) {

            int num3 = dp[p3] * 3, num5 = dp[p5] * 5, num7 = dp[p7] * 7;
            dp[i] = Math.min(Math.min(num3, num5), num7);
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
            if (dp[i] == num7) {
                p7++;
            }

        }
        return dp[k];

    }
}
