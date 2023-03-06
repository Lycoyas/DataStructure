package lc202212.lc2027;

/**
 * @author Lycoyas
 * @create 2022-12-27 17:53
 */
public class Solution {
    public int minimumMoves(String s) {

        int res=0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'X') {
                res++;
                i += 2;
            }
        }
        return res;

    }
}
