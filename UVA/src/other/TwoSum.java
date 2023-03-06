package other;

/**
 * @author Lycoyas
 * @create 2022-09-21 18:53
 */
public class TwoSum {



    private static int count = 1;
    public static void d(){
        System.out.println(count++);
        d();
    }

    public static void main(String[] args) {
        d();
    }

}
