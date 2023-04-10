package stack;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key="";
        boolean loop=true;
        Scanner scanner = new Scanner(System.in);

        while(loop){
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈");
            System.out.println("pop: 表示出栈");
            System.out.println("请输入你的选择");
            key=scanner.next();
            switch(key){
                case "show":
                    stack.list();break;
                case "push":
                    System.out.println("输入一个数：");
                    int value=scanner.nextInt();
                    stack.push(value);break;
                case "pop":
                    int res=stack.pop();
                    System.out.printf("出栈的数据时%d \n",res);
                    break;
                case "exit":
                    scanner.close();
                    loop=false;
                    break;
                default:break;

            }
        }
        System.out.println("程序退出");

    }
}

class ArrayStack {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value){
        if(isFull()){
            System.out.println("栈满了");
            return;
        }
        top++;
        stack[top]=value;
    }

    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("栈空了");
        }
        int value=stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void list(){
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }
        for(int i=top;i>=0;i--){
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }
    }

}
