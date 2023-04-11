package dscourse.arraylinklist;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * @author Lycoyas
 * @create 2023-02-20 14:08
 */
public class MyArrayList<E> {
    private E[] data;
    private int size;
    private static final int INIT_CAP=1;

    public MyArrayList() {
        this(INIT_CAP);
    }

    public MyArrayList(int initCapacity) {
        data = (E[]) new Object[initCapacity];
        size = 0;
    }

    /******增******/
    public void addLast(E e){
        if(data.length==size){
            //扩容
            resize(data.length * 2);
        }
        data[size] = e;
        size++;
    }

    public void add(int index, E element) {
        checkPositionIndex(index);
        if(data.length==size){
            //扩容
            resize(data.length * 2);
        }
        System.arraycopy(data,index,data,index+1,size-index);
        data[index] = element;
        size++;
    }

    /******删******/
    public E removeLast() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }

        if(size < data.length / 4){
            resize(data.length/2);
        }
        E deletedVal=data[size-1];
        data[size-1]=null;//为了垃圾回收
        size--;
        return deletedVal;
    }



    public E remove(int index) {
        checkElementIndex(index);

        if(size < data.length / 4){
            resize(data.length/2);
        }

        E deletedVal=data[index];
        System.arraycopy(data,index+1,data,index,size-index-1);
        data[size-1]=null;
        size--;
        return deletedVal;
    }

    /******查******/

    public E get(int index){
        checkElementIndex(index);
        return data[index];
    }

    /******改******/

    public E set(int index,E element){
        checkElementIndex(index);
        E oldVal=data[index];
        data[index] = element;
        return oldVal;
    }



    /******工具函数******/
    public int size(){
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    /*********私有函数*********/

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

    //扩容
    private void resize(int newCap) {
        E[] temp=(E[])new Object[newCap];
        System.arraycopy(data, 0, temp, 0, size);
        data=temp;
    }
}
