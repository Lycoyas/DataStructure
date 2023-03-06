package algorithm.dijkstra;

import java.util.Arrays;

/**
 * @author Lycoyas
 * @create 2022-09-13 14:45
 */
public class DijkstraAlgorithm {
    public static void main(String[] args) {

        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;// 表示不可以连接
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        //创建 Graph 对象
        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.dsj(2);
        graph.showDijkstra();

    }


}

class Graph{
    private char[] vertex;
    private int[][] matrix;
    private VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex=vertex;
        this.matrix = matrix;
    }

    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    /**
     * Dijkstra
     * @param index 出发顶点下标
     */
    public void dsj(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);//更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
        for (int j = 1; j < vertex.length; j++) {
            index = vv.updateArr();//选择并返回新的访问顶点
            update(index);
        }

    }

    //更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
    private void update(int index) {
        int len=0;
        for (int j = 0; j < matrix[index].length; j++) {
            //len表示 出发顶点到index顶点的距离 + index到j 的距离的和
            len = vv.getDis(index) + matrix[index][j];
            if (!vv.in(j) && len < vv.getDis(j)) {
                vv.updatePre(j, index);
                vv.updateDis(j, len);
            }

        }
    }

    public void showDijkstra() {
        vv.show();
    }



}

class VisitedVertex {

    // 记录各个顶点是否访问过 1 表示访问过,0 未访问,会动态更新
    public int[] already_arr;
    // 每个下标对应的值为前一个顶点下标, 会动态更新
    public int[] pre_visited;
    // 记录出发顶点到其他所有顶点的距离,比如 G 为出发顶点，就会记录 G 到其它顶点的距离，会动态更新，求的最短距离就会存放到 dis
    public int[] dis;

    /**
     *
     * @param length 顶点个数
     * @param index 出发顶点
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        //初始化 dis
        Arrays.fill(dis, 65535);
        this.already_arr[index]=1;//出发顶点被访问
        this.dis[index]=0;//设置出发顶点的访问距离为0
    }

    /**
     * 判断index顶点是否被访问过
     * @param index
     * @return
     */
    public boolean in(int index) {
        return already_arr[index]==1;
    }


    /**
     * 更新出发顶点到index顶点的距离
     * @param index
     * @param len 距离
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 更新pre顶点的前驱为index顶点
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     * @param index
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     *继续选择并返回新的访问顶点
     *
     */
    public int updateArr() {
        int min=65535,index=0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index=i;
            }
        }
        already_arr[index]=1;
        return index;
    }

    //显示最后结果
    public void show() {

        System.out.println("=========================");
        for (int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();
        char[] vertex = { 'A', 'B', 'C', 'D', 'E', 'F', 'G' };
        int count=0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count]+"("+i+")");
            }else{
                System.out.print("N ");
            }
            count++;
        }
        System.out.println();
    }


}
