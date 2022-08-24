package org.microframework.algorithm.listnode;

/**
 * 根据下标删除链表节点
 *
 * @author Shaoyu Liu
 * @date 2022/4/9 14:15
 */
public class T2 {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        removeNthFromEnd(listNode, 3);
        reverseList(listNode);
        System.out.println();
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // 1.头部创建哑节点，避免后续删除头结点的判断
        ListNode dummy = new ListNode(0, head);
        int size = listSize(head);
        // 2.引用传递，修改当前节点（c）同时也会修改哑结点（dummy）数据
        ListNode c = dummy;
        int i = 1;
        int p = size - n + 1;
        while (i < p) {
            c = c.next;
            i++;
        }
        c.next = c.next.next;
        return dummy.next;
    }

    public static int listSize(ListNode head) {
        int i = 0;
        while (head != null) {
            head = head.next;
            i++;
        }
        return i;
    }

    /**
     * 反转链表（方法一：迭代）
     * @param node
     */
    public static void reverseList(ListNode node){
        if(node == null){
            return;
        }
        ListNode previousNode  = null; //
        ListNode currentNode = node; // 当前节点

        while (currentNode != null){
            ListNode currentNodeIndex = node.next; // 当前节点的下一个节点，不保存就会丢失，node.next只是一个指针，指向下一个元素！
            currentNodeIndex = previousNode; // 反转指针，循环1的时候指向null，循环2的时候指向1...
            currentNode = currentNodeIndex;
        }

    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
