package lc202212.lc1769;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lycoyas
 * @create 2022-12-02 17:32
 */
public class Solution {
    public int[] minOperations(String boxes) {

        int[] result = new int[boxes.length()];
        for (int i = 0; i < boxes.length(); i++) {

             if(boxes.charAt(i)=='0') continue;
            for (int j = 0; j < result.length; j++) {
                result[j] += Math.abs(j - i);
            }

        }
        return result;

    }
}
