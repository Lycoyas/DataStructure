package graph;

import java.util.*;

public class Graph {
    private ArrayList<String> vertexList;//存储顶点
    private int[][] edges;//存储邻接矩阵
    private int numOfEdges; //边的数目
    private boolean[] isVisited ;
    public static void main(String[] args) {
        int n=5;
        String VertexValue[] = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        for (String str : VertexValue) {
            graph.insertVertex(str);
        }
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);

        graph.showGraph();
        System.out.println("DFS");
        graph.dfs();
        System.out.println();

        System.out.println("BFS");
        graph.bfs();
    }

    //对一个结点进行广度优先遍历
    private void bfs(boolean[] isVisited, int i) {

        int u;//队列的头结点对应下标
        int w;//邻接结点w
        Deque<Integer> queue = new ArrayDeque();
            System.out.print(getValueByIndex(i) + " ->");
            isVisited[i] = true;

        queue.addLast(i);
        while (!queue.isEmpty()) {
            u=queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + " ->");
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //以u为前驱，找w的后面的下一个邻接结点
                w = getNextNeighbor(u, w);
            }
        }

    }

    public void bfs() {
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    //得到第一个邻接结点的下标w
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接结点的下标来获取下一个邻接结点
    public int getNextNeighbor(int v1, int v2) {

        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    //深度优先遍历
    public void dfs(boolean[] isVisited, int i) {
        System.out.print(getValueByIndex(i)+" ->");
        isVisited[i] = true;
        //查找结点i的第一个邻接结点
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if(!isVisited[w]){
                dfs(isVisited, w);
            }
            w = getNextNeighbor(i, w);
        }
    }

    public void dfs() {
        isVisited = new boolean[getNumOfVertex()];
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }



    public Graph(int n) {
        //初始化矩阵
        edges=new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges=0;
        isVisited = new boolean[n];
    }

    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //返回结点个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回结点i对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //返回v1和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //显示邻接矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }

}
