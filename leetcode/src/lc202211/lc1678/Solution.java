package lc202211.lc1678;

/**
 * @author Lycoyas
 * @create 2022-11-06 15:26
 */
public class Solution {
    public String interpret(String command) {

        StringBuilder sb = new StringBuilder();


        for (int i = 0; i < command.length(); i++) {

            if (command.charAt(i) == 'G') {
                sb.append("G");
            } else if (command.charAt(i) == '(') {
                int temp=i;
                while (command.charAt(++i) != ')') {

                }
                if (i - temp == 1) {
                    sb.append("o");
                }else{
                    sb.append("al");
                }

            }

        }
        return sb.toString();

    }
}
