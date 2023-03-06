package lc202212.lc1759;

/**
 * @author Lycoyas
 * @create 2022-12-26 16:20
 */
public class Solution {
    public int countHomogenous(String s) {

        final int MOD=1000000007;
        long res=0;

        char prev = s.charAt(0);

        int cnt=0;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            if (c == prev) {
                cnt++;
            }else{
                res += (long) (cnt + 1) * cnt / 2;
                cnt=1;
                prev = c;
            }

        }
        res += (long) (cnt + 1) * cnt / 2;
        return (int) (res % MOD);

    }
}
