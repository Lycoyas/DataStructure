package lc202210.lc901;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Lycoyas
 * @create 2022-10-21 17:24
 */
public class StockSpanner {
    Deque<Stock> stack ;
    public StockSpanner() {
        stack = new ArrayDeque();
    }

    public int next(int price) {

        int  day=1;
        while (!stack.isEmpty() && stack.getFirst().price<= price) {
            day += stack.removeFirst().day;
        }
        stack.addFirst(new Stock(day, price));
        return day;
    }
    class Stock{
        int day;
        int price;

        public Stock(int day, int price) {
            this.day = day;
            this.price = price;
        }
    }
}
