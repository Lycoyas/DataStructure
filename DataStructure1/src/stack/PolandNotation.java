package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {

        //(3+4)x5-6 => 3 4 + 5 x 6 -
        //4x5 - 8 + 60 + 8 / 2  =>  4 5 x 8 - 60 + 8 2 / +
        //逆波兰表达式数字和符号使用空格隔开
       /* String suffixExpression = "4 5 x 8 - 60 + 8 2 / +";
        List<String> rpnList = getListString(suffixExpression);
        System.out.println(rpnList);
        int res = calculate(rpnList);
        System.out.printf("计算的结果是= %d", res);*/

        String expression="1+((2+3)x4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println(infixExpressionList);
        System.out.println(parseSuffixExpressionList(infixExpressionList));

    }

    public static List<String> getListString(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for(String ele:split){
            list.add(ele);
        }
        return list;
    }

    /*
    1．从左至右扫描，将 3 和 4 压入堆栈；
    2．遇到+运算符，因此弹出 4 和 3（4 为栈顶元素，3 为次顶元素），计算出 3+4 的值，得 7，再将 7 入栈；
    3．将 5 入栈；
    4．接下来是×运算符，因此弹出 5 和 7，计算出 7×5=35，将 35 入栈；
    5．将 6 入栈；
    6．最后是-运算符，计算出 35-6 的值，即 29，由此得出最终结果
     */
    public static int calculate(List<String> ls){
        Stack<String> stack = new Stack<String>();
        for (String item : ls) {
            if (item.matches("\\d+")) {//匹配多位数
                stack.push(item);
            } else {
                //pop出两个数，并运算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if ("-".equals(item)) {
                    res = num1 - num2;
                } else if ("x".equals(item)) {
                    res = num1 * num2;
                } else if ("/".equals(item)) {
                    res = num1 / num2;
                }else{
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push(""+res);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    //将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s){
        List<String> ls = new ArrayList<>();
        int i=0;//遍历中缀表达式字符串
        String str;//多位数拼接
        char c;
        do{
            //如果c是一个非数字，需要加入到ls
            if((c=s.charAt(i))<'0'||(c=s.charAt(i))>'9'){
                ls.add(""+c);
                i++;
            }else{//考虑多位数
                str="";
                while(i<s.length()&&(c=s.charAt(i))>='0'&&(c=s.charAt(i))<='9'){
                    str+=c;
                    i++;
                }
                ls.add(str);
            }
        }while(i < s.length());

        return ls;
    }

    public static List<String> parseSuffixExpressionList(List<String> ls){
        //符号栈
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();

        for(String item:ls){

            if(item.matches("\\d+")){
                s2.add(item);
            }else if("(".equals(item)){
                s1.push(item);
            }else if(")".equals(item)){
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop(); //消除一对括号
            }else{
                while(s1.size()!=0&&Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);

            }

        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;
    }

}

//返回运算符的优先级
class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;

    public static int getValue(String operation) {
        int result=0;
        switch (operation) {
            case"+":
                result=ADD;
                break;
            case"-":
                result=SUB;
                break;
            case"x":
                result=MUL;
                break;
            case"/":
                result=DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }

        return result;

    }

}
