package org.microframework.algorithm.array;

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
        reverseListMethod1(listNode);
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
     * 反转链表
     * 方法一：迭代法[时间复杂度O(N),空间复杂度O(1)]
     * 方法二：栈
     * 方法三：新建链表法
     * 方法四：递归法
     * 总结：链表迭代只能从第一个元素开始循环，因为不知道链表有多少元素，只能一个个往下查并修改
     * @param node
     */
    public static ListNode reverseListMethod1(ListNode node) {
        if (node == null) {
            return null;
        }
        // 当前节点的前一个元素（必须有上一个元素定义，比如你循环第2个元素的时候需要指向第1个元素，如果不定义这个字段那么你只知道元素2后一个指向元素3，而不知道元素的上一个是元素1）
        ListNode previousNode = null;
        // 当前节点（循环必要条件）
        ListNode currentNode = node;
        while (currentNode != null) {
            //步骤一：当前节点保存旧指针 （当前节点的下一个节点，不保存就会丢失，currentNode.next只是一个指针，指向下一个元素！）
            ListNode currentNodeIndex = currentNode.next;
            //步骤二：当前节点指向新数据（反转指针，循环1的时候指向null，循环2的时候指向1...）
            currentNode.next = previousNode;
            //步骤三：当前节点移动位置：把元素和他的属性向前移动
            previousNode = currentNode;
            // 步骤四：更新循环条件，开启下次循环
            currentNode = currentNodeIndex;
        }
        // 最后所有的元素都会放到它前面一个元素里面，所以直接返回previousNode即可
        return previousNode;
    }


}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
