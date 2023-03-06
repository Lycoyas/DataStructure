package lc202210.lc862;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Lycoyas
 * @create 2022-10-26 17:13
 */
public class Solution {

    public int shortestSubarray(int[] nums, int k) {

        int n = nums.length, ans = n + 1;

        long[] s = new long[n + 1];
        for (int i = 0; i < n; i++) {
            s[i + 1] = s[i] + nums[i];
        }

        Deque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i <= n; i++) {
            long curS = s[i];
            while (!q.isEmpty() && curS - s[q.getFirst()] >= k) {
                ans = Math.min(ans, i - q.removeFirst());
            }
            while (!q.isEmpty() && s[q.getLast()] >= curS) {
                q.removeLast();
            }
            q.addLast(i);
        }
        return ans > n ? -1 : ans;

    }

}
