package lc202211.lc1742;

/**
 * @author Lycoyas
 * @create 2022-11-23 11:59
 */
public class Solution {
    public static void main(String[] args) {
        new Solution().countBalls(1, 10);
    }

    public int countBalls(int lowLimit, int highLimit) {

        int[] cnt = new int[46];

        for (int i = lowLimit; i <= highLimit; i++) {
            int temp = getSum(i);
            cnt[temp]++;

        }

        int max = cnt[1];
        for (int i = 2; i < cnt.length; i++) {

            if (cnt[i] > max) {
                max = cnt[i];
            }

        }
        return max;
    }

    int getSum(int i) {

        int ans=0;
        while (i > 0) {

            ans += i % 10;
            i = i / 10;

        }
        return ans;

    }
}
