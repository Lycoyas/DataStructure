package num_bit_dp.lc600;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Lycoyas
 * @create 2022-09-25 17:31
 */
public class Solution {

    char s[];
    int[][] dp;

    public static void main(String[] args) {


        System.out.println(new Solution().findIntegers(100000000));

    }

    public int findIntegers(int n) {

        s=Integer.toBinaryString(n).toCharArray();
        int m = s.length;
        dp = new int[m][2];
        for (int i = 0; i < m; i++) Arrays.fill(dp[i], -1);


        return f(0, 0, true);

    }


    int f(int i, int mask, boolean isLimit) {

        if(i==s.length) {
            return 1;
        }
        if(!isLimit&&dp[i][mask]!=-1) return dp[i][mask];

        int res=0;

        for (int d = 0, up = isLimit ? s[i] - '0' : 1; d <= up; d++) {

            if (mask == 1 && d == 1) {

            }else{
                res+=f(i + 1, d, isLimit && d == up);
            }


        }
        if(!isLimit) dp[i][mask]=res;
        return res;
    }

}
