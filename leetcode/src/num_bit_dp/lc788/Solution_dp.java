package num_bit_dp.lc788;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-09-25 16:09
 */
public class Solution_dp {
    static int[] DIFFS = {0, 0, 1, -1, -1, 1, 1, -1, 0, 1};
    char[] s;
    int[][] dp;

    public int rotatedDigits(int n) {
        s = Integer.toString(n).toCharArray();
        int m = s.length;
        dp = new int[m][2];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], -1);

        return f(0, 0, true);

    }

    int f(int i, int hasDiff, boolean isLimit) {

        if(i==s.length) return hasDiff;
        if(!isLimit&&dp[i][hasDiff]!=-1) return dp[i][hasDiff];

        int res=0;
        int up = isLimit ? s[i] - '0' : 9;
        for (int d = 0; d <= up; d++) {
            if (DIFFS[d] != -1) {
                res+=f(i+1,hasDiff|DIFFS[d],isLimit&&d==up);
            }
        }
        if(!isLimit) dp[i][hasDiff] = res;
        return res;

    }
}
