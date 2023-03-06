package lc202211.lc1106;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Lycoyas
 * @create 2022-11-05 11:40
 */
public class Solution {
    public boolean parseBoolExpr(String expression) {

        Deque<Integer> nums = new ArrayDeque<>(), ops = new ArrayDeque<>();
        int n = expression.length();
        for (int i = 0; i < n; i++) {

            char c = expression.charAt(i);
            if(c==',') continue;
            else if(c=='t'||c=='f') nums.addLast(c == 't' ? 1 : -1);
            else if(c=='|'||c=='&'||c=='!') ops.addLast((int)c);
            else if(c=='(') nums.addLast(0);
            else if (c == ')') {

                int cur=100;
                while (!nums.isEmpty() && nums.getLast() != 0) {

                    int top = nums.removeLast();
                    cur = cur == 100 ? top : calc(top, cur, ops.peekLast());

                }
                if(ops.peekLast()=='!') cur *= -1;
                nums.removeLast();
                ops.removeLast();
                nums.addLast(cur);

            }
        }
        return nums.getLast() == 1;

    }

    int calc(int a, int b, int op) {

        boolean ba = a == 1, bb = b == 1;
        boolean ans = op == (int) '|' ? ba | bb : ba & bb;
        return ans ? 1 : -1;

    }
}
