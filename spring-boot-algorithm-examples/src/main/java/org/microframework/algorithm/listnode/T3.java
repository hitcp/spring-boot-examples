package org.microframework.algorithm.listnode;

/**
 * @author Shaoyu Liu
 * @date 2022/4/9 18:45
 */
public class T3 {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode listNode2 = new ListNode(2, new ListNode(5, new ListNode(6)));
        ListNode listNode = mergeTwoLists(listNode1, listNode2);
        System.out.println(1);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            // 1.循环完l1后循环l1.next，l2还是原来的，返回递归确认l1.next取l1取值：取l1（传入的l1.next）还是取l2（传入的l2）
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            // 2.逻辑同上
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }

    }

}
