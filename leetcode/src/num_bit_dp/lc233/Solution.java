package num_bit_dp.lc233;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-09-25 16:55
 */
public class Solution {
    char s[];

    int dp[][];

    public static void main(String[] args) {
        new Solution().countDigitOne(13);
    }

    public int countDigitOne(int n) {
        s=Integer.toString(n).toCharArray();
        int m=s.length;
        dp = new int[m][m];
        for(int i=0;i<m;i++){
            Arrays.fill(dp[i],-1);
        }
        return f(0, 0, true);

    }

    int f(int i, int mask, boolean isLimit) {

        if(i==s.length){
            return mask;
        }
        if(!isLimit&&dp[i][mask]!=-1) return dp[i][mask];

        int res = 0;

        for (int d = 0, up = isLimit ? s[i] - '0' : 9; d <= up; d++) {

            if (d == 1) {
                res += f(i + 1, mask + 1, isLimit && d == up);
            } else {
                res += f(i + 1, mask, isLimit && d == up);
            }

        }
        if(!isLimit) dp[i][mask] = res;
        return res;
    }



}
