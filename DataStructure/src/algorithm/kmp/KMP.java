package algorithm.kmp;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-09-11 15:35
 */
public class KMP {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext("ABCDABD");
        System.out.println(Arrays.toString(next));

        int index = kmpSearch(str1, str2, next);
        System.out.println("index = " + index);

    }

    //获取到一个字符串（字串）的部分匹配值
    public static int[] kmpNext(String dest) {
        //next数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0]=0; //如果字符串长度为1，部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //dest.charAt(i) != dest.charAt(j)时，需要从next[j-1]获取新的j
            //直到我们发现有dest.charAt(i) == dest.charAt(j)成立才退出

            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i]=j;
        }

        return next;

    }



    /**
     * KMP搜索算法
     * @param str1 源字符串
     * @param str2 字串
     * @param next 字串对应的部分匹配表
     * @return
     */
    public static int kmpSearch(String str1, String str2, int[] next) {

        for (int i = 0, j = 0; i < str1.length(); i++) {

            //考虑str1.charAt(i) != str2.charAt(j)
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            if (j == str2.length()) {
                return i-j+1;
            }

        }

        return -1;

    }

}
