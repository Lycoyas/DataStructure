package chapter06.uva816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Lycoyas
 * @create 2022-09-23 18:49
 */
public class UVA816 {

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

    static String dirs = "NESW";
    static String turns = "FLR";
    static int dr[] = {-1, 0, 1, 0};
    static int dc[] = {0, 1, 0, -1};

    static int dir_id(char c) {
        return dirs.indexOf(c);
    }

    static int turn_id(char c) {
        return turns.indexOf(c);
    }

    static class Node{
        int r;
        int c;
        int dir;

        public Node(int r, int c, int dir) {
            this.r=r;
            this.c=c;
            this.dir = dir;
        }
    }

    static Node walk(Node node, int turn) {

        int dir = node.dir;
        if (turn == 1) {
            dir = (dir + 3) % 4;
        }
        if (turn == 2) {
            dir = (dir + 1) % 4;
        }
        return new Node(node.r + dr[dir], node.c + dc[dir], dir);
    }

    static FastReader fr = new FastReader();

    public static void main(String[] args) {

        while (true) {

            String name = fr.next();
            if ("END".equals(name)) {
                break;
            }

            //当前状态是否可以转到这个方向
            boolean[][][][] hasEdge = new boolean[10][10][4][3];
            //到初始点的最短距离
            int[][][] d = new int[10][10][4];
            //给d赋值-1，判断是否已经走过了
            for (int i = 0; i < d.length; i++) {
                for (int j = 0; j < d[0].length; j++) {
                    d[i][j] = new int[]{-1, -1, -1, -1};
                }
            }
            //路径父结点
            Node[][][] p = new Node[10][10][4];

            int r0 = fr.nextInt();
            int c0 = fr.nextInt();
            int dir = dir_id(fr.next().charAt(0));
            int desR = fr.nextInt();
            int desC = fr.nextInt();

            //r,c转换后的初始状态
            int initR = r0 + dr[dir];
            int initC = c0 + dc[dir];

            //根据输入初始化hasEdge
            while (true) {
                int r1 = fr.nextInt();
                if(r1==0) break;
                int c1 = fr.nextInt();
                while (true) {
                    String str = fr.next();
                    if ("*".equals(str)) break;

                    int tempDir = dir_id(str.charAt(0));
                    for (int i = 1; i < str.length(); i++) {
                        hasEdge[r1][c1][tempDir][turn_id(str.charAt(i))]=true;
                    }
                }
            }

            //打印迷宫名字
            System.out.println(name);
            solve(initR,initC,dir,d,p,hasEdge,desR,desC,r0,c0);


        }

    }

    //判断是否越界
    static boolean inside(int r, int c) {
        return r > 0 && c > 0 && r < 10 && c < 10;
    }

    static void solve(int r, int c, int dir, int[][][] d, Node[][][] p, boolean[][][][] hasEdge,int desR,int desC,int r0,int c0) {

        Deque<Node> q = new ArrayDeque<>();
        Node u = new Node(r, c, dir);
        q.addLast(u);
        //入口为0
        d[r][c][dir] = 0;
        while (!q.isEmpty()) {

            Node node = q.removeFirst();
            //是否到终点了
            if (node.r == desR && node.c == desC) {
                print(node,p,d,r0,c0,dir);
                return;
            }

            for (int i = 0; i < 3; i++) {
                if (hasEdge[node.r][node.c][node.dir][i]){
                    Node v = walk(node, i);
                    if (inside(v.r, v.c) && d[v.r][v.c][v.dir] < 0) {
                        d[v.r][v.c][v.dir] = d[node.r][node.c][node.dir] + 1;
                        p[v.r][v.c][v.dir] = node;
                        q.addLast(v);
                    }
                }
            }
        }
        System.out.println("  No Solution Possible");

    }

    static void print(Node u, Node[][][] p, int[][][] d,int r,int c,int dir) {

        List<Node> nodes = new ArrayList<>();
        for (; ; ) {
            nodes.add(u);
            if (d[u.r][u.c][u.dir]==0) break;
            u = p[u.r][u.c][u.dir];
        }
        nodes.add(new Node(r, c, dir));

        int cnt=0;

        for (int i = nodes.size() - 1; i >= 0; i--) {
            if (cnt % 10 == 0) {
                System.out.print(" ");
            }
            System.out.printf(" (%d,%d)", nodes.get(i).r, nodes.get(i).c);
            if (++cnt % 10 == 0) {
                System.out.println();
            }
        }

        if (nodes.size() % 10 != 0) {
            System.out.println();
        }

    }

}
