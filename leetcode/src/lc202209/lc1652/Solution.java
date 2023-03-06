package lc202209.lc1652;

/**
 * @author Lycoyas
 * @create 2022-09-24 11:00
 */
public class Solution {

    public static void main(String[] args) {
        int[] code = {5, 7, 1, 4};
        new Solution().decrypt(code, 3);
    }
    public int[] decrypt(int[] code, int k) {


        int len = code.length;

        int[] res = new int[len];
        if (k == 0) {
            return res;
        }

        int l = k > 0 ? 1 : len + k;
        int r = k > 0 ? k : len - 1;
        int sum = 0;
        for (int i = l; i <= r; i++) {
            sum += code[i];
        }
        for (int i = 0; i < len; i++) {
            res[i] = sum;
            sum -= code[l];
            sum += code[(r + 1) % len];
            l = (++l) % len;
            r = (++r) % len;
        }
        return res;



    }
}
