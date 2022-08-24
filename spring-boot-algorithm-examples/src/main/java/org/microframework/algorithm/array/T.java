package org.microframework.algorithm.array;

import java.util.Arrays;

/**
 * @author Shaoyu Liu
 * @date 2022/4/16 21:41
 */
public class T {
    static char[][] chars = {
            "ABCD".toCharArray()
            , "EFGH".toCharArray()
            , "IJKL".toCharArray()};

    public static void main(String[] args) {
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                System.out.println("i:" + i + ",j:" + j);
                System.out.println("chars[i][j]:" + chars[i][j]);
                System.out.println("chars[i][j] -0:" + chars[i][j] + '0' + "0");
                System.out.println("chars[i]:" + Arrays.toString(chars[i]));
                System.out.println("chars[j]:" + Arrays.toString(chars[j]));
                System.out.println("====================================");
            }
        }
    }
}
