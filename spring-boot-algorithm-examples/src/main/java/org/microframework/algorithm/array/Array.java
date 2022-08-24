package org.microframework.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Shaoyu Liu
 * @date 2022/4/10 16:23
 */

class Array {

    public static void main(String[] args) {
        intersect(new int[]{1, 1, 2, 2}, new int[]{2, 2});
        merge(new int[]{1,2,3,0,0,0},3,new int[]{2,5,6},3);
    }

    /**
     * 删除两个数组重复元素：双指针算法（快慢指针）
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        /*1. 排序。排好序后就变成了如下所示排好序的数组
         * [1,1,2,2]
         * [2,2]
         * */
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                // 2.数组1小就数组1++
                i++;
            } else if (nums2[j] < nums1[i]) {
                // 3.数组2小就数组2++
                j++;
            } else {
                // 4.相等就存起来重复元素
                list.add(nums1[i]);
                i++;
                j++;
            }

        }

        // 5.list转换array
        int[] res = new int[list.size()];
        for (int k = 0; k < list.size(); k++) {
            res[k] = list.get(k);
        }
        return res;
    }

    /**
     * 合并两个有序数组
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2,0,nums1,m,n);
        Arrays.sort(nums1);
        System.out.println(1);
    }

}
