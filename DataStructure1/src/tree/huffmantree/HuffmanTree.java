package tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = new int[]{13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);
        preOrder(root);

    }

    //创建HuffmanTree
    public static Node createHuffmanTree(int[] arr) {

        //遍历arr数组
        //将arr每个元素构建成一个Node
        //将Node放入ArrayList
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        //循环处理
        while (nodes.size() > 1) {
            //排序
            Collections.sort(nodes);
            //取出权值最小的两颗 二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //构建新二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left=leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    //谦虚遍历
    public static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        }else{
            System.out.println("是空树，不能遍历");
        }
    }
}


class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.value - o.value;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }
}
