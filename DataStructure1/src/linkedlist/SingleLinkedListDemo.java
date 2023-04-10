package linkedlist;

import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero4);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);

        //加入按照编号的顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        //显示一把
        singleLinkedList.list();
/*        HeroNode newHeroNode = new HeroNode(2, "小卢", "玉麒麟~~");
        System.out.println("修改");
        singleLinkedList.update(newHeroNode);
        //显示一把
        singleLinkedList.list();


        System.out.println(getLength(singleLinkedList.getHead()));

        System.out.println(findLastIndexNode2(singleLinkedList.getHead(),2));*/

       /* System.out.println("反转");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();*/

        System.out.println("逆序打印");
        reversePrint(singleLinkedList.getHead());

    }

    //单链表中节点个数(如果带头节点，需求不统计头节点)
    public static int getLength(HeroNode head){

        if(head.next==null){
            return 0;
        }
        int length=0;
        HeroNode cur=head.next;
        while(cur!=null){
            length++;
            cur=cur.next;
        }
        return length;

    }

    //查找单链表倒数第K个节点
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        if(head.next==null){return null;}
        int size=getLength(head);
        if(index<=0||index>size){return null;}
        HeroNode cur=head.next;
        for(int i=0;i<size-index;i++){
            cur=cur.next;
        }
        return cur;

    }

    //查找单链表倒数第K个节点
    public static HeroNode findLastIndexNode2(HeroNode head,int index){
        if(head.next==null){return null;}
        HeroNode pre=head;

        for(int i=0;i<index;i++){
            pre=pre.next;
            if(pre==null){return null;}
        }
        HeroNode cur=head;
        while(pre!=null){
            cur=cur.next;
            pre=pre.next;
        }
        return cur;
    }

    //反转单链表
    public static void reverseList(HeroNode head){
        if(head.next==null||head.next.next==null){return ;}
        HeroNode cur=head.next;
        HeroNode next=null;//指向当前节点的下一个节点
        HeroNode reverseHead=new HeroNode(0,"","");

        while(cur!=null){
            next=cur.next;
            cur.next=reverseHead.next;
            reverseHead.next=cur;
            cur=next;
        }
        head.next=reverseHead.next;

    }

    //逆序遍历打印
    public static void reversePrint(HeroNode head){
        if(head.next==null){return;}
        Stack<HeroNode> stack=new Stack<>();
        HeroNode cur=head.next;
        while(cur!=null){
            stack.push(cur);
            cur=cur.next;
        }
        while(stack.size()>0){
            System.out.println(stack.pop());
        }

    }


}

class SingleLinkedList{
    private HeroNode head=new HeroNode(0,"","");

    public HeroNode getHead(){return head;}

    public void add(HeroNode heroNode){
        HeroNode temp=head;
        while(true){
            if(temp.next==null){
                break;
            }
            temp=temp.next;
        }
        temp.next=heroNode;
    }

    public void addByOrder(HeroNode heroNode){
        HeroNode temp=head;
        //flag标志添加的编号是否存在，默认为false
        boolean flag=false;
        while(true){
            if(temp.next==null){
                break;
            }
            if(temp.next.no>heroNode.no){//位置找到，在temp的后面添加
                break;
            }
            if(temp.next.no==heroNode.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            System.out.printf("准备插入的英雄编号%d已存在\n",heroNode.no);
        }else{
            heroNode.next=temp.next;
            temp.next=heroNode;
        }

    }

    public void update(HeroNode newHeroNode){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp=head.next;
        boolean flag=false;
        while(true){
            if(temp==null){
                break;//链表遍历结束
            }
            if(temp.no== newHeroNode.no){
                //找到
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.name= newHeroNode.name;;
            temp.nickname= newHeroNode.nickname;
        }else{
            System.out.printf("没有找到编号为%d的英雄\n",newHeroNode.no);
        }

    }

    public void del(int no){
        HeroNode temp=head;
        boolean flag=false;
        while(true){
            if(temp.next==null){
                break;
            }
            if(temp.next.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.next=temp.next.next;
        }else{
            System.out.printf("要删除的%d节点不存在",no);
        }

    }


    public void list(){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp=head.next;
        while(true){
            if(temp==null){
                break;
            }
            System.out.println(temp);
            temp=temp.next;
        }
    }



}

class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;

    public HeroNode(int hNo,String name,String nickname){
        this.no=hNo;
        this.name=name;
        this.nickname=nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
