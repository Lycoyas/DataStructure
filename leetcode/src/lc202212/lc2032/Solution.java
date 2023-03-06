package lc202212.lc2032;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lycoyas
 * @create 2022-12-29 11:55
 */
public class Solution {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i, 1);
        }

        for (int i : nums2) {
            map.put(i, map.getOrDefault(i, 0) | 2);
        }

        for (int i : nums3) {
            map.put(i, map.getOrDefault(i, 0) | 4);
        }

        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int k= entry.getKey(),v = entry.getValue();
            if ((v & (v - 1)) != 0) {
                res.add(k);
            }
        }
        return res;

    }
}
