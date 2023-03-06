package chapter07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-09-29 17:29
 */
public class UVA725 {

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt(){return Integer.parseInt(next());}
        long nextLong(){return Long.parseLong(next());}
        double nextDouble(){return Double.parseDouble(next());}

        String nextLine() {
            String str = "";
            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                }else{
                    str = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }

        boolean hasNext() {
            if (st == null || !st.hasMoreElements()) {
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
            return st.hasMoreElements();
        }
    }

    static FastReader fr = new FastReader();

    static int n;
    static boolean flag=false;
    public static void main(String[] args) {

        int cnt=0;
        while (true) {
            n = fr.nextInt();
            if(n==0) {
                return;
            }
            flag=false;
            cnt++;
            if (cnt > 1) {
                System.out.println();
            }
            solve(n);
        }

    }

    static void solve(int n) {

        for (int i = 1; i <= 99999; i++) {

            int abcde = i * n;


            String str1 = abcde + "";
            String str2 = i + "";
            int len1=(str1).length();
            int len2 = (str2).length();
            if(len1+len2>10) break;
            if(len1>5||len2>5||len1+len2<9)continue;

            if(isUnique(str1,str2)){
                flag = true;
            }

        }
        if (!flag) {
            System.out.println("There are no solutions for "+n+".");
        }

    }

    static boolean isUnique(String a, String b) {
        int[] num = new int[10];
        Arrays.fill(num, 1);
        if (a.length()==4) {
            a = "0" + a;
        }
        if (b.length()==4) {
            b = "0" + b;
        }
        if(a.length()>b.length()) System.out.println(a+" "+a.length());
        for (int i = 0; i < a.length(); i++) {
            int numA = a.charAt(i)-'0';
            int numB=b.charAt(i)-'0';
            num[numA]--;
            if(num[numA]<0){
                return false;
            }
            num[numB]--;
            if(num[numB]<0) {
                return false;
            }
        }

        System.out.println(a + " / " + b + " = " + n);
        return true;
    }


}
