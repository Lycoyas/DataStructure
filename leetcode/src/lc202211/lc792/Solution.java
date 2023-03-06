package lc202211.lc792;

import java.util.ArrayList;

/**
 * @author Lycoyas
 * @create 2022-11-17 11:21
 */
public class Solution {

    public int numMatchingSubseq(String s, String[] words) {

        int n = s.length(), m = words.length;
        ArrayList<Integer>[] index = new ArrayList[256];

        for (int i = 0; i < n; i++) {

            char c = s.charAt(i);
            if (index[c] == null) {
                index[c] = new ArrayList<>();
            }
            index[c].add(i);

        }

        int res=0;

        for (String word : words) {
            int i=0;
            int j=0;

            for (; i < word.length(); i++) {


                char c = word.charAt(i);
                if (index[c] == null) {
                    break;
                }

                int pos = left_bound(index[c], j);
                if (pos == -1) {
                    break;
                }
                j = index[c].get(pos) + 1;

            }

            if (i == word.length()) {
                res++;
            }


        }
        return res;

    }

    int left_bound(ArrayList<Integer> arr, int target) {

        int left = 0, right = arr.size();
        while (left < right) {

            int mid = left + (right - left) / 2;
            if (target > arr.get(mid)) {
                left = mid + 1;
            }else{
                right = mid;
            }

        }

        if (left == arr.size()) {
            return -1;
        }
        return left;

    }

}
