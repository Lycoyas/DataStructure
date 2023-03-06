package num_bit_dp.lc2376;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-09-25 15:43
 */
public class Solution {

    char s[];
    int dp[][]; //记录当前选择顺位为i，已选状态为mask时，构造第i位及后面位的合法方案数

    public int countSpecialNumbers(int n) {
        s=Integer.toString(n).toCharArray();
        int m=s.length;
        dp=new int[m][1<<10];
        for(int i=0;i<m;i++){
            Arrays.fill(dp[i],-1);
        }

        return f(0,0,true,false);
    }

    /**
     *
     * @param i 第i位要选的数字
     * @param mask 表示前面选过的数字集合，换句话说，第 i 位要选的数字不能在 mask 中
     * @param isLimit 表示当前是否受到了n的约束。若为真，则第i位填入的数字至多为s[i],否则可以是9。
     *                如果受到约束的情况下填了s[i],那么后续填入的数字仍会受到n的约束
     * @param isNum 表示i前面的数位是否填了数字。若为假，则当前位可以跳过（不填数字），或者要填入的数字至少为1；
     *              若为真，则必须填数组，且要填入的数字可以从0开始
     * @return
     */
    int f(int i,int mask,boolean isLimit,boolean isNum){

        if(i==s.length) return isNum ? 1 : 0;
        if(!isLimit&&isNum&&dp[i][mask]!=-1) return dp[i][mask];

        int res=0;
        //跳过当前位
        if (!isNum) res = f(i + 1, mask, false, false);

        for (int d = isNum ? 0 : 1, up = isLimit ? s[i] - '0' : 9; d <= up; d++) {

            if(((mask>>d)&1)==0) {//d不在mask中

                res += f(i + 1, mask | (1 << d), isLimit && d == up, true);

            }

        }



        if(!isLimit&&isNum) dp[i][mask] = res;

        return res;

    }

}
