package lc202209.lc17_19;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-09-26 09:05
 */
public class Solution {

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(13));
        System.out.println(Integer.toBinaryString(-13));

    }


    public int[] missingTwo(int[] nums) {
        int n = nums.length;
        int sum = (1 + n) * n / 2;
        int sumOfNums = Arrays.stream(nums).sum();
        int ts = sum - sumOfNums;

        int m = ts / 2;

        sum=0;
        for (int i : nums) {
            if (i <= m) {
                sum += i;
            }
        }

        int res = (1 + m) * m / 2 - sum;
        return new int[]{res, ts - res};

    }

    //平方和
    public int[] missingTwo2(int[] nums) {
        int xorsum=0;
        int n = nums.length + 2;
        for (int num : nums) {
            xorsum ^= num;
        }
        for (int i = 1; i <= n; i++) {
            xorsum ^= i;
        }

        //防止溢出
        int diff=xorsum&(-xorsum);

        int o=0;
        for (int i = 1; i <= n; i++) {
            if((diff&i)!=0) o ^= i;
        }
        for (int i : nums) {
            if((diff&i)!=0) o ^= i;
        }

        return new int[]{o, o ^ xorsum};

    }

}
