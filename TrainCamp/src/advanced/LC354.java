package advanced;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2023-03-12 13:52
 * @description
 */
public class LC354 {

    public int maxEnvelopes(int[][] envelopes) {

        int n=envelopes.length;
        Arrays.sort(envelopes,(a,b)->{
            return a[0]==b[0]?b[1]-a[1]:a[0]-b[0];
        });

        // 对高度数组寻找 LIS
        int[] height = new int[n];
        for (int i = 0; i < n; i++)
            height[i] = envelopes[i][1];

        return lengthOfLIS(height);

    }

    int lengthOfLIS(int[] nums) {
        // 见前文
        int  piles=0;

        int[] top=new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int poke=nums[i];

            int left=0,right=piles;
            while (left<right){

                int mid=left+(right-left)/2;

                if(top[mid]<poke){
                    left=mid+1;
                }else if(top[mid]>=poke){
                    right=mid;
                }

            }
            top[left]=poke;
            if(left==piles){
                piles++;
            }


        }

        return piles;


    }

}
