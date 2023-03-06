package basic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * @author Lycoyas
 * @create 2023-02-13 11:48
 */
public class lc380 {
    List<Integer> nums;
    HashMap<Integer,Integer> valToIndex;
    public lc380() {
        nums = new ArrayList<>();
        valToIndex = new HashMap<>();

    }

    public boolean insert(int val) {
        if (valToIndex.containsKey(val)) {
            return false;
        }
        valToIndex.put(val, nums.size());
        nums.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!valToIndex.containsKey(val)) {
            return false;
        }

        int index = valToIndex.get(val);
        valToIndex.put(nums.get(nums.size() - 1), index);
        nums.set(index, nums.get(nums.size() - 1));
        nums.remove(nums.size() - 1);
        valToIndex.remove(val);
        return true;

    }

    public int getRandom() {
        Random random = new Random();
        return nums.get(random.nextInt(nums.size()));
    }
}
