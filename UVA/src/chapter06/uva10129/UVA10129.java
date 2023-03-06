package chapter06.uva10129;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-09-24 16:11
 */
public class UVA10129 {
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

    static int maxn = 30;
    static int[][] G;
    static int[]in,out, visited;


    public static void main(String[] args) {


        int n = fr.nextInt();

        while (n-- > 0) {
            if (solve()) {
                System.out.println("Ordering is possible.");
            }else{
                System.out.println("The door cannot be opened.");
            }

        }





    }

    static void dfs(int u) {

        visited[u] = 0;
        for (int i = 0; i < 26; i++) {
            if (G[u][i]>0 && visited[i] == 1) {
                dfs(i);
            }
        }

    }

    static boolean solve() {

        int n = fr.nextInt();

        G = new int[maxn][maxn];
        in = new int[maxn];
        out = new int[maxn];
        visited = new int[maxn];

        int u=0,v=0,inM=0,outM=0;
        for (int i = 0; i < n; i++) {
            String word = fr.next();
            u= word.charAt(0) - 'a';
            v = word.charAt(word.length() - 1) - 'a';
            G[u][v]++;
            G[v][u]++;
            in[v]++;
            out[u]++;
            visited[u]=1;
            visited[v]=1;
        }

        dfs(u);
        for (int i = 0; i < 26; i++) {
            if(visited[i]==1) return false;
            if (in[i] != out[i]) {
                if(in[i]-out[i]==1){
                    inM++;
                } else if (out[i] - in[i] == 1) {
                    outM++;
                }else{
                    return false;
                }
            }
        }

        if ((inM == 1 && outM == 1) || (inM == 0 && outM == 0)) {
            return true;
        }
        return false;
    }



}
