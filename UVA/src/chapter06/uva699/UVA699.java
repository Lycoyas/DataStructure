package chapter06.uva699;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-09-22 11:15
 */
public class UVA699 {

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt(){return Integer.parseInt(next());}

        String nextLine() {
            String str = "";
            if (st.hasMoreTokens()) {
                str = st.nextToken("\n");
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
            if (st == null || !st.hasMoreTokens()) {
                try {
                    String str = br.readLine();
                    if (str == null) {
                        return false;
                    }
                    st = new StringTokenizer(str);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return true;
        }

    }

    static FastReader fr = new FastReader();
    static int sum[];
    public static void main(String[] args) {
        int kase = 0;

        while (fr.hasNext()) {
            sum = new int[100005];

            int value = fr.nextInt();
            if(value==-1) break;

            int pos = sum.length / 2;
            sum[pos] = value;
            build(pos - 1);
            build(pos + 1);

            int p=0;
            while(sum[p]==0) p++;
            System.out.printf("Case "+ ++kase+":\n"+sum[p++]);
            while (sum[p] != 0) {
                System.out.print(" "+sum[p++]);
            }
            System.out.printf("\n\n");
        }
    }



    static void build(int p) {
        int value = fr.nextInt();
        if(value==-1) return;
        sum[p] += value;
        build(p - 1);
        build(p + 1);
    }

}
