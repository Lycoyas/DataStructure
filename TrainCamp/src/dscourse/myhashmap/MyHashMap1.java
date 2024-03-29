package dscourse.myhashmap;

import java.util.Map;

/**
 * @author Lycoyas
 * @create 2023-03-24 11:45
 * @description
 */
public class MyHashMap1<K,V>{

    // 哈希表中存入的键值对个数
    private int size;
    // 底层存储链表的数组
    private Slot<K, V>[] table;
    // 底层数组的初始容量
    private static final int INIT_CAP = 4;

    public MyHashMap1() {
        this(INIT_CAP);
    }

    public MyHashMap1(int initCapacity) {
        size = 0;
        // 初始化哈希表
        table = (Slot<K, V>[]) new Slot[initCapacity];
        for (int i = 0; i < table.length; i++) {
            table[i] = new Slot<>();
        }
    }

    /***** 增/改 *****/

    // 添加 key -> val 键值对
    // 如果键 key 已存在，则将值修改为 val
    public V put(K key, V val) {
        if(key==null){
            throw new IllegalArgumentException("key is null");
        }

        if(size>=table.length*0.75){
            resize(2*table.length);
        }

        Slot<K,V> slot=table[hash(key)];

        if(!slot.containsKey(key)){
            size++;

        }

        return slot.put(key,val);

    }

    /***** 删 *****/

    // 删除 key 和对应的 val，并返回 val
    // 若 key 不存在，则返回 null
    public V remove(K key) {
        if(key==null){
            throw new IllegalArgumentException("key is null");
        }

        Slot<K,V> slot=table[hash(key)];
        if(slot.containsKey(key)){
            size--;
            return slot.remove(key);
        }

        return null;
    }

    /***** 查 *****/

    // 返回 key 对应的 val
    // 如果 key 不存在，则返回 null
    public V get(K key) {
        if(key==null){
            throw new IllegalArgumentException("key is null");
        }

        Slot<K,V> slot=table[hash(key)];
        return slot.get(key);

    }

    // 判断 key 是否存在 Map 中
    public boolean containsKey(K key) {
        if(key==null){
            throw new IllegalArgumentException("key is null");
        }

        Slot<K,V> slot=table[hash(key)];
        return slot.containsKey(key);
    }

    /***** 其他工具函数 *****/

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 哈希函数，将键映射到 table 的索引
    private int hash(K key) {
        return (key.hashCode()&0x7fffffff)%table.length;
    }

    private void resize(int newCap) {

        MyHashMap1<K,V> newMap=new MyHashMap1<>(newCap);

        for (Slot<K, V> slot : table) {
            for (Map.Entry<K, V> entry : slot.entries()) {
                K key= entry.getKey();;
                V val = entry.getValue();
                newMap.put(key,val);
            }
        }

        this.table=newMap.table;

    }

}
