package dscourse.myhashmap;

import java.util.HashMap;

/**
 * @author Lycoyas
 * @create 2023-03-26 11:39
 * @description
 */
public class MyHashSet<K> {

    // val 占位符
    private static final Object PRESENT = new Object();
    // 底层 HashMap
    private final HashMap<K, Object> map = new HashMap<>();

    public boolean add(K k) {
        return map.put(k, PRESENT) == null;
    }

    public boolean remove(K k) {
        return map.remove(k) == PRESENT;
    }

    public boolean contains(K k) {
        return map.containsKey(k);
    }

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

}
