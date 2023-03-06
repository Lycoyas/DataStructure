package chapter06;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @author Lycoyas
 * @create 2022-09-15 22:33
 */
public class UVA442 {
    static Matrix[] matrices=new Matrix[26];
    static{
        for (int i = 0; i < matrices.length; i++) {
            matrices[i] = new Matrix();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();

        for (int i = 0; i < N; i++) {
            String name = scan.next();
            int k = name.charAt(0) - 'A';
            int m = scan.nextInt();
            int n = scan.nextInt();
            matrices[k].m = m;
            matrices[k].n = n;
        }

        while (scan.hasNext()) {
            String str = scan.next();
            int res = check(str);
            System.out.println(res == -1 ? "error" : res);
        }


    }

    static int check(String str) {

        Deque<Matrix> stack = new ArrayDeque<>();
        int res=0;

        for (char ch : str.toCharArray()) {

            if (ch >= 'A' && ch <= 'Z') {
                stack.offerLast(matrices[ch - 'A']);
            } else if (ch == ')') {

                Matrix m2 = stack.removeLast();
                Matrix m1 = stack.removeLast();
                if (m1.n != m2.m) {
                    return -1;
                }
                res += m1.m * m1.n * m2.n;
                stack.offerLast(new Matrix(m1.m, m2.n));
            }

        }
        return res;
    }


}

class Matrix{
    int m;
    int n;

    public Matrix() {

    }

    public Matrix(int m, int n) {
        this.m=m;
        this.n = n;
    }

}
