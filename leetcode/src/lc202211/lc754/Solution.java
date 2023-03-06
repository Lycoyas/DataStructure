package lc202211.lc754;

/**
 * @author Lycoyas
 * @create 2022-11-04 17:15
 */
public class Solution {
    public int reachNumber(int target) {

        int result = 0, num = 0, t = Math.abs(target);

        while (num < t || (num - t) % 2 != 0) {

            num += ++result;

        }
        return result;

    }
}
