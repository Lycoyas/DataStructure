package basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Lycoyas
 * @create 2023-02-14 13:06
 */
public class lc239 {

    class MonotonicQueue {

        private LinkedList<Integer> maxq = new LinkedList<>();

        void push(int n) {

            while (!maxq.isEmpty() && maxq.getLast() < n) {
                maxq.pollLast();
            }
            maxq.addLast(n);

        }


        int max() {
            return maxq.getFirst();
        }

        void pop(int n) {
            if (n == maxq.getFirst()) {
                maxq.pollFirst();
            }
        }

    }
    public int[] maxSlidingWindow(int[] nums, int k) {

        MonotonicQueue window = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                window.push(nums[i]);
            }else{
                window.push(nums[i]);
                res.add(window.max());
                window.pop(nums[i - k + 1]);
            }
        }

        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;

    }
}
