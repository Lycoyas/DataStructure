package lc202211.lc878;

/**
 * @author Lycoyas
 * @create 2022-11-22 10:49
 */
public class Solution {
    private static final long MOD = (long) 1e9 + 7;

    public int nthMagicalNumber(int n, int a, int b) {

        long lcm = a / gcd(a, b) * b;
        long left = 0, right = (long) Math.min(a, b) * n;

        while (left + 1 < right) {

            long mid = left + (right - left) / 2;
            if (mid / a + mid / b - mid / lcm >= n) {
                right = mid;
            }else{
                left = mid;
            }

        }
        return (int) (right % MOD);

    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
