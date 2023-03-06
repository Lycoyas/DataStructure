package lc202210.lc1768;

/**
 * @author Lycoyas
 * @create 2022-10-23 18:58
 */
public class Solution {

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "pqr";
        Solution solution = new Solution();
        String s = solution.mergeAlternately(s1, s2);
        System.out.println(s);
    }

    public String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();

        int i=0;
        while (i != word1.length() && i != word2.length()) {

            sb.append(word1.charAt(i)).append(word2.charAt(i));
            i++;

        }

        if (word1.length() > i) {
            sb.append(word1.substring(i));
        }
        if (word2.length() > i) {
            sb.append(word2.substring(i));
        }
        return sb.toString();

    }
}
