package lc202211.lc791;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Lycoyas
 * @create 2022-11-13 10:41
 */
public class Solution {
    public String customSortString(String order, String s) {

        int w[] = new int[26];
        int x=1;
        for (char c : order.toCharArray()) {
            w[c - 'a'] = x++;
        }

        Character[] arr = new Character[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            arr[i] = s.charAt(i);
        }

        Arrays.sort(arr,(a,b)->w[a-'a']-w[b-'a']);
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            ans.append(arr[i]);
        }
        return ans.toString();

    }
}
