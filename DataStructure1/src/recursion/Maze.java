package recursion;

public class Maze {
    public static void main(String[] args) {
        //迷宫地图
        int[][] map = new int[8][7];

        //上下置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //设置挡板
        map[3][1]=1;
        map[3][2]=1;
        map[1][2]=1;
        map[2][2]=1;

        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        setWay(map, 1, 1);

        System.out.println("===============================");
        //输出地图
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    //递归回溯找路
    //策略：下-》右-》上-》左，如果该点走不通，再回溯
    /**
     *
     * @param map 迷宫地图
     * @param i  出发位置
     * @param j  出发位置
     * @return
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if(map[6][5]==2){//通路找到
            return true;
        }else{
            if(map[i][j]==0){//如果当前这个点还没有走过
                //策略：下-》右-》上-》左
                map[i][j]=2;
                if(setWay(map,i+1,j)){
                    return true;
                }else if(setWay(map,i,j+1)){
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                }else {
                    //走不通
                    map[i][j]=3;
                    return false;
                }
            }else{//map[i][j]!=0,可能是1，2，3
                return false;
            }
        }

    }

}
