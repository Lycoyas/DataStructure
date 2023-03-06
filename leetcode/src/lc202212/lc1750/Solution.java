package lc202212.lc1750;

/**
 * @author Lycoyas
 * @create 2022-12-28 16:42
 */
public class Solution {
    public int minimumLength(String s) {


        int n = s.length();
        int left = 0, right = n - 1;
        while (left < right && s.charAt(left) == s.charAt(right)) {

            char c = s.charAt(left);
            while (left <= right && s.charAt(left) == c) {
                left++;
            }
            while (left <= right && s.charAt(right) == c) {
                right--;
            }

        }
        return right - left + 1;


    }
}
