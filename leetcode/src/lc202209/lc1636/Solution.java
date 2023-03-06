package lc202209.lc1636;

import java.util.*;

/**
 * @author Lycoyas
 * @create 2022-09-19 10:40
 */
public class Solution {

    public int[] frequencySort(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
            map.put(num, 1 + map.getOrDefault(num, 0));
        }
        Collections.sort(list,new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int c1 = map.get(o1);
                int c2 = map.get(o2);
                return c1!=c2? c1 - c2 : o2 - o1  ;
            }
        });


        return list.stream().mapToInt(Integer::intValue).toArray();



    }



}
