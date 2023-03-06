package lc202209.lc854;

/**
 * @author Lycoyas
 * @create 2022-09-21 11:50
 */
public class Main {

    int result = Integer.MAX_VALUE;

    public int kSimilarity(String s1, String s2) {

        return backtrack(s1.toCharArray(), s2.toCharArray(), 0, 0);


    }

    public int backtrack(char[] s1, char[] s2, int start, int current) {

        if(current>=result) return result;
        if(start==s1.length-1) return Math.min(current, result);

        for (int i = start; i < s1.length; i++) {
            if (s1[i] != s2[i]) {
                for (int j = i + 1; j < s1.length; j++) {
                    if (s1[j] == s1[i]) {
                        swap(s1,i,j);
                        backtrack(s1, s2, i + 1, current + 1);
                        swap(s1, i, j);
                    }
                }
                return result;
            }
        }
        return result = Math.min(current, result);

    }

    public void swap(char[] sc, int i, int j){
        char temp = sc[i];
        sc[i] = sc[j];
        sc[j] = temp;
    }





}
