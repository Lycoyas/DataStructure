package tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "1");
        HeroNode heroNode2 = new HeroNode(3, "3");
        HeroNode heroNode3 = new HeroNode(6, "6");
        HeroNode heroNode4 = new HeroNode(8, "8");
        HeroNode heroNode5 = new HeroNode(10, "10");
        HeroNode heroNode6 = new HeroNode(14, "14");
        heroNode1.setLeft(heroNode2);
        heroNode1.setRight(heroNode3);
        heroNode2.setLeft(heroNode4);
        heroNode2.setRight(heroNode5);
        heroNode3.setLeft(heroNode6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(heroNode1);
        threadedBinaryTree.threadedNodes();

        System.out.println("线索化遍历");
        threadedBinaryTree.threadedList();

    }
}


//二叉树
class ThreadedBinaryTree{
    private HeroNode root;

    //为了实现线索化，需要创建指向当前结点的前驱结点的指针
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }


    public void threadedNodes() {
        this.threadedNodes(root);
    }

    //遍历线索化二叉树的方法
    public void threadedList() {

        //定义一个变量，存储当前遍历的结点，从root开始
        HeroNode node = root;
        while (node != null) {
            //找到中序遍历的第一个结点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            //如果右指针指向后继结点，就一直输出
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }

    }

    /**
     * 中序线索化
     * @param node 当前线索化的结点
     */
    public void threadedNodes(HeroNode node) {

        if (node == null) {
            return;
        }
        //线索化左子树
        threadedNodes(node.getLeft());
        //线索化当前结点
        if (node.getLeft() == null) {
            //指向前驱结点
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre!=null&&pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //每处理一个结点，让当前结点是下一个结点的前驱结点
        pre=node;



        //线索化右子树
        threadedNodes(node.getRight());



    }

    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            }else{
                root.delNode(no);
            }
        }else{
            System.out.println("空树不能删除");
        }
    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历
    public HeroNode preOrderSearch(int no) {
        if(root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }
    //中序遍历
    public HeroNode infixOrderSearch(int no) {
        if(root != null) {
            return root.infixOrderSearch(no);
        }else {
            return null;
        }
    }
    //后序遍历
    public HeroNode postOrderSearch(int no) {
        if(root != null) {
            return this.root.postOrderSearch(no);
        }else {
            return null;
        }
    }


}

//先创建HeroNode结点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    // 0: 左子树 1:前驱结点
    private int leftType;
    // 0: 右子树 1:后继结点
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
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

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }
        HeroNode resNode = null;
        if (this.left != null) {
            resNode=this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode=this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
//判断当前结点的左子节点是否为空，如果不为空，则递归中序查找
        HeroNode resNode = null;
        if(this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if(resNode != null) {
            return resNode;
        }
        System.out.println("进入中序查找");
//如果找到，则返回，如果没有找到，就和当前结点比较，如果是则返回当前结点
        if(this.no == no) {
            return this;
        }
//否则继续进行右递归的中序查找
        if(this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }
    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
//判断当前结点的左子节点是否为空，如果不为空，则递归后序查找
        HeroNode resNode = null;
        if(this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null) {//说明在左子树找到
            return resNode;
        }
//如果左子树没有找到，则向右子树递归进行后序遍历查找
        if(this.right != null) {
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode != null) {
            return resNode;
        }
        System.out.println("进入后序查找");
//如果左右子树都没有找到，就比较当前结点是不是
        if(this.no == no) {
            return this;
        }
        return resNode;
    }

    public void delNode(int no) {

        if (this.left!=null&&this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right!=null&&this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(no);
        }
        if (this.right != null) {
            this.right.delNode(no);
        }


    }


    public HeroNode(int no, String name) {
        this.no=no;
        this.name=name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}



