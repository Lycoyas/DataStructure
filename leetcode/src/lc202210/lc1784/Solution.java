package lc202210.lc1784;

/**
 * @author Lycoyas
 * @create 2022-10-03 0:00
 */
public class Solution {
    public static void main(String[] args) {


        new Solution().checkOnesSegment("1001");
    }
    public boolean checkOnesSegment(String s) {

        int sum=0;
        for (int i = 0; i < s.length();) {
            if (s.charAt(i) == '1') {
                while(++i<s.length()&&s.charAt(i)=='1'){}
                sum++;
            }else{
                i++;
            }
        }
        return sum<=1;

    }
}
