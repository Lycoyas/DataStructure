package lc202212.lc1753;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-12-21 17:35
 */
public class Solution {
    public int maximumScore(int a, int b, int c) {

        int ans=0;
        int[] rec = new int[]{a, b, c};
        Arrays.sort(rec);

        while (rec[0] != 0 || rec[1] != 0) {
            ans++;
            rec[1]--;rec[2]--;
            Arrays.sort(rec);
        }
        return ans;

    }
}
