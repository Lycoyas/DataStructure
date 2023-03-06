package chapter07.enumearrange;

import java.util.Arrays;

/**可重集的排列
 * @author Lycoyas
 * @create 2022-10-02 09:55
 */
public class RepeatPermutation {
    public static void main(String[] args) {

        int n = 3;
        int[] nums = {1,1,1};
        Arrays.sort(nums);
        int[] perm = new int[n];
        printPermutation(n,nums,perm,0);

    }

    static void printPermutation(int n,int[]nums,int[] perm,int cur) {

        if (cur == n) {
            for (int num : perm) {
                System.out.print(num+" ");
            }
            System.out.println();
        }else{
            for (int i = 0; i < n; i++) {
                //已经排过序，拒绝重复
                if (i == 0 || nums[i] != nums[i-1]) {
                    int c1 = 0, c2 = 0;
                    for (int j = 0; j < cur; j++) {
                        if(perm[j]==nums[i]) c1++;
                    }
                    for (int j = 0; j < n; j++) {
                        if(nums[j]==nums[i]) c2++;
                    }
                    if (c1<c2) {
                        perm[cur] = nums[i];
                        printPermutation(n,nums, perm, cur + 1);
                    }
               }
            }

        }


    }
}
