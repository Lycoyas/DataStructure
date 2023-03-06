package lc202211.lc1710;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-11-15 9:12
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] boxTypes = new int[][]{{1, 3}, {2, 2}, {3, 1}};
        solution.maximumUnits(boxTypes, 4);

    }

    public int maximumUnits(int[][] boxTypes, int truckSize) {

        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int cnt = truckSize;
        int sum = 0;

        for (int i = 0; i < boxTypes.length; i++) {

            if (cnt > boxTypes[i][0]) {
                sum += boxTypes[i][1]*boxTypes[i][0];
                cnt -= boxTypes[i][0];
            }else{
                sum += cnt * boxTypes[i][1];
                cnt -= cnt;
            }
            if (cnt == 0) {
                break;
            }

        }
        return sum;

    }
}
