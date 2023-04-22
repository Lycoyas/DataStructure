package dscourse.recursivelist;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author Lycoyas
 * @create 2023-04-19 14:30
 * @description
 */
public class MaxPQ <Key extends Comparable<Key>>{





    private Key[]pq;

    private int size=0;

    public MaxPQ(int cap) {
        // 索引 0 不用，所以多分配一个空间
        pq = (Key[]) new Comparable[cap + 1];

    }


    /* 返回当前队列中最大元素 */
    public Key max() {
        return pq[1];
    }

    /* 插入元素 e */
    public void insert(Key e) {

        size++;
        pq[size]=e;
        swim(size);

    }

    /* 删除并返回当前队列中最大元素 */
    public Key delMax() {
        Key max = pq[1];
        swap(1, size);
        pq[size] = null;
        size--;
        sink(1);
        return max;
    }

    /* 上浮第 x 个元素，以维护最大堆性质 */
    private void swim(int x) {
        while (x > 1 && less(parent(x), x)) {
            swap(parent(x),x);
            x = parent(x);
        }
    }

    /* 下沉第 x 个元素，以维护最大堆性质 */
    private void sink(int x) {

        while (left(x) <= size) {
            int max = left(x);

            if (right(x) <= size && less(max, right(x))) {
                max = right(x);
            }
            if(less(max,x)) {
                break;
            }
            swap(x, max);
            x = max;
        }

    }

    /* 交换数组的两个元素 */
    private void swap(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }


    /* pq[i] 是否比 pq[j] 小？ */
    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    // 父节点的索引
    int parent(int root) {
        return root / 2;
    }
    // 左孩子的索引
    int left(int root) {
        return root * 2;
    }
    // 右孩子的索引
    int right(int root) {
        return root * 2 + 1;
    }





}
