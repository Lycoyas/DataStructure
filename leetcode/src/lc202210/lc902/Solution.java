package lc202210.lc902;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-10-18 10:19
 */
public class Solution {
    char[] s;
    int[] dp;
    public int atMostNGivenDigitSet(String[] digits, int n) {
        s = Integer.toString(n).toCharArray();
        int m = s.length;
        dp = new int[m];
        Arrays.fill(dp, -1);
        return f(0, digits, true, false);

    }

    int f(int i, String[] digits, boolean isLimit,boolean isNum) {

        if(i==s.length) return isNum ? 1 : 0;
        if(!isLimit&&isNum&&dp[i]!=-1) return dp[i];
        int res=0;

        if (!isNum) {

            res += f(i+1, digits, false, false);
        }

        int up = isLimit?s[i] - '0':9;

        for (String str : digits) {

            int d = Integer.parseInt(str);
            if (d > up) {
                break;
            }
            res += f(i + 1, digits, isLimit && d == up, true);

        }

        if (!isLimit && isNum) {
            dp[i] = res;
        }
        return res;

    }
}
