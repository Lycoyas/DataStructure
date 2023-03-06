package chapter06.uva11988;

import java.util.Scanner;

/**
 * @author Lycoyas
 * @create 2022-09-16 11:28
 */
public class UVA11988 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int[] next = new int[100000 + 5];

        while (scan.hasNext()) {
            String str = scan.next();
            char[] s = new char[str.length() + 1];

            int last=0,cur=0;
            next[0]=0;
            System.arraycopy(str.toCharArray(), 0, s, 1, str.length());
            for (int i = 1; i < s.length; i++) {
                if (s[i] == '[') {
                    cur=0;
                } else if (s[i] == ']') {
                    cur = last;
                }else{
                    next[i] = next[cur];
                    next[cur] = i;
                    if (next[i] == 0) {
                        last = i;
                    }
                    cur=i;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = next[0]; i != 0; i = next[i]) {
                sb.append(s[i]);
            }
            System.out.println(sb.toString());

        }

    }

}



