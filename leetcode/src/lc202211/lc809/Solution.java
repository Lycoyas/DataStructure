package lc202211.lc809;

/**
 * @author Lycoyas
 * @create 2022-11-25 10:12
 */
public class Solution {
    public int expressiveWords(String s, String[] words) {

        int result=0;
        char[] sc = s.toCharArray();
        for (String word : words) {
            result += stretchWord(sc, word.toCharArray()) ? 1 : 0;
        }
        return result;
    }

    private boolean stretchWord(char[] sc, char[] wc) {

        if (sc.length < wc.length) return false;
        int cp, p1 = 0, p2 = p1;

        while ((cp = p1) < sc.length && p2 < wc.length) {

            int c1 = 0, c2 = 0;
            while (p1 < sc.length && sc[p1] == sc[cp]) {
                c1++; p1++; // 在字符串s中，遍历连续的字符sc[cp]的数量
            }

            while (p2 < wc.length && wc[p2] == sc[cp]) {

                c2++;
                p2++;
            }
            if ((c1 != c2 && c1 < 3) || (c1 < c2 && c1 >= 3)) return false;

        }
        return p1 == sc.length && p2 == wc.length;

    }
}
