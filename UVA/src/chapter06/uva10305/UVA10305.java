package chapter06.uva10305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Lycoyas
 * @create 2022-09-24 11:38
 */
public class UVA10305 {
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
    public static void main(String[] args) {




        while (true) {

            int n = fr.nextInt();//任务数
            int m=fr.nextInt();//m行数据
            if (n == 0 && m == 0) break;


            List<Integer>[] G = new ArrayList[n+1];
            for (int i = 0; i < G.length; i++) {
                G[i] = new ArrayList<>();
            }
            int[] in = new int[n + 1];
            boolean[] visited = new boolean[n + 1];

            for (int k = 0; k < m; k++) {
                int i = fr.nextInt();
                int j = fr.nextInt();
                G[i].add(j);
                in[j]++;
            }

            Deque<Integer> q = new ArrayDeque<>();

            int t;
            for (int i = 1; i <= n; i++) {
                if(in[i]==0) q.addLast(i);
            }

            while (!q.isEmpty()) {
                t = q.removeFirst();
                System.out.printf("%d ", t);
                for (int i = 0; i < G[t].size(); i++) {
                    in[G[t].get(i)]--;
                    if (in[G[t].get(i)] == 0) {
                        q.addLast(G[t].get(i));
                    }
                }
            }
            System.out.println();
        }

    }
}
