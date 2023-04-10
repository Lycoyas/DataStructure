package algorithm.mst.kruskal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

/**
 * @author Lycoyas
 * @create 2022-09-13 13:14
 */
public class KruskalCase {

    private int edgeNum; //边的个数
    private char[] vertexes; //顶点数组
    private int[][] matrix; //邻接矩阵
    //使用 INF 表示两个顶点不能连通
    private  final static int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
                            /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
                            /*A*/ { 0, 12, INF, INF, INF, 16, 14},
                            /*B*/ {12, 0, 10, INF, INF, 7, INF},
                            /*C*/ {INF, 10, 0, 3, 5, 6, INF},
                            /*D*/ {INF, INF, 3, 0, 4, INF, INF},
                            /*E*/ {INF, INF, 5, 4, 0, 2, 8},
                            /*F*/ {16, 7, 6, INF, 2, 0, 9},
                            /*G*/ {14, INF, INF, INF, 8, 9, 0}};

        KruskalCase kruskalCase = new KruskalCase(vertexs, matrix);
        kruskalCase.print();
        kruskalCase.kruskal();

    }

    public KruskalCase(char[] vertexes, int[][] matrix) {
        int vlen = vertexes.length;

        this.vertexes = Arrays.copyOf(vertexes, vertexes.length);

        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }


        for (int i = 0; i < vlen; i++) {
            for (int j = i+1; j < vlen; j++) {
                if (matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }


    }

    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为: ");
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = 0; j < vertexes.length; j++) {
                System.out.printf("%12d",matrix[i][j]);
            }
            System.out.println();
        }
    }

    //对边进行排序
    private void sortEdge(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }

    /**
     *
     * @param ch 'A','B',,,,,,
     * @return
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 功能: 获取图中边，放到EData[]数组中，后面需要遍历该数组
     * @return
     */
    private EData[] getEdges() {
        int index=0;
        EData[] edges=new EData[edgeNum];
        for (int i = 0; i < vertexes.length; i++) {
            for (int j = i + 1; j < vertexes.length; j++) {
                if (matrix[i][j] != INF) {
                    edges[index++] = new EData(vertexes[i], vertexes[j], matrix[i][j]);
                }
            }
        }
        return edges;

    }

    /**
     * 获取下标为i的顶点的终点
     * @param ends : 数组记录了各个顶点对应的终点是哪个,在遍历过程中逐步形成
     * @param i 顶点对应的下标
     * @return 下标为i的顶点对应的终点的下标
     */
    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    public void kruskal() {

        int[] ends = new int[vertexes.length]; //保存已有最小生成树中每个顶点在最小生成树中的终点

        //结果数组，保存最后的最小生成树
        List res = new ArrayList<EData>();

        //获取图中所有的边的集合
        EData[] edges = getEdges();
        System.out.println("图的边的集合="+Arrays.toString(edges)+" 共"+edges.length+"条边");

        //按边的权值大小从小到大排序
        sortEdge(edges);

        //遍历edges
        for (int i = 0; i < edgeNum; i++) {
            //获取第i条边的第一个顶点
            int p1 = getPosition(edges[i].start);
            //获取第i条边的第二个顶点
            int p2 = getPosition(edges[i].end);
            //获取p1,p2的终点，判断是否构成通路
            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);
            if (m != n) {
                //更新ends
                ends[m]=n;
                res.add(edges[i]);
            }

        }

        //统计并打印最小生成树
        System.out.println("最小生成树为: ");
        System.out.println(res);
    }





}

//实例表示一条边
class EData {

    char start;//边的起点
    char end;//边的终点
    int weight;

    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }


    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }


}
