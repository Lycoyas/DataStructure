package lc202209.lc1640;

import java.util.HashMap;

/**
 * @author Lycoyas
 * @create 2022-09-22 10:37
 */
public class Solution {
    public static void main(String[] args) {
        int arr[]={2,82,79,95,28};
        int pieces[][] = {{2}, {82},{28},{79,95}};
        new Solution().canFormArray(arr, pieces);

    }
    public boolean canFormArray(int[] arr, int[][] pieces) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < pieces.length; i++) {
            map.put(pieces[i][0], i);
        }

        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];

            int index = map.getOrDefault(num,-1);

            if(index==-1) {return false;}
            for (int j = 1; j < pieces[index].length; j++) {
                if (arr[i + j] != pieces[index][j]) {
                    return false;
                }
            }
            i+=pieces[index].length-1;

        }
        return true;

    }
}
