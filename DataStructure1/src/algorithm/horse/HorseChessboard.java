package algorithm.horse;

import javax.print.StreamPrintService;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Lycoyas
 * @create 2022-09-13 16:54
 */
public class HorseChessboard {
    private static int X;//棋盘的列数
    private static int Y;//棋盘的行数
    //标记棋盘各个位置是否被访问过
    private static boolean visited[];
    //标记是否棋盘所有位置都被访问
    private static boolean finished;

    public static void main(String[] args) {
        System.out.println("骑士周游算法，开始运行~~");
        //测试骑士周游算法是否正确
        X = 8;
        Y = 8;
        int row = 1; //马儿初始位置的行，从 1 开始编号
        int column = 1; //马儿初始位置的列，从 1 开始编号
        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];//初始值都是 false

        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时: " + (end - start) + " 毫秒");
        for(int[] rows : chessboard) {
            for(int step: rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }


    /**
     * 骑士周游
     * @param chessboard 棋盘
     * @param row 马儿当前位置的行
     * @param column 马儿当前位置的列
     * @param step 第几步
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column,int step) {
        chessboard[row][column] = step;
        visited[row * X + column] = true;
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        sort(ps);
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);
            if(!visited[p.y*X+p.x]){
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }
        //判断是否完成任务
        if (step < X * Y &&!finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        }else{
            finished = true;
        }

    }

    /**
     *根据当前位置(Point 对象)，计算马儿还能走哪些位置(Point)，并放入到一个集合中(ArrayList), 最多
     * 有 8 个位置
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        //创建一个ArrayList
        ArrayList<Point> ps = new ArrayList<>();

        Point p1 = new Point();
        //判断马儿可以走 5 这个位置

        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走 6 这个位置
        if((p1.x = curPoint.x - 1) >=0 && (p1.y=curPoint.y-2)>=0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走 7 这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走 0 这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走 1 这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走 2 这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走 3 这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走 4 这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;

    }

    //根据当前这一步的所有下一步的选择位置进行非递减排序
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                return count1 - count2;
            }
        });

    }

}
