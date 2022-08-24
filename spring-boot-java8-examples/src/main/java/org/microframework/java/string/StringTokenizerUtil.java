package org.microframework.java.string;

import java.util.StringTokenizer;

public class StringTokenizerUtil {

    public static void main(String[] args) {
        // 分词器 , 提取指定字符串的前半部分
        StringTokenizer stringTokenizer = new StringTokenizer("AB,C/D,E,F;/G", "/");

        // 返回到第一个符号前内容 AB,C
        stringTokenizer.nextToken();
        // 上面符号到此符号中间的内容（连带符号） /D,E,F
        stringTokenizer.nextToken(";");
        // 符号出现次数
        stringTokenizer.countTokens();
        // 第一个符号到下个符号内容 D,E,F
        stringTokenizer.nextElement();
        // 返回符号出现的的次数。 2
        stringTokenizer.countTokens();
        // 符号是否出现在内容中 true
        stringTokenizer.hasMoreTokens();
    }

}
