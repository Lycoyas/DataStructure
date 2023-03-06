package lc202212.lc1774;

/**
 * @author Lycoyas
 * @create 2022-12-04 16:11
 */
public class Solution {
    int ans = 0x3f3f3f3f;
    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {

        for(int x:baseCosts) dfs(0, x, target, toppingCosts);
        return ans;

    }

    void dfs(int x, int sum, int target, int[] top) {

        int a = Math.abs(target - sum), b = Math.abs(target - ans);
        if (a < b) {
            ans = sum;
        }
        if (a == b && sum < ans) {
            ans = sum;
        }
        for (int i = x; i < top.length; i++) {


            dfs(i + 1, sum + top[i], target, top);
            dfs(i + 1, sum + 2 * top[i], target, top);

        }

    }
}
