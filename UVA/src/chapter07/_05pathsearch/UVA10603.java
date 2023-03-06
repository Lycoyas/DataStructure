package chapter07._05pathsearch;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-10-13 16:32
 */
public class UVA10603 {

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

    static class FastWriter implements Closeable {
        private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        public void print(Object object) {
            try {
                writer.write(object.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void println(Object object)  {
            try {
                writer.write(object.toString());
                writer.write(System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        public void println()  {
            try {
                writer.write(System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        @Override
        public void close() {
            try {
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static FastReader fr = new FastReader();
    static FastWriter fw = new FastWriter();

    static class Node{
        public int[] v = new int[3];
        public int dist;

    }

    static int maxn = 205;
    static int[][] vis = new int[maxn][maxn];
    static int[] cap = new int[3];
    static int[] ans = new int[maxn];

    static void updateAns(Node u) {
        for (int i = 0; i < 3; i++) {
            int d = u.v[i];
            if (ans[d] < 0 || ans[d] > u.dist) {
                ans[d] = u.dist;
            }
        }
    }

    static void solve(int a, int b, int c, int d) {

        cap[0] = a;cap[1] = b;cap[2] = c;
        vis = new int[maxn][maxn];
        ans = new int[maxn];
        Arrays.fill(ans, -1);

        PriorityQueue<Node> q = new PriorityQueue<Node>((u,v)->u.dist-v.dist);

        Node start = new Node();
        start.dist=0;
        start.v[2] = c;
        q.offer(start);

        vis[0][0] = 1;

        while (!q.isEmpty()) {
            Node u = q.poll();
            updateAns(u);
            if(ans[d]>=0) break;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    //选择两个杯子倒水,i往j倒
                    if (i != j) {
                        //i杯子没水，j杯子已经满了
                        if(u.v[i]==0||u.v[j]==cap[j]) continue;
                        //计算倒水量
                        int amount = Math.min(cap[j], u.v[i] + u.v[j]) - u.v[j];
                        Node u2 = new Node();
                        for (int k = 0; k < 3; k++) {
                            u2.v[k] = u.v[k];
                        }
                        u2.dist = u.dist + amount;
                        u2.v[i] -= amount;
                        u2.v[j] += amount;
                        if (vis[u2.v[0]][u2.v[1]] == 0) {
                            vis[u2.v[0]][u2.v[1]] =1;
                            q.offer(u2);
                        }
                    }
                }
            }
        }

        while (d >= 0) {
            if (ans[d] >= 0) {
                fw.println(ans[d] + " " + d);
                return;
            }
            d--;
        }
    }

    public static void main(String[] args) {

        int T, a, b, c, d;
        T = fr.nextInt();
        while (T-->0) {

            a = fr.nextInt();
            b = fr.nextInt();
            c = fr.nextInt();
            d = fr.nextInt();
            solve(a, b, c, d);

        }
        fw.close();

    }

}
