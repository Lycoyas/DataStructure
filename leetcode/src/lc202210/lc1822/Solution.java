package lc202210.lc1822;

/**
 * @author Lycoyas
 * @create 2022-10-27 11:54
 */
public class Solution {

    public int arraySign(int[] nums) {

        int product=1;
        for (int num : nums) {

            if (num < 0) {
                product *= -1;
            } else if (num == 0) {
                return 0;
            }

        }
        return product;

    }

}
