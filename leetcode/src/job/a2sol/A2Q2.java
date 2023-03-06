package job.a2sol; /**
 * EECS 2011 A, Fall 2022.
 * Assignment 2, Question 2 starter code.
 * Please read carefully the instructions in the assignment handout
 * and the starter code.
 */
import java.util.ArrayList;
// DO NOT ADD ANY package
// DO NOT ADD ANY import
/**
 * The A2Q2 class
 */
public class A2Q2 {

    /**
     * A class representing a single move in the TOH solution,
     * i.e., moving the disk at the top of fromPeg to the top of toPeg
     * Note: a move is invalid if it results in a larger disk being above a smaller disk.
     * DO NOT MODIFY THIS CLASS.
     */
    static class Move {

        private final int fromPeg;
        private final int toPeg;

        public Move(int from, int to) {
            this.fromPeg = from;
            this.toPeg = to;
        }

        public String toString() {
            return String.format("%d to %d", this.fromPeg, this.toPeg);
        }
    }

    /**
     * Return the sequence of moves that solves the 3-peg TOH problem with n disks.
     * Assumptions:
     * - The pegs are numbered 1, 2, 3
     * - The origin peg is 1.
     * - The destination peg is 2.
     * - n > 0
     */
    public static ArrayList<Move> threePegTOH(int n) {
        // TODO: complete this method
        ArrayList<Move> list = new ArrayList<>();
        three(n, 1, 2, 3, list);
        return list;
    }

    static void three(int n,int p1,int p2,int p3,ArrayList<Move> list) {
        if(n==1){
            Move move = new Move(p1, p2);
            list.add(move);
        }else{
            three(n - 1, p1, p3, p2, list);
            Move move = new Move(p1, p2);
            list.add(move);
            three(n - 1, p3, p2, p1, list);
        }
    }


    /**
     * Return the sequence of moves that solves the 4-peg TOH problem with n disks,
     * with the strategy described in the assignment handout
     * Assumptions:
     * - The pegs are numbered 1, 2, 3, 4
     * - The origin peg is 1.
     * - The destination peg is 2.
     * - n > 0
     */
    public static ArrayList<Move> fourPegTOH(int n) {

        // TODO: complete this method
        ArrayList<Move> list = new ArrayList<>();
        four(n, 1, 2, 3, 4,list);
        return list;
    }

    static void four(int n, int p1, int p2, int p3, int p4, ArrayList<Move> list) {

        if (n == 1) {
            Move move = new Move(p1, p2);
            list.add(move);
        }else{
            int k = n / 2;
            four(n-k, p1, p3, p2, p4, list);
            three(k, p1, p2, p4, list);
            four(n-k, p3, p2, p1, p4,list);


        }
    }

    public static void main(String[] args) {

       //System.out.println(threePegTOH(9).size());
        System.out.println(fourPegTOH(5).size());
        System.out.println(fourPegTOH(6).size());
        System.out.println(fourPegTOH(7).size());
        System.out.println(fourPegTOH(8).size());
        System.out.println(fourPegTOH(9).size());
        System.out.println(fourPegTOH(10).size());
        System.out.println(fourPegTOH(12).size());
    }
}
