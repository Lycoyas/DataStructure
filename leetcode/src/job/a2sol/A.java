package job.a2sol;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-10-18 10:55
 */
public class A {

    public static void main(String[] args) {
        A a2Q2 = new A();
        System.out.println(a2Q2.programB(10));

        String str = "CSSSCCCSSCCSSSC";
        char[] chars = str.toCharArray();
        a2Q2.ProgramA(chars);
        System.out.println(Arrays.toString(chars));
    }

    void ProgramA(char[] lst) {
        int p1=0;
        int p2 = 0;

        while (p1 < lst.length) {
            if (lst[p1] == 'C') {
                char temp = lst[p1];
                lst[p1] = lst[p2];
                lst[p2] = temp;
                p2 = p2 + 1;
            }
            p1 = p1 + 1;
        }
    }
    float programB(int n) {
        float r = 1.0f;
        int k=1;
        while (k < n) {
            r = r * k / (k + 1);
            k = k + 1;
        }
        return r;
    }

}
