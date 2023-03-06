package lc202211.lc799;

/**
 * @author Lycoyas
 * @create 2022-11-20 19:23
 */
public class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {

        double[][] dp = new double[query_row + 2][query_row + 2];
        dp[1][1] = poured;
        for (int row = 2; row <= query_row + 1; row++) {
            for (int column = 1; column <= row; column++) {
                dp[row][column]=Math.max(dp[row-1][column-1]-1,0)/2+Math.max(dp[row-1][column]-1,0)/2;
            }
        }
        return Math.min(dp[query_row + 1][query_glass + 1], 1);

    }


}
