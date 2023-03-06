package lc202211.lc813;

/**
 * @author Lycoyas
 * @create 2022-11-28 11:44
 */
public class Solution {

    public double largestSumOfAverages(int[] nums, int k) {

        int n = nums.length;
        double[] sum = new double[n + 10];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        double[][] f = new double[n + 10][k + 10];

        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= Math.min(i, k); j++) {
                if (j == 1) {
                    f[i][1] = sum[i] / i;
                }else{
                    for (int m = 2; m <= i; m++) {

                        f[i][j] = Math.max(f[i][j], f[m - 1][j - 1] + (sum[i] - sum[m - 1]) / (i - m + 1));

                    }
                }
            }

        }
        return f[n][k];

    }

}
