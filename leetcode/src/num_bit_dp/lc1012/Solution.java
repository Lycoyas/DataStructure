package num_bit_dp.lc1012;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-09-25 20:51
 */
public class Solution {

    char[] s;
    int[][][] dp;

    public static void main(String[] args) {
        System.out.println(new Solution().numDupDigitsAtMostN(1000));
    }

    public int numDupDigitsAtMostN(int n) {
        s = String.valueOf(n).toCharArray();
        int m = s.length;
        dp = new int[m][1<<10][2]; //第i位有无重复数字

        return f(0, 0, 0, true, false);
    }

    int f(int i,int mask,int hasRepeat,boolean isLimit,boolean isNum) {

        if (i == s.length) return isNum&&hasRepeat==1 ? 1 : 0;
        if (!isLimit && isNum && dp[i][mask][hasRepeat] > 0) return dp[i][mask][hasRepeat];

        int res = 0;
        //跳过当前位
        if (!isNum) res = f(i + 1, mask, 0, false, false);

        for (int d = isNum ? 0 : 1, up = isLimit ? s[i] - '0' : 9; d <= up; d++) {


            if (((mask >> d) & 1) == 0) {//d不在mask中

                res += f(i + 1, mask | (1 << d),hasRepeat, isLimit && d == up, true);

            }else{
                res+=f(i+1,mask,1,isLimit && d == up, true);
            }

        }

        if (!isLimit && isNum) dp[i][mask][hasRepeat] = res;
        return res;
    }

}
