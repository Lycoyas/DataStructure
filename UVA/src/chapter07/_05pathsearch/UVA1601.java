package chapter07._05pathsearch;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-10-15 14:58
 */
public class UVA1601 {
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

    static class FastWriter implements Closeable {
        private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        public void print(Object object) {
            try {
                writer.write(object.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void println(Object object) {
            try {
                writer.write(object.toString());
                writer.write(System.lineSeparator());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public void println() {
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

    static int w, h, n;
    static int[] start = new int[3];//初始状态
    static int[] target = new int[3];//目标状态
    static char[][] dataset = new char[20][20];
    static int[][] G = new int[200][5];
    //static boolean[][][] vis = new boolean[200][200][200];
    static int[][][] dist1 = new int[200][200][200];
    static int[][][] dist2 = new int[200][200][200];
    static int[] deg = new int[200];//出度，最大为5

    static int dx[] = new int[]{0, -1, 1, 0, 0};
    static int dy[] = new int[]{0, 0, 0, -1, 1};

    public static void main(String[] args) {

        while (true) {
            w = fr.nextInt();
            h = fr.nextInt();
            n = fr.nextInt();
            if (w == 0 && h == 0 && n == 0) {
                return ;
            }
            //输入dataset
            int cnt=0;
            int[] x = new int[200];
            int[] y = new int[200];
            int[][] id = new int[20][20];//对应位置的编号
            deg = new int[200];
            for (int i = 0; i < h; i++) {
                String str = fr.nextLine();
                for (int j = 0; j < w; j++) {
                    dataset[i][j] = str.charAt(j);
                    if (dataset[i][j] != '#') {
                        x[cnt]=i;
                        y[cnt]=j;
                        id[i][j] = cnt;
                        if (dataset[i][j] >= 'a' && dataset[i][j] <= 'z') {
                            start[dataset[i][j] - 'a'] = cnt;
                        }else if (dataset[i][j] >= 'A' && dataset[i][j] <= 'Z') {
                            target[dataset[i][j] - 'A'] = cnt;
                        }
                        cnt++;
                    }
                }
            }

            //建立图
            for (int i = 0; i < cnt; i++) {
                for (int j = 0; j < 5; j++) {
                    int nx = x[i] + dx[j];
                    int ny = y[i] + dy[j];
                    if (dataset[nx][ny] != '#') {
                        G[i][deg[i]++] = id[nx][ny];
                    }
                }
            }

            if (n <= 2) {
                deg[cnt]=1;G[cnt][0]=cnt; start[2]=target[2]=cnt++;
            }
            if (n <= 1) {
                deg[cnt]=1;G[cnt][0]=cnt; start[1]=target[1]=cnt++;
            }

            System.out.println(bfs());

        }


    }

    static int mm(int a,int b,int c){return (a<<16)|(b<<8)|c;}
    static boolean conflict(int a, int b, int a2, int b2){
        return ((a2 == b2) || (a == b2 && b == a2));
    }
    static int bfs() {

        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 200; j++) {
                Arrays.fill(dist1[i][j], -1);
                Arrays.fill(dist2[i][j], -1);
            }
        }

        Deque<Integer> q1=new ArrayDeque<>();//q2=new ArrayDeque<>();
        q1.addLast(mm(start[0], start[1], start[2]));
        dist1[start[0]][start[1]][start[2]] = 0;
        //q2.addLast(mm(target[0], target[1],target[2]));

        while (!q1.isEmpty()) {

            int u = q1.removeFirst();
            int a = (u >> 16) & 0xff, b = (u >> 8) & 0xff, c = u & 0xff;

            if(a == target[0] && b == target[1] && c == target[2]) return dist1[a][b][c];

            for (int i = 0; i < deg[a]; i++) {
                int a2 = G[a][i];
                for (int j = 0; j < deg[b]; j++) {
                    int b2 = G[b][j];
                    if(conflict(a, b, a2, b2))  continue;
                    for(int k = 0; k < deg[c]; k++) {
                        int c2 = G[c][k];
                        if(conflict(a, c, a2, c2) || conflict(b, c, b2, c2)) continue;
                        if(dist1[a2][b2][c2] == -1) {    //等于-1说明没有访问过该状态，就要压入队列
                            dist1[a2][b2][c2] = dist1[a][b][c] + 1;
                            q1.addLast(mm(a2, b2, c2));
                        }
                    }

                }

            }

        }
        return -1;

    }


}
