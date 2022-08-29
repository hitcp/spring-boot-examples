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
    public void testFastListSpeed(){
        // 模拟数据量
        int data_size = 1000000;
        ArrayList<String> arrayList = new ArrayList<>();
        long arrayListStartTime = System.currentTimeMillis();
        for (int i = 0; i < data_size; i++) {
            arrayList.add(String.valueOf(i));
        }
        long arrayListCostTime = System.currentTimeMillis() - arrayListStartTime;
        System.out.println("arrayList operation cost time:" + arrayListCostTime);


        List<String> fastList = new FastList<>(String.class);
        long fastListStartTime = System.currentTimeMillis();
        for (int i = 0; i < data_size; i++) {
            fastList.add(String.valueOf(i));
        }
        long fastListCostTime = System.currentTimeMillis() - fastListStartTime;
        System.out.println("fastList operation cost time:" + fastListCostTime);

//        arrayList operation cost time:73
//        fastList operation cost time:50
    }
}