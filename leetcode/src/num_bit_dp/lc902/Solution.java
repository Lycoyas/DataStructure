package num_bit_dp.lc902;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-09-25 20:10
 */
public class Solution {
    char[]s;
    int[] dp;

    public static void main(String[] args) {
        String[] digits={"1","3","5","7"};
        System.out.println(new Solution().atMostNGivenDigitSet(digits, 100));
    }

    public int atMostNGivenDigitSet(String[] digits, int n) {

        s = String.valueOf(n).toCharArray();
        int m = s.length;
        dp = new int[m];
        Arrays.fill(dp, -1);
        return f(0, true,false, digits);

    }

    int f(int i, boolean isLimit,boolean isNum,String[] digits) {
        if(i==s.length) return isNum?1:0;
        if(!isLimit&&isNum&&dp[i]!=-1) return dp[i];
        int res=0;

        if(!isNum) res = f(i + 1, false, false, digits);

        int up=isLimit? s[i] - '0' : 9 ;
        for (String str : digits) {
            int d = Integer.parseInt(str);
            if(d>up) break;
            res += f(i + 1, isLimit && d == up, true,digits);
        }

        if(!isLimit&&isNum) dp[i] = res;
        return res;

    }





}
