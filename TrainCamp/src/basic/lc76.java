package basic;

import java.util.HashMap;

/**
 * @author Lycoyas
 * @create 2023-02-10 11:47
 */
public class lc76 {

    public static void main(String[] args) {

        new lc76().minWindow("ADOBECODEBANC",
                "ABC");

    }

    public String minWindow(String s, String t) {

        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        int left=0,right=0;
        int valid=0;

        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        int start = 0, len = Integer.MAX_VALUE;

        while (right < s.length()) {

            char c=s.charAt(right);
            right++;
            if(need.containsKey(c)){
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals( need.get(c))) {
                    valid++;
                }
            }

            while (valid == need.size()) {

                if (right - left < len) {
                    len = right - left;
                    start = left;
                }
                char cl=s.charAt(left);
                left++;
                if(need.containsKey(cl)){
                    if (window.get(cl).equals( need.get(cl))) {
                        valid--;
                    }
                    window.put(cl, window.getOrDefault(cl, 0) - 1);
                }

            }

        }

        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);

    }

}
