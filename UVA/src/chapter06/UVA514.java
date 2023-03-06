package chapter06;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * @author Lycoyas
 * @create 2022-09-15 11:13
 */
public class UVA514 {
    static int[] target = new int[1010];
    public static void main(String[] args) {


        Scanner scan = new Scanner(System.in);
        int N=scan.nextInt();
        while (N != 0) {

            while (true) {
                target[1] = scan.nextInt();
                if (target[1] == 0) {
                    System.out.println();
                    break;
                }
                for (int i = 2; i <= N; i++) {
                    target[i] = scan.nextInt();
                }

                boolean ok = check(N);
                System.out.println(ok ? "Yes" : "No");
            }
            N = scan.nextInt();


        }

    }

    static boolean check(int N) {

        Deque<Integer> stack = new ArrayDeque();
        int A = 1, B = 1;
        boolean ok = true;
        while (B <= N) {
            if (A == target[B]) {
                A++;B++;
            } else if (!stack.isEmpty() && stack.getLast() == target[B]) {
                stack.removeLast();
                B++;
            } else if (A <= N) {
                stack.offerLast(A++);
            }else{
                ok=false;break;
            }
        }

        return ok;

    }
}
