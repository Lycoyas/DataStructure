package lc202210.lc1694;

/**
 * @author Lycoyas
 * @create 2022-10-01 17:32
 */
public class Solution {

    public String reformatNumber(String number) {
        StringBuilder sb = new StringBuilder();
        for (char ch : number.toCharArray()) {
            if (ch != '-' && ch != ' ') {
                sb.append(ch);
            }
        }
        StringBuilder res = new StringBuilder();
        int i=0;
        int flag = 0;
        if (sb.length() > 4) {
            flag = 1;
            for (i = 0; i < sb.length(); i+=3) {

                if(i+4>=sb.length())break;
                if(i>0) res.append("-");
                res.append(sb.substring( i, i + 3));


            }
        }

        if (flag == 1) {
            res.append("-");

        }

        if (sb.length() - i == 4) {
            res.append(sb.substring(i,i+2));
            res.append("-");
            res.append(sb.substring(i+2,i+4));
        } else if (sb.length() - i == 3||sb.length() - i == 2) {
            res.append(sb.substring(i, sb.length()));
        }
        return res.toString();



    }

}
