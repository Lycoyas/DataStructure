package chapter06.uva11988;

import java.util.Scanner;

/**
 * @author Lycoyas
 * @create 2022-09-16 23:00
 */
public class UVA11988_2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] next = new int[100000 + 5];

        while (scan.hasNext()) {
            String str = scan.next();
            solution(str);
        }

    }

    static void solution(String str) {

        Node head = new Node();
        Node last = head, cur = head;

        for (char c : str.toCharArray()) {
            if (c == '[') {
                cur=head;
            } else if (c == ']') {
                cur = last;
            }else{
                Node node = new Node();
                node.c = c;
                node.next=cur.next;
                cur.next=node;
                cur = cur.next;
                if (cur.next==null) {
                    last = cur;
                }

            }
        }

        StringBuilder sb = new StringBuilder();
        while (head.next != null) {
            sb.append(head.next.c);
            head = head.next;
        }
        System.out.println(sb.toString());

    }
}

class Node{
    char c;
    Node next;
}
