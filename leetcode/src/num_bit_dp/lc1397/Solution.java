package num_bit_dp.lc1397;

/**
 * @author Lycoyas
 * @create 2022-09-25 21:35
 */
public class Solution {

    int MOD = (int) (1e9 + 7);
    int[][] dp;


    public int findGoodStrings(int n, String s1, String s2, String evil) {


        int len = s1.length();
        int evilLen = evil.length();
        dp = new int[len][evilLen];

        return f(0, true, true, s1, s2,evil,0);
    }

    int f(int i,  boolean botisLimit,boolean topisLimit, String s1,String s2,String evil,int eviIndex) {

        if(eviIndex==evil.length()) return 0;
        if(i==s1.length()) return 1;
        if (!botisLimit&&!topisLimit && dp[i][eviIndex]!=0) {
            return dp[i][eviIndex];
        }

        int res=0;

        int bot = botisLimit ? s1.charAt(i) - 'a' : 0;
        int up = topisLimit ? s2.charAt(i) - 'a' : 25;
        for (int d=bot; d <= up; d++) {


                res = (res + f(i + 1, botisLimit && d == bot, topisLimit && d == up, s1, s2,evil,getNextStats(eviIndex, (char) (d+'a'),evil)))%MOD;

        }



        if (!botisLimit&&!topisLimit) dp[i][eviIndex] = res;
        return res;

    }

    private int getNextStats(int stats, char ch,String evil) {
        if (evil.charAt(stats) == ch) {
            return stats + 1;
        }
        String s = evil.substring(0, stats) + ch;
        for (int i = 1; i < s.length(); i++) {
            String t = s.substring(i);
            boolean matched = true;
            for (int j = 0; j < t.length(); j++) {
                if (evil.charAt(j) != t.charAt(j)) {
                    matched = false;
                    break;
                }
            }
            if (matched) {
                return t.length();
            }
        }
        return 0;
    }

}
