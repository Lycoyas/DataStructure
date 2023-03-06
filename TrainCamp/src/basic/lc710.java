package basic;

import java.util.HashMap;
import java.util.Random;

/**
 * @author Lycoyas
 * @create 2023-02-13 12:23
 */
public class lc710 {

    int sz;
    HashMap<Integer,Integer> blackToWhite;
    public lc710(int n, int[] blacklist) {

        sz = n - blacklist.length;
        blackToWhite = new HashMap<>();

        for (int black : blacklist) {
            blackToWhite.put(black, 666);
        }

        int last=n-1;

        for (int black : blacklist) {
            if (blackToWhite.containsKey(black)) {

                if (black >= sz) {
                    continue;
                }
                while (blackToWhite.containsKey(last)) {
                    last--;
                }

                blackToWhite.put(black, last);
            }
            last--;
        }

    }

    public int pick() {

        Random random = new Random();
        int res = random.nextInt(sz);

        if (blackToWhite.containsKey(res)) {
            res = blackToWhite.get(res);
        }

        return res;

    }
}
