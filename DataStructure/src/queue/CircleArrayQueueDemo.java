package queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        //测试一把
        //创建一个队列
        CircleArray queue = new CircleArray(4);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }

        }

        System.out.println("程序退出~~");
    }
}


//使用数组模拟环形队列
class CircleArray {
    private int maxSize; //数组最大容量
    private int front; //队列头，初始值为0
    private int rear; //队列最后一个元素的后一个位置，初始值为0
    private int[] arr; //存放数据，模拟队列

    //创建队列的构造器
    public CircleArray(int arrMaxsize) {
        maxSize = arrMaxsize;
        arr = new int[maxSize];
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        //判断队列是否已满
        if (isFull()) {
            System.out.println("队列满，不能加入数据！");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //出队列
    public int getQueue() {
        //判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列为空！");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空！");
            return;
        }

        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n", i%maxSize, arr[i%maxSize]);
        }
    }

    //求队列有效数据的个数
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }

    //显示队列的头数据，不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            System.out.println("队列空！");
            throw new RuntimeException("队列空！");
        }
        return arr[front];
    }
}