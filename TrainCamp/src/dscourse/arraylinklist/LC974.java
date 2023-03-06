package dscourse.arraylinklist;

import java.util.HashMap;

/**
 * @author Lycoyas
 * @create 2023-02-23 20:29
 */
public class LC974 {

    public static void main(String[] args) {
        System.out.println(subarraysDivByK(new int[]{4, 5, 0, -2, -3, 1}, 5));

    }

    public static int subarraysDivByK(int[] nums, int k) {

        int n=nums.length;
        int[] preSum=new int[n+1];
        for(int i=1;i<=n;i++){
            preSum[i]=preSum[i-1]+nums[i-1];
        }

        HashMap<Integer,Integer> map=new HashMap<>();

        int res=0;

        for(int i=0;i<preSum.length;i++){

            int need=preSum[i]%k;

            if(need<0){
                need+=k;
            }

            if(map.containsKey(need)){
                res+=map.get(need);

            }


           map.put(need,map.getOrDefault(need,0)+1);


        }
        return res;



    }
}
