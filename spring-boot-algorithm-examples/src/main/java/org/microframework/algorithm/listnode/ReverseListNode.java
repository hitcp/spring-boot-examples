package org.microframework.algorithm.listnode;

/**
 * @author Shaoyu Liu --- website: <a href="http://hitcp.cn">Hi TCP</a>
 * @date 2022-09-08
 */
public class ReverseListNode {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        reverseListNodeByRecursive(listNode);
        System.out.println(listNode);
    }

    /**
     * 迭代
     * @param listNode
     * @return
     */
    private static ListNode reverseListNodeByRecursive(ListNode listNode) {
        // 定义下一个元素。反转链表其实就是以当前元素为准，将下一个元素指针指向当前元素，以此类推
        ListNode nextNode = null;
        while (listNode.next != null) {
            ListNode temp = listNode.next;
//            nextNode = listNode.next;
//            nextNode.next = listNode;
            nextNode.val = listNode.next.val;
            nextNode.next = temp;
        }
        return nextNode;
    }


}
