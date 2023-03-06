package lc202211.lc895;

import java.util.HashMap;
import java.util.Stack;

/**
 * @author Lycoyas
 * @create 2022-11-30 15:04
 */
public class FreqStack {

    int maxFreq=0;
    HashMap<Integer, Integer> valtoFreq = new HashMap<>();
    HashMap<Integer, Stack<Integer>> freqToVals = new HashMap<>();

    public FreqStack() {

    }

    public void push(int val) {
        int freq = valtoFreq.getOrDefault(val, 0) + 1;
        valtoFreq.put(val, freq);
        freqToVals.putIfAbsent(freq, new Stack<>());
        freqToVals.get(freq).push(val);
        maxFreq = Math.max(maxFreq, freq);
    }

    public int pop() {

        Stack<Integer> vals = freqToVals.get(maxFreq);
        int v = vals.pop();
        int freq = valtoFreq.get(v) - 1;
        valtoFreq.put(v, freq);
        if (vals.isEmpty()) {
            maxFreq--;
        }
        return v;


    }

}
