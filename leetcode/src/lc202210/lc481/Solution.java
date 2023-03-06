package lc202210.lc481;

/**
 * @author Lycoyas
 * @create 2022-10-31 11:04
 */
public class Solution {
    public int magicalString(int n) {

        int[] magic = new int[n + 1];

        magic[0] = 1;
        int tail = 1, p = 1, result = 1, value = 1, count = 2;
        while (tail < n) {
            value = value ^ 3;
            while (count-- > 0 && tail < n) {

                magic[tail++] = value;
                if(value==1) result++;

            }
            count = magic[++p];
        }
        return result;

    }
}
