package org.microframework.java.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 主要用来操作集合
 *
 * @author Shaoyu Liu
 * @date 2022/4/2 14:36
 */
public class CollectionsTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("bbb");
        list.add("ccc");
        list.add("aaa");
        // 返回一个空数组
        Collections.emptyList();
        // 添加元素
        Collections.addAll(list,"eee","fff");
        // 排序
        Collections.sort(list);
        // 倒序
        Collections.reverse(list);
        // 二分查找下标
        int index = Collections.binarySearch(list, "aaa");

    }
}
