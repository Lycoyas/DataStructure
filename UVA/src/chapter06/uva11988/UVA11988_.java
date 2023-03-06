package chapter06.uva11988;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * @author Lycoyas
 * @create 2022-09-16 17:29
 */
public class UVA11988_ {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str=br.readLine())!=null) {

            int pos = 0;
            LinkedList<Character> list = new LinkedList();

            for (char c : str.toCharArray()) {
                if (c == '[') {
                    pos = 0;
                } else if (c == ']') {
                    pos = list.size();
                }else{
                    list.add(pos++, c);
                }
            }

            StringBuilder sb = new StringBuilder();
            for (char c : list) {
                sb.append(c);
            }
            System.out.println(sb.toString());

        }
    }
}
