package stack;

public class Calculator {
    public static void main(String[] args) {
        String expression="700+2*6-4";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int index=0;//用于扫描
        int num1=0;
        int num2=0;
        int oper=0;
        int res=0;
        char ch=' ';//将每次扫描得到的char保存到ch
        String keepNum="";//拼接多位数

        while(true){
            ch=expression.substring(index,index+1).charAt(0);
            if(operStack.isOper(ch)){
                if(!operStack.isEmpty()){
                    if(operStack.priority(ch)<=operStack.priority(operStack.peek())){
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper=operStack.pop();
                        res=numStack.cal(num1,num2,oper);
                        numStack.push(res);
                        operStack.push(ch);
                    }else{
                        operStack.push(ch);
                    }
                }else{
                    //栈为空直接入栈
                    operStack.push(ch);
                }
            }else{
                //处理多位数，不能发现一个数就立即入栈
                //需要看表达式后一位是不是数
                keepNum+=ch;

                //如果ch已经是表达式最后一位，直接入栈
                if(index == expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        //后一位是运算符，入栈
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum="";
                    }

                }



            }
            index++;
            if(index >= expression.length()){
                break;
            }
        }

        //表达式扫描完毕
        while(true){
            if(operStack.isEmpty()){
                break;
            }
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();
            res=numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        System.out.printf("表达式 %s = %d\n",expression,numStack.pop());

    }
}

class ArrayStack2 {
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public ArrayStack2(int maxSize) {
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

    public int peek(){
        if(isEmpty()){
            throw new RuntimeException("栈空了");
        }
        return stack[top];
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

    //返回运算符的优先级
    //数字越大，优先级越高
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else{
            return -1;
        }
    }

    //判断是不是运算符
    public boolean isOper(char val){
        return val=='+'||val=='-'||val=='/'||val=='*';
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int res=0;
        switch(oper){
            case '+':
                res=num1+num2;
                break;
            case '-':
                res=num2-num1;
                break;
            case '*':
                res=num2*num1;
                break;
            case '/':
                res=num2/num1;
                break;
            default:
                break;
        }
        return res;
    }



}
