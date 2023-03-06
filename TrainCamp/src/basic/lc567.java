package basic;

import javax.print.attribute.standard.MediaSizeName;
import java.util.HashMap;

/**
 * @author Lycoyas
 * @create 2023-02-10 12:14
 */
public class lc567 {



    public boolean checkInclusion(String s1, String s2) {

        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        int left=0,right=0;
        int valid=0;

        for (char c : s1.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        while (right < s2.length()) {

            char c = s2.charAt(right);
            right++;

            if(need.containsKey(c)){
                window.put(c, window.getOrDefault(c, 0).intValue() + 1);
                if (window.get(c).equals( need.get(c))) {
                    valid++;
                }
            }

            if (right - left == s1.length()) {

                if (valid == need.size()) {
                    return true;
                }
                char d = s2.charAt(left);
                left++;
                if(need.containsKey(d)){
                    if (window.get(d).equals( need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        return false;




    }
}
