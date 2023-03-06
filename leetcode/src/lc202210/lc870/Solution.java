package lc202210.lc870;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Lycoyas
 * @create 2022-10-08 16:29
 */
public class Solution {

    public static void main(String[] args) {
        int[] nums1 = new int[]{2, 7, 11, 15};
        int[] nums2 = new int[]{1, 10, 4, 11};

        int[] ints = new Solution().advantageCount(nums1, nums2);
        System.out.println(Arrays.toString(ints));

    }
    public int[] advantageCount(int[] nums1, int[] nums2) {

        List<Integer> list = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        Collections.sort(list);

        int[] res = new int[nums1.length];

        for (int i = 0; i < res.length; i++) {

            if (list.get(list.size() - 1) > nums2[i]) {
                int index = binary(list, nums2[i]);
                res[i] = list.get(index);
                list.remove(index);
            }else{
                res[i] = list.get(0);
                list.remove(0);
            }


        }
        return res;

    }

    static int binary(List<Integer> list, int target) {

        int left = 0, right = list.size() - 1;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else if (list.get(mid) > target) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;

    }

}
