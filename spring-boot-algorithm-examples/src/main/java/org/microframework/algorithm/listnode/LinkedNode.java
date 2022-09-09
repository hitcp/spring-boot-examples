package org.microframework.algorithm.listnode;

/**
 * @author Shaoyu Liu --- website: <a href="http://hitcp.cn">Hi TCP</a>
 * @date 2022-09-08
 */
public class LinkedNode<T> {

    T val;
    LinkedNode next;

    LinkedNode() {
    }

    public LinkedNode(T val, LinkedNode next) {
        this.val = val;
        this.next = next;
    }

    public LinkedNode(T val) {
        this.val = val;
    }



    public LinkedNode getNext() {
        return next;
    }

    public void setNext(LinkedNode next) {
        this.next = next;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }
}
