package lc202211.lc775;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-11-16 11:17
 */
public class Solution {
    public boolean isIdealPermutation(int[] nums) {

        /*int max = nums[0];
        for (int i = 2; i < nums.length; i++) {
            if(nums[i]<max) return false;
            max = Math.max(max, nums[i - 1]);
        }
        return true;*/

        for (int i = 0; i < nums.length; i++) {

            if (Math.abs(i - nums[i]) > 1) {
                return false;
            }

        }
        return true;
    }
}
