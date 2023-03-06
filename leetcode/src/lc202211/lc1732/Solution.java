package lc202211.lc1732;

/**
 * @author Lycoyas
 * @create 2022-11-19 15:57
 */
public class Solution {
    public int largestAltitude(int[] gain) {

        int res = -200;
        int high = 0;

        for (int i = 0; i < gain.length; i++) {

            high = high + gain[i];
            res = Math.max(res, high);
        }
        return res;

    }
}
