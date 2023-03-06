package lc202210.lc811;

import com.sun.xml.internal.bind.v2.runtime.output.SAXOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lycoyas
 * @create 2022-10-05 10:48
 */
public class Solution {

    public static void main(String[] args) {

        String[] cpdomains = {"9001 discuss.leetcode.com"};

        List<String> strings = new Solution().subdomainVisits(cpdomains);

        System.out.println(strings);


    }


    public List<String> subdomainVisits(String[] cpdomains) {

        HashMap<String, Integer> map = new HashMap<>();

        for (String str : cpdomains) {

            String[] sp1=str.split (" ");
            int count = Integer.parseInt(sp1[0]);
            String[] split = sp1[1].split("\\.");
            String domain = "";
            for (int i = split.length-1; i >=0; i--) {
                if (i < split.length - 1) {
                    domain = "." + domain;
                }
                domain = split[i] + domain;
                map.put(domain, map.getOrDefault(domain, 0) + count);
            }
        }

        List<String> res = new ArrayList<>();

        for (Map.Entry<String, Integer> item : map.entrySet()) {
            res.add(item.getValue() + " " + item.getKey());
        }

        return res;

    }


}
