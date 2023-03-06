package linkedlist;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();

        System.out.println("修改");
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        doubleLinkedList.list();

        System.out.println("删除");
        doubleLinkedList.del(3);
        doubleLinkedList.list();



    }

}

class DoubleLinkedList{
    private HeroNode2 head=new HeroNode2(0,"","");

    public HeroNode2 getHead(){return head;}

    public void list(){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp=head.next;
        while(true){
            if(temp==null){
                break;
            }
            System.out.println(temp);
            temp=temp.next;
        }
    }

    public void add(HeroNode2 heroNode){
        HeroNode2 temp=head;
        while(true){
            if(temp.next==null){
                break;
            }
            temp=temp.next;
        }
        temp.next=heroNode;
        heroNode.pre=temp;
    }

    public void update(HeroNode2 newHeroNode){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp=head.next;
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

        if(head.next==null){
            System.out.println("链表为空，无法删除");
        }

        HeroNode2 temp=head.next;
        boolean flag=false;
        while(true){
            if(temp==null){
                break;
            }
            if(temp.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.pre.next=temp.next;
            if(temp.next!=null){
                temp.next.pre=temp.pre;
            }
        }else{
            System.out.printf("要删除的%d节点不存在",no);
        }

    }

}

class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int hNo,String name,String nickname){
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
