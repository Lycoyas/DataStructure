package lc202211.lc1752;

/**
 * @author Lycoyas
 * @create 2022-11-27 12:19
 */
public class Solution {

    public boolean check(int[] nums) {

        int t = 0, n = nums.length;
        for (int i = 1; i < n; i++) {
            if (t > 1) {
                return false;
            }
            if(nums[i-1]>nums[i]) t++;
        }
        return t == 0 || (t == 1 && nums[0] >= nums[n - 1]);
    }

}
