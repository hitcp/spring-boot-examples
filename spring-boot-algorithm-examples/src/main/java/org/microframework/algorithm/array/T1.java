package org.microframework.algorithm.array;

/**
 * @author Shaoyu Liu
 * @date 2022/4/9 23:24
 */
public class T1 {
    public static void main(String[] args) {
        int leetcode = firstUniqChar("leetcodl");
        System.out.println();

    }

    public static int firstUniqChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            // indexOf从前面找，lastIndexOf从后面找，如果这个字母存在两次那么找到的下标肯定不同，如果只有一个那么都是同一个字母，下标必定相同
            if (s.indexOf(s.charAt(i)) == s.lastIndexOf(s.charAt(i)))
                return i;
        }
        return -1;
    }

}
