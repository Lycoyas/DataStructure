package chapter06.uva297;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-09-22 15:06
 */
public class UVA297 {

    static class FastReader{

        BufferedReader br;
        StringTokenizer stringTokenizer;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
                try {
                    stringTokenizer = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return stringTokenizer.nextToken();
        }

        int nextInt(){
            return Integer.parseInt(next());
        }

        String nextLine() {

            String str = "";
            if (stringTokenizer.hasMoreTokens()) {
                str = stringTokenizer.nextToken("\n");
            }else{
                try {
                    str = br.readLine();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return str;
        }

        boolean hasNext() {
            if (stringTokenizer == null || !stringTokenizer.hasMoreTokens()) {
                String str = "";
                try {
                    str = br.readLine();
                    if (str == null) {
                        return false;
                    }
                    stringTokenizer = new StringTokenizer(str);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return stringTokenizer.hasMoreTokens();
        }

    }

    static int index=0;
    public static void main(String[] args) {
        FastReader fr = new FastReader();
        int n = fr.nextInt();
        while (n-- > 0) {

            String s1 = fr.nextLine();
            String s2 = fr.nextLine();

            index=0;
            int[][] buffer = new int[32][32];
            int c1=draw(s1.toCharArray(),  buffer, 0, 0, 32);
            index=0;
            int c2=draw(s2.toCharArray(),  buffer, 0, 0, 32);
            int sum = c1 + c2;
            System.out.printf("There are %d black pixels.\n",sum);

        }



    }


    static int draw(char[] s, int[][] buffer, int row, int column, int length) {

        if(index==s.length) return 0;
        int count=0;
        char ch = s[index++];
        if (ch == 'p') {
            count+=draw(s, buffer, row, column + length / 2, length / 2);
            count+=draw(s,  buffer, row, column , length / 2);
            count+=draw(s, buffer, row+length/2, column , length / 2);
            count+=draw(s, buffer, row+length/2, column + length / 2, length / 2);
        } else if (ch == 'f') {
            for (int i = row; i < row + length; i++) {
                for (int j = column; j < column + length; j++) {
                    if (buffer[i][j] == 0) {
                        buffer[i][j]=1;
                        count++;
                    }
                }
            }
        }
        return count;
    }

}
