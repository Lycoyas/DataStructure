package dscourse.arraylinklist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Lycoyas
 * @create 2023-02-20 14:08
 */
public class MyLinkedList<E> {
    // 虚拟头尾节点
    final private Node<E> head, tail;
    private int size;

    // 双链表节点
    private static class Node<E> {
        E val;
        Node<E> next;
        Node<E> prev;

        Node(E val) {
            this.val = val;
        }
    }

    // 构造函数初始化头尾节点
    public MyLinkedList() {
        this.head = new Node<>(null);
        this.tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }


    /***** 增 *****/

    public void addLast(E e) {
        Node<E> temp=tail.prev;
        Node<E> x = new Node<>(e);
        x.next=tail;
        x.prev=temp;
        tail.prev=x;
        temp.next=x;

        size++;
    }

    public void addFirst(E e) {
        Node<E> temp=head.next;
        Node<E> x = new Node<>(e);
        x.next=temp;
        x.prev=head;
        head.next=x;
        temp.prev=x;
        size++;
    }

    public void add(int index, E element) {
        checkPositionIndex(index);

        Node<E> p = getNode(index);
        Node temp=p.prev;
        Node<E> x = new Node<>(element);

        temp.next=x;
        x.prev=temp;
        x.next=p;
        p.prev=x;
        size++;
    }

    /***** 删 *****/

    public E removeFirst() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        Node<E> x=head.next;
        Node<E> temp=x.next;

        head.next=temp;
        temp.prev=head;
        x.next=x.prev=null;

        size--;

        return x.val;
    }

    public E removeLast() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        Node<E> x=tail.prev;
        Node<E> temp=x.prev;

        tail.prev=temp;
        temp.next=tail;
        x.next = x.prev = null;
        size--;
        return x.val;
    }

    public E remove(int index) {
        checkElementIndex(index);

        Node<E> p = getNode(index);
        Node prev=p.prev;
        Node next = p.next;

        prev.next=next;
        next.prev=prev;
        p.next=p.prev=null;
        size--;
        return p.val;
    }

    /***** 查 *****/

    public E get(int index) {
        checkElementIndex(index);
        Node<E> p=getNode(index);
        return p.val;
    }

    public E getFirst() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        return head.next.val;

    }

    public E getLast() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        return tail.prev.val;
    }

    /***** 改 *****/

    public E set(int index, E val) {

        checkElementIndex(index);
        Node<E> p=getNode(index);
        E oldVal=p.val;
        p.val = val;
        return oldVal;

    }

    /***** 其他工具函数 *****/

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node<E> getNode(int index) {
        checkElementIndex(index);
        Node<E> p = head.next;
        // TODO: 可以优化，通过 index 判断从 head 还是 tail 开始遍历
        for (int i = 0; i < index;  i++) {
            p = p.next;
        }
        return p;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * 检查 index 索引位置是否可以存在元素
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    /**
     * 检查 index 索引位置是否可以添加元素
     */
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }


    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E val = p.val;
                p = p.next;
                return val;
            }
        };
    }
}
