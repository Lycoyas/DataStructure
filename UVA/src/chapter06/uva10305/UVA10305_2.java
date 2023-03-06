package chapter06.uva10305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-09-24 12:18
 */
public class UVA10305_2 {
    static class FastReader {
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

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                if (st.hasMoreTokens()) {
                    str = st.nextToken("\n");
                } else {
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

    static int[]topo;
    static int[]visited;
    static int t;

    public static void main(String[] args) {

        while (true) {

            int n = fr.nextInt();//任务数
            int m = fr.nextInt();//m行数据
            if (n == 0 && m == 0) break;

            int[][] G = new int[n + 1][n + 1];

            for (int i = 0; i < m; i++) {
                int a = fr.nextInt();
                int b = fr.nextInt();
                G[a][b] = 1;
            }

            if (!topoSort(G)) {
                System.out.println("-1");
            }else{

                for (int i = 0; i < n; i++) {
                    System.out.printf("%d ", topo[i]);
                }
                System.out.println();

            }


        }
    }



    static boolean dfs(int u, int[][] G) {

        visited[u] = -1;

        for (int v = 1; v <= G.length - 1; v++) {

            if (G[u][v] == 1) {

                if (visited[v] == -1) {
                    return false;//有结点正在访问
                }
                if (visited[v] == 0) {
                    dfs(v, G);
                }
            }

        }
        visited[u]=1;
        topo[--t]=u;
        return true;

    }

    static boolean topoSort(int[][]G) {

        int n = G.length - 1;
        t = n;
        topo = new int[n];
        visited = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (visited[i] == 0) {
                if (!dfs(i, G)) {
                    return false;
                }
            }
        }
        return true;

    }
}
