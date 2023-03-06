package lc202209.lc777;

/**
 * @author Lycoyas
 * @create 2022-10-02 08:54
 */
public class Solution {
    public boolean canTransform(String start, String end) {

        int n = start.length();
        int i = 0, j = 0;

        while (i < n && j < n) {

            while(i<n&&start.charAt(i)=='X') i++;
            while(j<n&&end.charAt(j)=='X') j++;
            if(i>=n||j>=n) break;
            if (start.charAt(i) != end.charAt(j)) {
                return false;
            } else if (start.charAt(i) == 'R' && i > j) {
                return false;
            } else if (start.charAt(i) == 'L' && i < j) {
                return false;
            }
            i++;j++;
        }


            while (i < n) {
                if(start.charAt(i++)!='X') return false;
            }
            while (j < n) {
                if(end.charAt(j++)!='X') return false;
            }



        return true;

    }
}
