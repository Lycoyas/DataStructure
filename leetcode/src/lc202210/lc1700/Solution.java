package lc202210.lc1700;

/**
 * @author Lycoyas
 * @create 2022-10-19 11:37
 */
public class Solution {

    public int countStudents(int[] students, int[] sandwiches) {

        int s0 = 0, s1 = 0;
        for (int i = 0; i < students.length; i++) {
            if (students[i] == 0) {
                s0++;
            }else{
                s1++;
            }
        }

        for (int i = 0; i < sandwiches.length; i++) {
            if (sandwiches[i] == 0 && s0 > 0) {
                s0--;
            } else if (sandwiches[i] == 1 && s1 > 0) {
                s1--;
            }else{
                break;
            }
        }

        return s0 + s1;


    }

}
