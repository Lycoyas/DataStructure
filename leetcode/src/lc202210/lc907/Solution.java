package lc202210.lc907;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-10-28 11:42
 */
public class Solution {
    public int sumSubarrayMins(int[] arr) {

        long result=0;
        int[] stack = new int[arr.length];
        int head = 0, tail = head, mod = (int) (1e9 + 7);

        for (int i = 0; i < arr.length; i++) {

            int num = (i == arr.length) ? 0 : arr[i];
            while (head != tail && arr[stack[tail - 1]] > num) {

                int n = stack[--tail];
                int h = (head != tail) ? stack[tail - 1] : -1;
                int t = i;
                result = (result + (long) (n - h) * (t - n) * arr[n]) % mod;

            }
            stack[tail++] = i;

        }
        return (int) result;



    }
}
