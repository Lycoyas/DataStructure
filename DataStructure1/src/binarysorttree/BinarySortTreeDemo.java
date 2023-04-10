package binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        binarySortTree.infixOrder();
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(10);
        binarySortTree.delNode(1);
        System.out.println("==========================");
        System.out.println("当前叶子结点:"+binarySortTree.getRoot());
        binarySortTree.infixOrder();



    }
}

class BinarySortTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    //查找要删除的结点
    public Node search(int value) {
        if (root == null) {
            return null;
        }else{
            return root.search(value);
        }
    }

    //查找父结点
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        }else{
            return root.searchParent(value);
        }
    }

    /**
     * 删除node为根结点的二叉排序树的最小结点
     * @param node 传入结点
     * @return 返回以node为根节点的二叉排序树的最小结点的值，并删除node为根结点的二叉排序树的最小结点
     */
    public int delRightTreeMin(Node node) {
        Node target=node;
        while (target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }

    //删除结点
    public void delNode(int value) {
        if (root == null) {
            return;
        }else{
            //找到要删除的结点
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            //如果当前这颗二叉排序树只有一个结点
            if (root.left == null && root.right == null) {
                root=null;
                return;
            }

            //去查找targetNode的父结点
            Node parent = searchParent(value);
            //如果删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是父结点的左子结点，还是右子结点
                if (parent.left != null && parent.left.value == value) {//左子结点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {//右子结点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) {
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;

            } else {//删除只有一颗子树的结点
                //如果删除的结点有左子结点
                if (targetNode.left != null) {
                    if(parent != null){
                        if (parent.left.value == value) {
                            parent.left=targetNode.left;
                        }else{
                            parent.right = targetNode.left;
                        }
                    }else{
                        root=targetNode.left;
                    }

                }else{
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left=targetNode.right;
                        }else{
                            parent.right = targetNode.right;
                        }
                    }else{
                        root = targetNode.right;
                    }

                }
            }

        }
    }

    //添加结点的方法
    public void add(Node node) {
        if (root == null) {
            root = node;
        }else{
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        }else{
            System.out.println("二叉排序树为空！");
        }
    }

}

class   Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }


    /**
     * 查找要删除的结点
     * @param value
     * @return
     */
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        }else{
            if (this.right == null) {
                return null;
            }
            return this.right.search(value);
        }
    }

    //查找要删除的结点的父节点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        }else{
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            }else{
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //添加结点的方法
    //满足二叉排序树的要求
    public void add(Node node) {
        if (node == null) {
            return;
        }
        //判断传入的结点的值，和当前子树的根节点的值关系
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            }else{
                //递归向左子树添加
                this.left.add(node);
            }
        }else{
            if (this.right == null) {
                this.right = node;
            }else{
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

}
