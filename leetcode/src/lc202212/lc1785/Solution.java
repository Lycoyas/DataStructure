package lc202212.lc1785;

/**
 * @author Lycoyas
 * @create 2022-12-16 19:45
 */
public class Solution {
    public int minElements(int[] nums, int limit, int goal) {

        long sum=0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        int res=0;

        if (sum == goal) {
            return res;
        }else{

            long need = Math.abs(goal - sum);
            res=(int) ((need + limit - 1) / limit);

        }
        return res;

    }
}
