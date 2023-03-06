package chapter06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-09-16 22:41
 */
public class UVA12657 {

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));

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
            return true;
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

    }
    public static void main(String[] args){


        FastReader fr = new FastReader();

        int kase=0;



        while (fr.hasNext()) {


            int n = fr.nextInt();
            int m = fr.nextInt();

            int[] left = new int[n + 1];
            int[] right = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                left[i] = i - 1;
                right[i] = (i + 1) % (n + 1);
            }
            right[0]=1;
            left[0]=n;
            int op, X, Y;
            boolean inv = false;


            for (int i = 0; i < m; i++) {

                op = fr.nextInt();
                if(op==4) inv = !inv;
                else{
                    X = fr.nextInt();
                    Y = fr.nextInt();
                    if(op==3&&right[Y]==X){
                        int temp=X;
                        X=Y;
                        Y = temp;
                    }
                    if(op!=3&&inv) op = 3 - op;
                    if(op==1&&left[Y]==X) continue;
                    if(op==2&&right[Y]==X) continue;
                    int LX = left[X], RX = right[X], LY = left[Y], RY = right[Y];
                    if (op == 1) {
                        link(LX, RX,left,right);
                        link(LY, X,left,right);
                        link(X, Y,left,right);
                    } else if (op == 2) {

                        link(LX, RX,left,right);
                        link(Y, X,left,right);
                        link(X, RY,left,right);

                    } else if (op == 3) {
                        if (right[X] == Y) {
                            link(LX, Y,left,right);
                            link(Y, X,left,right);
                            link(X, RY,left,right);
                        }else{
                            link(LX, Y,left,right);
                            link(Y, RX,left,right);
                            link(LY, X,left,right);
                            link(X, RY,left,right);
                        }
                    }

                }
            }

            int b=0;
            long ans=0;
            for (int i = 1; i <= n; i++) {
                b = right[b];
                if (i % 2 == 1) {
                    ans += b;
                }
            }
            if (inv && n % 2 == 0) {
                ans = (long)(1 + n) * (n / 2) - ans;
            }



            System.out.printf("Case %d: %d\n", ++kase ,ans);

        }





    }

    static void link(int L, int R,int[]left,int[]right) {
        left[R]=L;
        right[L] = R;
    }

}
