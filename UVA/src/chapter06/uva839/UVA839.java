package chapter06.uva839;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-09-21 22:09
 */
public class UVA839 {

    static class FastReader{
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st=new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt(){return Integer.parseInt(next());}

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
    static int weight=0;

    public static void main(String[] args) {

        int n = fr.nextInt();
        while (n-->0) {
            fr.nextLine();
            weight = 0;
            Node root = new Node(0, 0);
            if(solution(root)){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
            if (n > 0) {
                System.out.println();
            }
        }

    }

    public static boolean solution(Node root) {
        int w1, d1, w2, d2;

        w1 = fr.nextInt();
        d1 = fr.nextInt();
        w2 = fr.nextInt();
        d2 = fr.nextInt();

        Node left = new Node(w1, d1);
        Node right = new Node(w2, d2);

        boolean bl = true;
        boolean br = true;
        if(left.w==0){
            bl = solution(left);
        }
        if (right.w == 0) {
            br = solution(right);
        }

        root.w = left.w + right.w;
        return bl && br && (left.w * left.d == right.w * right.d);

    }

     static class Node{
        int w;
        int d;

        public Node(int w, int d) {
            this.w=w;
            this.d = d;
        }

        Node left;
        Node right;
    }
}

