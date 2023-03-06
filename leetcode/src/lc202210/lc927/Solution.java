package lc202210.lc927;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Lycoyas
 * @create 2022-10-06 10:48
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums=new int[]{1,1,1,0,0,1,1,1,1,1,1,0,0,1,1,1,0,1,1,1,0};
        int[] ints = new Solution().threeEqualParts(nums);
        System.out.println(Arrays.toString(ints));
    }

    public int[] threeEqualParts(int[] arr) {

        int oneCount=0;
        int zeroCount=0;
        for (int num : arr) {
            if(num==1) oneCount++;
        }
        for (int i = arr.length-1; i >= 0; i--) {
            if (arr[i] == 0) {
                zeroCount++;
            }else{
                break;
            }
        }

        if (oneCount % 3 != 0) {
            return new int[]{-1, -1};
        }

        if (oneCount == 0) {
            return new int[]{0, arr.length - 1};
        }

        int n = oneCount / 3;

        int temp=0;
        int[] res = new int[2];

        for (int i=0;i<arr.length;i++) {
            if (arr[i] == 1) {
                temp++;
            }
            if(temp==n){
                while (zeroCount>0) {
                    i++;
                    if(arr[i]!=0){
                        return new int[]{-1, -1};
                    }
                    zeroCount--;
                }

                res[0] = i;
                break;
            }
        }

        List<Integer> list = new ArrayList<>();
        int start=0;
        while(arr[start]==0){start++;}
        for (int i = start; i <= res[0]; i++) {
            list.add(arr[i]);
        }

        start = res[0] + 1;
        while(arr[start]==0){start++;}
        for (int i = start; i < arr.length; i++) {
            if(i-start==list.size()){
                res[1]=i;
                break;
            }
            if (list.get(i - start) != arr[i]) {
                return new int[]{-1, -1};
            }
        }

        start = res[1];
        while(arr[start]==0){start++;}
        for (int i = start; i < arr.length; i++) {
            if (list.get(i - start) != arr[i]) {
                return new int[]{-1, -1};
            }
        }


        return new int[]{res[0], res[1]};

    }
}
