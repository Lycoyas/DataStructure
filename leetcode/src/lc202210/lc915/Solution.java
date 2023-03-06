package lc202210.lc915;

/**
 * @author Lycoyas
 * @create 2022-10-24 18:43
 */
public class Solution {
    public int partitionDisjoint(int[] nums) {

        int index = 0;

        int leftMax = nums[0];
        int max = nums[0];
        for (int i=1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            if (nums[i] < leftMax) {
                leftMax=max;
                index = i;

            }


        }
        return index + 1;



    }
}
