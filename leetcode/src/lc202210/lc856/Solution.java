package lc202210.lc856;

import java.util.ArrayDeque;
import java.util.Deque;

/**栈
 * @author Lycoyas
 * @create 2022-10-09 15:48
 */
public class Solution {

    public int scoreOfParentheses(String s) {

        Deque<Character> stack = new ArrayDeque();
        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {
                stack.addLast('(');
            }else{
                char c = stack.removeLast();
                if (c == '(') {
                    stack.addLast('1');
                }else{

                    int sum = c - '0';
                    while((c=stack.removeLast())!='(') sum += (c - '0');
                    stack.addLast((char)((sum << 1) + '0'));
                }

            }

        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.removeLast() - '0';
        }
        return res;
    }

}
