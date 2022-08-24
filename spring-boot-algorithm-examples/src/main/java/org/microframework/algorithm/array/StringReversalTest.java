package org.microframework.algorithm.array;

/**
 * 翻转字符串
 */
public class StringReversalTest {
    public static void main(String[] args) {
        String str = "hello";
        char[] chars = SolutionWhile.reverseString(str.toCharArray());
        System.out.println(chars);
        char[] chars1 = SolutionFor.reverseString(str.toCharArray());
        System.out.println(chars1);
    }
}

/**
 * while方式反转书字符串
 */
class SolutionWhile {
    public static char[] reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
        return s;
    }
}

/**
 * for方式反转字符串
 */
class SolutionFor {
    public static char[] reverseString(char[] s) {
        for(int l =0,r=s.length-1;l<r;l++,r--){
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
        }
        return s;
    }
}