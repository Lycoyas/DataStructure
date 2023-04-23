package dscourse.trie;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Lycoyas
 * @create 2023-04-23 14:10
 * @description
 */
public class TrieSet {

    private static final int R = 256;

    private int size=0;

    private static class TrieNode{

        TrieNode[] next=new TrieNode[R];
        boolean isEnd=false;
    }

    private TrieNode root = null;

    public TrieSet() {

    }

    /***** 增 *****/

    // 在集合中添加元素 key
    public boolean add(String key) {

        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        if (contains(key)) {
            return false;
        }

        root = add(root, key, 0);
        size++;
        return true;
    }

    private TrieNode add(TrieNode node, String key, int i) {

        if (node == null) {
            node = new TrieNode();
        }

        if (i == key.length()) {
            node.isEnd = true;
            return node;
        }

        char c = key.charAt(i);
        node.next[c] = add(node.next[c], key, i + 1);
        return node;


    }

    /***** 删 *****/

    // 从集合中删除元素 key
    public boolean remove(String key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }

        if (!contains(key)) {
            return false;
        }

        root = remove(root, key, 0);
        size--;

        return true;


    }

    private TrieNode remove(TrieNode node, String key, int i) {

        if (i == key.length()) {
            node.isEnd = false;
        }else{
            char c = key.charAt(i);
            node.next[c] = remove(node.next[c], key, i + 1);
        }

        if (node.isEnd) {
            return node;
        }

        for (int c = 0; c < R; c++) {
            if (node.next[c] != null) {
                return node;
            }
        }

        return null;

    }

    /***** 查 *****/

    public boolean startsWith(String prefix) {
        TrieNode x = getNode(root, prefix);
        return x != null;
    }

    // 判断元素 key 是否存在集合中
    public boolean contains(String key) {

        if (key == null) {
            throw new IllegalArgumentException("key is null");

        }
        TrieNode x = getNode(root, key);
        return x != null && x.isEnd;

    }

    private TrieNode getNode(TrieNode node, String key) {
        TrieNode p = node;

        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (p == null) {
                return null;
            }
            p = p.next[c];
        }
        return p;
    }

    // 在集合中寻找 query 的最短前缀
    public String shortestPrefixOf(String query) {

        if (query == null) {
            return null;
        }

        TrieNode p = root;
        int i=0;
        while (i < query.length() && p != null) {

            if (p.isEnd) {
                break;
            }

            i++;
            char c = query.charAt(i);
            p=p.next[c];
        }

        if (p != null && p.isEnd) {
            return query.substring(0, i);
        }

        return "";

    }

    // 在集合中寻找 query 的最长前缀
    public String longestPrefixOf(String query) {
        if (query == null) {
            return null;
        }

        TrieNode p = root;
        int i=0;
        int len=0;
        while (i < query.length() && p != null) {

            if (p.isEnd) {
                len=i;
            }

            i++;
            char c = query.charAt(i);
            p=p.next[c];
        }

        if (p != null && p.isEnd) {
            len = i;
        }

        return query.substring(0, len);

    }

    // 在集合中搜索前缀为 prefix 的所有元素
    public List<String> keysWithPrefix(String prefix) {

        LinkedList<String> res = new LinkedList<>();

        TrieNode x = getNode(root, prefix);

        if (x == null) {
            return res;
        }

        traverse(x, new StringBuilder(prefix), res);
        return res;
    }

    private void traverse(TrieNode node, StringBuilder path, List<String> res) {

        if (node == null) {
            return;
        }

        if (node.isEnd) {
            res.add(path.toString());
        }

        for (char c = 0; c < R; c++) {
            path.append(c);
            traverse(node.next[c], path, res);
            path.deleteCharAt(path.length() - 1);
        }

    }



    // 通配符 . 匹配任意字符，返回集合中匹配 pattern 的所有元素
    public List<String> keysWithPattern(String pattern) {

        LinkedList<String> res = new LinkedList<>();
        traverse(root, new StringBuilder(), pattern, 0, res);
        return res;
    }

    private void traverse(TrieNode node, StringBuilder path, String pattern, int i, List<String> res) {

        if (node == null) {
            return;
        }

        if (i == pattern.length()) {
            if (node.isEnd) {
                res.add(path.toString());
            }
            return;
        }

        char c = pattern.charAt(i);

        if (c == '.') {

            for (char j = 0; j < R; j++) {
                path.append(j);
                traverse(node.next[j], path, pattern, i + 1, res);
                path.deleteCharAt(path.length() - 1);
            }

        }else{

            if(node.next[c]==null) {
                return;
            }

            path.append(c);
            traverse(node.next[c], path, pattern, i + 1, res);
            path.deleteCharAt(path.length() - 1);
        }

    }




    // 返回集合中元素的个数
    public int size() {

        return size;

    }

}
