package lc202211.lc1668;

/**
 * @author Lycoyas
 * @create 2022-11-03 16:01
 */
public class Solution {
    public int maxRepeating(String sequence, String word) {

        int result=0;
        int index = sequence.indexOf(word);

        if (index == -1) {
            return 0;
        }else{
            int count=1;
            while (index != -1) {

                int last=index;
                index = sequence.indexOf(word, last +word.length());
                if(index==last+word.length()){
                    count++;
                }else{
                    index = sequence.indexOf(word, last +1);
                    result = Math.max(result, count);
                    count = 1;
                }

            }

        }
        return result;

    }
}
