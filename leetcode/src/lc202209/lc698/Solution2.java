package lc202209.lc698;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Lycoyas
 * @create 2022-09-20 11:55
 */
public class Solution2 {
    public boolean canPartitionKSubsets(int[] nums, int k) {

        // 求总和
        int sum = 0;
        for (int i = 0; i < nums.length; i++) sum += nums[i];
// 不能刚好分配的情况
        if (sum % k != 0) return false;
// target 即每个子集所需要满足的和
        int target = sum / k;
        int[] bucket = new int[k];
        int used=0;
        return backtrack(nums, 0, bucket, k-1, target, used);

    }

    private HashMap<Integer, Boolean> memo = new HashMap<>();
    private boolean backtrack(int[] nums, int start, int[] bucket, int k, int target, int used) {

        if(k==0) return true;


        if (bucket[k] == target) {
            boolean res=backtrack(nums, 0, bucket, k - 1, target, used);
            memo.put(used, res);
            return res;
        }

        if (memo.containsKey(used)) {
            return memo.get(used);
        }

        for (int i = start; i < nums.length; i++) {

            if(((used>>i)&1)==1) continue;
            if(bucket[k]+nums[i]>target) continue;
            bucket[k] += nums[i];
            used |= 1<<i;

            if(backtrack(nums,i+1,bucket,k,target,used)) return true;

            bucket[k] -= nums[i];
            used ^= 1<<i ;

            while(i+1<nums.length&&nums[i+1]==nums[i]) i++;

        }
        return false;

    }


}
