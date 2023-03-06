package chapter06.uva122;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author Lycoyas
 * @create 2022-09-20 17:12
 */
public class UVA122 {

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

    static class Node{
        boolean have_value;//是否被赋值过
        int value;
        Node left;
        Node right;
    }

    static boolean failed=false;

    public static void main(String[] args) {

        FastReader fr = new FastReader();

        Node root = new Node();
        while (fr.hasNext()) {

            String str=fr.next();

            if(failed){
                while(!"()".equals(str)){
                    str = fr.next();
                }
                System.out.println("not complete");
                failed=false;
                root = new Node();
                continue;
            }

            if ("()".equals(str)) {
                //层次遍历打印
                List<Integer> res = new ArrayList<>();
                boolean success = bfs(root, res);
                if (success) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < res.size(); i++) {
                        sb.append(res.get(i));
                        if (i != res.size() - 1) {
                            sb.append(" ");
                        }
                    }
                    System.out.println(sb.toString());
                }else{
                    System.out.println("not complete");
                }
                root = new Node();
                continue;
            }

            String[] split = str.split(",");
            int value = Integer.parseInt(split[0].substring(1, split[0].length()));
            String path = split[1].substring(0, split[1].length() - 1);
            addNode(value, str, root);

        }



    }

    //加结点的过程中失败返回false
    static void addNode(int value, String str, Node root) {

        Node node=root;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'L') {
                if (node.left == null) {
                    node.left = new Node();
                }
                node = node.left;
            } else if (str.charAt(i) == 'R') {
                if (node.right == null) {
                    node.right = new Node();
                }
                node = node.right;
            }
        }
        if(node.have_value) failed=true;
        node.value = value;
        node.have_value = true;
    }

    static boolean bfs(Node root, List res) {

        Deque<Node> queue = new ArrayDeque<>();
        queue.addLast(root);

        while (!queue.isEmpty()) {

            Node node = queue.removeFirst();
            if(!node.have_value) return false;
            res.add(node.value);

            if(node.left!=null) queue.addLast(node.left);
            if(node.right!=null) queue.addLast(node.right);

        }
        return true;

    }




}
