package lc202209.lc698;

import java.awt.*;
import java.util.Arrays;
import java.util.Set;

/**
 * @author Lycoyas
 * @create 2022-09-20 11:27
 */
public class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {

        // 求总和
        int sum = 0;
        for (int i = 0; i < nums.length; i++) sum += nums[i];
// 不能刚好分配的情况
        if (sum % k != 0) return false;
// target 即每个子集所需要满足的和
        int target = sum / k;

        int[] bucket = new int[k];

        // 降序排列
        Arrays.sort(nums);
        int left = 0, right= nums.length - 1;
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }

        return backtrack(nums, 0, bucket, k,target);

    }

    private boolean backtrack(int[] nums, int index, int[] bucket, int k, int target) {

        if (index == nums.length) {
            return true;
        }

        for (int i = 0; i < k; i++) {
            if (i > 0 && index == 0) {
                break;
            }

            if(bucket[i]+nums[index]>target) continue;

            if(i>0&&bucket[i]==bucket[i-1]) continue;

            bucket[i] += nums[index];
            if (backtrack(nums, index + 1, bucket, k, target)) {
                return true;
            }
            bucket[i] -= nums[index];

        }
        return false;

    }
}
