package basic;

import java.util.HashMap;

/**
 * @author Lycoyas
 * @create 2023-02-12 11:58
 */
public class LRUCache {


    class Node{
        int key,val;
        public Node next,prev;

        public Node(int k, int v) {
            this.key=k;
            this.val=v;
        }
    }

    class DoubleList{

        private Node head, tail;
        private int size;

        public DoubleList() {

            head = new Node(0, 0);
            tail = new Node((0), 0);
            head.next=tail;
            tail.prev=head;
            size = 0;
        }

        public void addLast(Node x) {

            x.next=tail;
            x.prev=tail.prev;
            tail.prev.next=x;
            tail.prev=x;
            size++;

        }

        public void remove(Node x) {

            x.prev.next=x.next;
            x.next.prev = x.prev;
            size--;

        }

        public Node removeFirst() {

            if (head.next == tail) {
                return null;
            }

            Node first=head.next;
            remove(first);
            return first;

        }

        public int size() {
            return size;
        }

    }

    private HashMap<Integer,Node>map;
    private DoubleList cache;
    private int cap;

    /* 将某个 key 提升为最近使用的 */
    private void makeRecently(int key) {

        Node x = map.get(key);
        cache.remove(x);
        cache.addLast(x);
    }

    /* 添加最近使用的元素 */
    private void addRecently(int key, int val) {

        Node x = new Node(key, val);
        map.put(key, x);
        cache.addLast(x);

    }

    /* 删除某一个 key */
    private void deleteKey(int key) {

        Node x = map.get(key);
        cache.remove(x);
        map.remove(key);

    }


    /* 删除最久未使用的元素 */
    private void removeLeastRecently() {

        Node deleted = cache.removeFirst();
        map.remove(deleted.key);
    }

    public LRUCache(int capacity) {
        this.cap=capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key) {

        if (!map.containsKey(key)) {
            return -1;
        }
        makeRecently(key);
        return map.get(key).val;
    }

    public void put(int key, int value) {

        if (map.containsKey(key)) {
            deleteKey(key);
            addRecently(key,value);
            return;
        }

        if (cache.size == cap) {
            removeLeastRecently();
        }

        addRecently(key, value);

    }



}
