package lc202211.lc1620;

/**
 * @author Lycoyas
 * @create 2022-11-02 18:39
 */
public class Solution {
    public int[] bestCoordinate(int[][] towers, int radius) {

        int[][] g = new int[110][110];

        int x=0, y = 0, val = 0;

        for (int[] t : towers) {

            int a = t[0], b = t[1], q = t[2];

            for (int i = Math.max(0, a - radius); i <= a + radius; i++) {

                for (int j = Math.max(0, b - radius); j <= b + radius; j++) {

                    double d = Math.sqrt((a - i) * (a - i) + (b - j) * (b - j));
                    if(d>radius) continue;

                    g[i][j] += q / (1 + d);
                    if (g[i][j] > val) {
                        x=i;y=j;
                        val = g[i][j];
                    } else if (g[i][j] == val && (i < x || (i == x && j < y))) {
                        x=i;
                        y = j;
                    }

                }

            }

        }
        return new int[]{x, y};

    }
}
