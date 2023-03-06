package tree.huffmancode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        byte[] huffmanCodeBytes = huffmanZip(str.getBytes());
        System.out.println(Arrays.toString(huffmanCodeBytes));

        byte[] decode = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println(new String(decode));

        String srcFile = "C:\\Users\\admin\\Desktop\\Uninstall.xml";
        String dstFile = "C:\\Users\\admin\\Desktop\\dst.zip";
        zipFile(srcFile,dstFile);

        //测试解压
        String zipFile = "C:\\\\Users\\\\admin\\\\Desktop\\\\dst.zip";
        String dstFile2 = "C:\\\\Users\\\\admin\\\\Desktop\\\\Uninstall2.xml";
        unzipFile(zipFile, dstFile2);
        System.out.println("解压成功");

    }


    /**
     * 解压文件
     * @param zipFile
     * @param dstFile
     */
    public static void unzipFile(String zipFile, String dstFile) {
        OutputStream os = null;
        ObjectInputStream ois=null;
        InputStream is=null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes =(byte[]) ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 压缩文件
     * @param srcFile 源文件路径
     * @param dstFile 压缩后的目标文件路径
     */
    public static void zipFile(String srcFile, String dstFile) {
        //创建输出流
        OutputStream os = null;
        ObjectOutputStream oos=null;
        FileInputStream is=null;
        try {
            //创建文件输入流
             is = new FileInputStream(srcFile);
            //创建和源文件大小一样的byte[]
            byte[] b = new byte[is.available()];
            is.read(b);
            //获取到文件对应的Huffman编码
            byte[] huffmanBytes = huffmanZip(b);
            //创建文件的输出流，存放压缩文件
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            //Huffman编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            //以对象流的方式写入Huffman编码，为了以后恢复源文件时使用
            oos.writeObject(huffmanCodes);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
    }

    /**
     * 数据解压
     * @param huffmanCodes
     * @param huffmanBytes
     * @return 原来字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1.先得到huffmanBytes对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag,huffmanBytes[i]));
        }

        //反向Huffman编码表
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length();) {
            int count=1;
            boolean flag=true;
            Byte b = null;
            while (flag) {
                //取出一个'1' '0'
                String key = stringBuilder.substring(i, i + count);//直到匹配到一个字符
                b = map.get(key);
                if (b == null) {
                    count++;
                }else{
                    flag=false;
                }
            }
            list.add(b);
            i=i+count;
        }

        byte[] res = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }

        return res;
    }

    /**
     * 将byte转成二进制的字符串
     * @param b
     * @param flag 标志是否需要 补 高位
     * @return b对应的二进制的字符串  补码
     */
    private static String byteToBitString(boolean flag,byte b) {
        int temp=b;
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length()-8);
        }else{
            return str;
        }

    }

    /**
     * 封装各步骤
     * @param bytes 原始字符串对应的字节数组
     * @return 经过Huffman编码处理后的字节数组
     */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //创建Huffman树
        Node huffmanTreeRoot = createHuffmanTree(nodes);
        //生成Huffman编码表
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    //转换成Nodes集合
    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();


        //存储每一个byte出现的次数
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            counts.put(b, counts.getOrDefault(b, 0) + 1);
        }

        //把买个键值对转成Node对象，并加入nodes
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //创建Huffman树
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(0);
            nodes.remove(0);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    private static void preOrder(Node root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("树为空");
        }
    }

    //生成Huffman编码表
    //编码存放的Map<Byte,String>
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();


    //重载getCodes
    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            return null;
        }
        getCodes(root, "", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 将传入node结点的所有叶子结点的Huffman编码得到，并放入map集合
     * @param node 传入结点
     * @param code 路径：左: 0 右: 1
     * @param stringBuilder 用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder) {

        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (node != null) {
             if(node.data==null){//非叶子结点
                 getCodes(node.left, "0", stringBuilder2);
                 getCodes(node.right, "1", stringBuilder2);
             }else{
                 huffmanCodes.put(node.data, stringBuilder2.toString());
             }
        }
    }


    /**
     * 将字符串的byte[]数组，通过Huffman编码表，返回一个压缩后的byte[]
     * @param bytes
     * @param huffmanCodes
     * @return
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {

        StringBuilder stringBuilder = new StringBuilder();

        for(byte b:bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }

        //将字符串转成byte[]
        int len = (stringBuilder.length() + 7) / 8;

        //存储压缩后的byte[]
        byte[] huffmanCodeBytes = new byte[len];
        int index=0;
        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            }else{
                strByte = stringBuilder.substring(i, i + 8);
            }
            //将strByte 转成 byte
            huffmanCodeBytes[index++] = (byte)Integer.parseInt(strByte, 2);
        }

        return huffmanCodeBytes;
    }

}

//创建Node，带数据和权值
class Node implements Comparable<Node> {
    Byte data;//存放数据本身
    int weight; //权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node o) {
        return this.weight-o.weight;
    }


    @Override
    public String toString() {
        return "Node[" +
                "data=" + data +
                ", weight=" + weight +
                ']';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }


}
