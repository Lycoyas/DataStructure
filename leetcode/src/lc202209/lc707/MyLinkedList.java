package lc202209.lc707;

/**
 * @author Lycoyas
 * @create 2022-09-23 10:09
 */
public class MyLinkedList {
    int size=0;
    Node first;
    Node last;

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(1);
        list.deleteAtIndex(0);

    }

    public MyLinkedList() {

    }

    public int get(int index) {

        if (index < 0 || index >= size) {
            return -1;
        }

        Node node;
        if (index < size / 2) {
            node=first;
            while (index-- > 0) {
                node = node.next;
            }
        }else{
            node =last;
            while (++index <size) {
                node = node.prev;
            }
        }
        return node.val;

    }

    public void addAtHead(int val) {
        Node node = new Node(null, val, first);
        if (first != null) {
            first.prev = node;
        }else{
            last=node;
        }
        first=node;
        size++;
    }

    public void addAtTail(int val) {
        Node node = new Node(last, val, null);
        if (last != null) {
            last.next=node;
        }else{
            first = node;
        }
        last=node;
        size++;
    }

    public void addAtIndex(int index, int val) {

        if (index > size) {
            return;
        }

        Node node;

        if (index <= 0) {
            node = new Node(null, val, first);
            if (first != null) {
                first.prev = node;
            }else{
                last=node;
            }
            first = node;

        }else if (index == size) {
            node = new Node(last, val, null);
            if (last != null) {
                last.next=node;
            }else{
                first = node;
            }
            last = node;
        }else{
            if (index < size / 2) {
                node=first;
                while (index-- > 0 ) {
                    node = node.next;
                }
            }else{
                node =last;
                while (++index <size) {
                    node = node.prev;
                }
            }

            Node prev = node.prev;

            Node newNode = new Node(prev, val, node);
            prev.next = newNode;
            node.prev = newNode;
        }



        size++;
    }

    public void deleteAtIndex(int index) {

        if (index < 0 || index >= size) {
            return;
        }

        if (index == 0) {
            Node f=first;
            first = first.next;
            f.next = null;
            if (first != null) {
                first.prev = null;
            }else{
                last = null;
            }
        }else if (index == size - 1) {
            Node l=last;
            last = last.prev;
            l.prev = null;
            last.next = null;
        }else{
            Node node;
            if (index < size / 2) {
                node=first;
                while (index-- > 0) {
                    node = node.next;
                }
            }else{
                node =last;
                while (++index <size) {
                    node = node.prev;
                }
            }

            Node prev = node.prev;
            prev.next = node.next;
            node.next.prev = prev;
            node.next = null;
            node.prev = null;
        }


        size--;

    }

    static class Node{

        int val;
        Node next;
        Node prev;

        Node(Node prev, int val, Node next) {
            this.prev=prev;
            this.val=val;
            this.next = next;
        }
    }
}


