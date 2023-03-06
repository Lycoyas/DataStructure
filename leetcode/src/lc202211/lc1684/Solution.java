package lc202211.lc1684;

/**
 * @author Lycoyas
 * @create 2022-11-08 11:11
 */
public class Solution {
    public int countConsistentStrings(String allowed, String[] words) {

        boolean[] alpha = new boolean[26];
        for (int i = 0; i < allowed.length(); i++) {
            alpha[allowed.charAt(i) - 'a'] = true;
        }

        int result=0;
        for (int i = 0; i < words.length; i++) {

            String word = words[i];
            boolean flag=true;
            for (int j = 0; j < word.length(); j++) {
                if (!alpha[word.charAt(j) - 'a']) {
                    flag=false;
                    break;
                }
            }
            if (flag) {
                result++;
            }

        }
        return result;

    }
}
