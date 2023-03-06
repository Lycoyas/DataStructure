package chapter06.uva548;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author Lycoyas
 * @create 2022-09-20 22:21
 */
public class UVA548 {
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

    static int minNode,minSum=Integer.MAX_VALUE;
    static int[] left = new int[10000 + 10];
    static int[] right = new int[10000 + 10];

    public static void main(String[] args) {

        FastReader fr = new FastReader();

        while (fr.hasNext()) {

            String line1 = fr.nextLine();
            String line2 = fr.nextLine();

            String[] s1 = line1.split(" ");
            String[] s2 = line2.split(" ");

            int[] inOrder = new int[s1.length];
            int[] postOrder = new int[s2.length];
            for (int i = 0; i < s1.length; i++) {
                inOrder[i] = Integer.parseInt(s1[i]);
                postOrder[i] = Integer.parseInt(s2[i]);
            }


            int root = build(inOrder, 0, inOrder.length - 1,
                    postOrder, 0, postOrder.length - 1);

            minSum = Integer.MAX_VALUE;

            dfs(root, 0);
            System.out.println(minNode);

        }



    }

    static int build(int[] inOrder, int inStart,int inEnd,
              int[] postOrder, int postStart,int postEnd) {

        if(inStart>inEnd) return 0;
        int root = postOrder[postEnd];

        int index=inStart;
        while(inOrder[index]!=root) index++;
        int leftSize = index - inStart;

        left[root]=build(inOrder,inStart,index-1,
                        postOrder,postStart,postStart+leftSize-1);
        right[root] = build(inOrder, index + 1, inEnd,
                postOrder, postStart + leftSize, postEnd - 1);

        return root;
    }


     static void dfs(int root, int sum) {
         sum+=root;
         if (left[root] == 0 && right[root] == 0) {

             if(sum<minSum||(sum==minSum&&root<minNode)){
                 minNode=root;
                 minSum = sum;
             }

         }

         if(left[root]!=0) dfs(left[root], sum);
         if(right[root]!=0) dfs(right[root], sum);
    }

}
