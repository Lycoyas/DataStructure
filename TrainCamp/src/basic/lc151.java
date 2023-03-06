package basic;

/**
 * @author Lycoyas
 * @create 2023-02-08 11:27
 */
public class lc151 {
    public static void main(String[] args) {

        new lc151().reverseWords("a good  example");

    }
    public String reverseWords(String s) {

        char[] chars = s.trim().toCharArray();
        reverse(chars);
        StringBuilder sb = new StringBuilder();
        String res = new String(chars);
        String[] s1 = res.split(" ");
        for (String str : s1) {
            if(str.equals("")){
                continue;
            }
            char[] chars1 = str.toCharArray();
            reverse(chars1);
            sb.append(new String(chars1));
            sb.append(" ");
        }
        return sb.toString().trim();



    }

    void reverse(char[] chars) {
        int left=0,right=chars.length-1;
        while(left<right){
            char temp=chars[right];
            chars[right]=chars[left];
            chars[left]=temp;
            left++;
            right--;
        }
    }

}
