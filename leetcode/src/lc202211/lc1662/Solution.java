package lc202211.lc1662;

/**
 * @author Lycoyas
 * @create 2022-11-01 12:04
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution=new Solution();
        String[] word1 = new String[]{"ab", "c"};
        String[] word2 = new String[]{"a", "bc"};
        System.out.println(solution.arrayStringsAreEqual(word1, word2));
    }

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {

        StringBuilder sb = new StringBuilder();
        for (String str : word1) {
            sb.append(str);
        }
        System.out.println(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        for (String str : word2) {
            sb2.append(str);
        }
        System.out.println(sb2.toString());
        return sb.toString().equals(sb2.toString());



    }
}
