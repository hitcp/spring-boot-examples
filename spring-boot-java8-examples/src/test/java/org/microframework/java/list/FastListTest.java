package org.microframework.java.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shaoyu Liu
 * @date 2022-08-29
 */
class FastListTest {
    /**
     * 测试FastList和ArrayList add方法速度
     */
    @Test
    void testFastListSpeed() {
        // 模拟数据量 （超过10w FastList速度不如ArrayList）
        int data_size = 100000;
        ArrayList<Integer> arrayList = new ArrayList<>();
        long arrayListStartTime = System.currentTimeMillis();
        for (int i = 0; i < data_size; i++) {
            arrayList.add(i);
        }
        // 在删的过程中元素一直在减少，所以删掉一半的时候元素只剩下 data_size/2，此时再往后查找元素去删除就会 IndexOutOfBoundsException
        for (int i = 0; i < data_size / 2; i++) {
            arrayList.remove(i);
        }
        long arrayListCostTime = System.currentTimeMillis() - arrayListStartTime;
        System.out.println("arrayList operation cost time:" + arrayListCostTime);


        List<Integer> fastList = new FastList<>(Integer.class);
        long fastListStartTime = System.currentTimeMillis();
        for (int i = 0; i < data_size; i++) {
            fastList.add(i);
        }
        // 在删的过程中元素一直在减少，所以删掉一半的时候元素只剩下 data_size/2，此时再往后查找元素去删除就会 IndexOutOfBoundsException
        for (int i = 0; i < data_size / 2; i++) {
            fastList.remove(i);
        }
        long fastListCostTime = System.currentTimeMillis() - fastListStartTime;
        System.out.println("fastList operation cost time:" + fastListCostTime);

//        arrayList operation cost time:73
//        fastList operation cost time:50
    }
}