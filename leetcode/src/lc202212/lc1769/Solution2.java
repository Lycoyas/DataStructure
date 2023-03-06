package lc202212.lc1769;

/**
 * @author Lycoyas
 * @create 2022-12-02 17:57
 */
public class Solution2 {
    public int[] minOperations(String boxes) {

        int[] result = new int[boxes.length()];
        char[] bc = boxes.toCharArray();
        int rc = 0, lc = (bc[0] == '1' ? 1 : 0);
        for (int i = 1; i < bc.length; i++) {
            if (bc[i] == '1') {
                result[0] += i;
                rc++;
            }
        }

        for (int i = 1; i < result.length; i++) {

            result[i] = result[i - 1] + lc - rc;
            if (bc[i] == '1') {
                lc++;
                rc--;
            }

        }
        return result;

    }
}
