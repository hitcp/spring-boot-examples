package org.microframework.algorithm.listnode;

import java.util.ArrayList;

/**
 * 回文链表判断
 * @author Shaoyu Liu
 * @date 2022/4/9 20:12
 */
public class T4 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3,new ListNode(1))));
        boolean palindrome = isPalindrome(head);
        System.out.println(1);
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        // 2.1 把数据放到数组中
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (head != null) {
            arrayList.add(head.val);
            head = head.next;
        }
        // 2.2 双指针对比
        int headIndex = 0;
        int tailIndex = arrayList.size() -1 ;
        while (headIndex < tailIndex) {
            if (!arrayList.get(headIndex).equals(arrayList.get(tailIndex))) {
                return false;
            }
            headIndex++;
            tailIndex--;
        }
        return true;
    }


}


