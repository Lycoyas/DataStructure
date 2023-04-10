package avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4, 3, 6, 5, 7, 8};
        //int[] arr = {10,12,8,9,7,6};
        int[] arr = {10,11,7,6,8,9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        avlTree.infixOrder();
        System.out.println("在平衡处理后：");
        System.out.println(avlTree.getRoot().height());
        System.out.println(avlTree.getRoot().leftHeight());
        System.out.println(avlTree.getRoot().rightHeight());
    }
}

//AVL树
class AVLTree{
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

    //左旋转方法
    private void leftRotate() {
        //创建新结点，以当前根结点的值
        Node newNode = new Node(value);
        //把新的结点的左子树设置为当前结点的左子树
        newNode.left=left;
        //把新结点的右子树设置为当前结点右子树的左子树
        newNode.right=right.left;
        //把当前结点的值替换成右子结点的值
        value = right.value;
        //把当前结点的右子树设置成当前结点右子树的右子树
        right=right.right;
        //把当前结点的左子树设置成新结点
        left=newNode;
    }

    private void rightRotate() {
        Node newNode = new Node(value);
        newNode.right=right;
        newNode.left = left.right;
        value=left.value;
        left=left.left;
        right = newNode;
    }

    //返回当前结点的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(),right == null ? 0 : right.height())+1;
    }

    //左子树高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }else{
            return left.height();
        }
    }

    //右子树高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }else{
            return right.height();
        }
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

        //添加后进行AVL的判断
        if (rightHeight() - leftHeight() > 1) {
            if (right != null && right.leftHeight()>right.rightHeight()) {
                right.rightRotate();
            }

            leftRotate();
        }
        if (leftHeight() - rightHeight() > 1) {
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前结点的左子树进行左旋转
                left.leftRotate();
            }
            rightRotate();
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
