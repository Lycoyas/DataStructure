package lc202210.lc2423;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lycoyas
 * @create 2022-10-11 21:09
 */
public class Solution {

    public static void main(String[] args) {
        boolean abcc = new Solution().equalFrequency("bac");
        System.out.println(abcc);
    }

    public boolean equalFrequency(String word) {
        int[] fre = new int[26];
        for (char c : word.toCharArray()) {
            fre[c - 'a']++;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();



        for (int i = 0; i < fre.length; i++) {
            if (fre[i] > 0) {
                List<Integer> list = map.get(fre[i]);
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(i);
                map.put(fre[i], list);
            }
        }

        if (map.size() == 1) {
            if(map.keySet().contains(1)){
                return true;
            }else{

                Object[] objects = map.keySet().toArray();

                int a = (int) objects[0];
                if (map.get(a).size() == 1) {
                    return true;
                }

            }

        }

        if (map.size() == 2) {
            Object[] objects = map.keySet().toArray();

            int a = (int) objects[0];
            int b = (int) objects[1];

            if (a == 1 && map.get(a).size() == 1) {
                return true;
            }
            if (b == 1 && map.get(b).size() == 1) {
                return true;
            }


            return a > b ? map.get(a).size() == 1 && a - b == 1 : map.get(b).size() == 1 && b - a == 1;

        }



        return false;

    }

}
