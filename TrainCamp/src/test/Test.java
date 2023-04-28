package test;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Lycoyas
 * @create 2023-04-25 13:28
 * @description
 */
public class Test {

    public static void main(String[] args) {
        removeDuplicateLetters("bcabc");
    }

    public static String removeDuplicateLetters(String s) {

        Deque<Character> stack=new ArrayDeque<>();

        int[] count=new int[26];
        boolean[] inStack=new boolean[26];

        for(char c:s.toCharArray()){
            count[c-'a']++;
        }

        StringBuilder sb=new StringBuilder();

        for(char c:s.toCharArray()){

            count[c-'a']--;

            if(inStack[c-'a']){
                continue;
            }

            while(!stack.isEmpty()&&stack.getLast()>c){
                if(count[stack.getLast()-'a']==0){
                    break;
                }
                inStack[stack.removeLast()-'a']=false;
            }
            stack.addLast(c);
            inStack[c-'a']=true;
        }

        while(!stack.isEmpty()){
            sb.append(stack.removeFirst());
        }

        return sb.toString();

    }
}
