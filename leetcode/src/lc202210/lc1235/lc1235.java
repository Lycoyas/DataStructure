package lc202210.lc1235;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-10-23 18:40
 */
public class lc1235 {

    class Work implements Comparable<Work>{

        int start;
        int end;
        int profit;

        public Work(int start, int end, int profit) {
            this.start = start;
            this.end = end;
            this.profit = profit;
        }

        public int compareTo(Work o) {
            return Integer.compare(this.end, o.end);
        }
    }

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        Work[] works = new Work[endTime.length+1];
        works[0] = new Work(0, 0, 0);
        for (int i = 1; i < works.length; i++) {

            works[i] = new Work(startTime[i-1], endTime[i-1], profit[i-1]);

        }
        Arrays.sort(works);

        int[] dp = new int[works.length];
        dp[0] = 0;

        for (int i = 1; i < works.length; i++) {

            int pre=0;
            for (int j = i; j > 0; j--) {

                if (works[j].end <= works[i].start) {
                    pre = j;break;
                }

            }
            dp[i] = Math.max(dp[i - 1], dp[pre] + works[i].profit);

        }
        return dp[works.length-1];

    }


}
