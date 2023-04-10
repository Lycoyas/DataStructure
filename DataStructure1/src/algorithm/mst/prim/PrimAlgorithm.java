package algorithm.mst.prim;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-09-13 12:32
 */
public class PrimAlgorithm {
    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertexes = data.length;
        int [][]weight=new int[][]{
                {10000,5,7,10000,10000,10000,2},
                {5,10000,10000,9,10000,10000,3},
                {7,10000,10000,10000,8,10000,10000},
                {10000,9,10000,10000,10000,4,10000},
                {10000,10000,8,10000,10000,5,4},
                {10000,10000,10000,4,5,10000,6},
                {2,3,10000,10000,4,6,10000},};

        //创建MGraph对象
        MGraph mGraph = new MGraph(vertexes);
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph, vertexes, data, weight);
        minTree.showGraph(mGraph);

        //测试Prim
        minTree.prim(mGraph,2);

    }
}

//创建最小生成树
class MinTree {
    public void createGraph(MGraph graph, int vertexes, char data[], int[][] weight) {

        int i, j;
        for (i = 0; i < vertexes; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < vertexes; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }

    }

    //显示图
    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }



    /**
     * Prim算法，得到最小生成树
     * @param graph
     * @param v 从图的第几个顶点开始生成
     */
    public void prim(MGraph graph, int v) {

        //标记顶点是否被访问过
        int[] visited = new int[graph.vertexes];
        visited[v]=1;
        //记录两个顶点的下标
        int h1=-1,h2=-1;
        int miniWeight = 10000;
        for (int k = 1; k < graph.vertexes; k++) {
            //确定每一次生成的子图，和哪个结点的距离最近
            for (int i = 0; i < graph.vertexes; i++) {//i结点表示被访问过的结点
                for (int j = 0; j < graph.vertexes; j++) {//j结点表示还没有访问过的结点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < miniWeight) {
                        miniWeight = graph.weight[i][j];
                        h1=i;
                        h2=j;
                    }
                }
            }
            //找到一条边
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + "> 权值:" + miniWeight);
            visited[h2]=1;
            miniWeight = 10000;
        }


    }


}

class MGraph{
    int vertexes; //图的结点个数
    char[] data;//结点数据
    int [][] weight;//存放边，邻接矩阵

    public MGraph(int vertexes) {
        this.vertexes = vertexes;
        data = new char[vertexes];
        weight = new int[vertexes][vertexes];
    }


}
