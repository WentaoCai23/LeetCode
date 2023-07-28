package org.example.linked_list;

public class Solution {
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(7);
        myLinkedList.addAtHead(2);
        myLinkedList.addAtHead(1);
        myLinkedList.addAtIndex(3,0);
        myLinkedList.deleteAtIndex(2);
    }

    public ListNode removeElements(ListNode head, int val) {
        while(head != null && head.val == val){
            head = head.next;
        }
        if (head == null){
            return head;
        }
        ListNode point = head;
        while(point != null){
            if (point.next == null)
                return head;
            if (point.next.val == val){
                point.next = point.next.next;
                continue;
            }
            point = point.next;
        }
        return head;
    }

    public ListNode removeElementsV2(ListNode head, int val) {
        if (head == null){
            return head;
        }
        ListNode dummy = new ListNode(-1,head);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null){
            if (cur.val == val){
                pre.next = cur.next;
            }else{
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
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

class MyLinkedList {

    int size;
    ListNode head;

    public MyLinkedList() {
        size = 0;
        head = new ListNode(-1);
        head.next = null;
    }

    public int get(int index) {
        if (index < 0 || index >= size){
            return -1;
        }
        ListNode point = head;
        for (int i = 0; i <= index; i++){
            point = point.next;
        }
        return point.val;
    }

    public void addAtHead(int val) {
        ListNode n = new ListNode(val);
        n.next = head.next;
        head.next = n;
        size++;
    }

    public void addAtTail(int val) {
        ListNode point = head;
        while (point.next != null){
            point = point.next;
        }
        point.next = new ListNode(val);
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index == size){
            addAtTail(val);
            return;
        }
        if (index > size){
            return;
        }
        if (index <= 0){
            addAtHead(val);
            return;
        }
        ListNode point = head;
        for (int i = 0; i < index; i++){
            point = point.next;
        }

        ListNode n = new ListNode(val);
        n.next = point.next;
        point.next = n;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size){
            return;
        }
        ListNode point = head;
        for (int i = 0; i < index; i++){
            point = point.next;
        }
        point.next = point.next.next;
        size--;
    }

    //双指针法
    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        ListNode temp = head;
        while (cur != null){
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    //递归法
    public ListNode reverseListV2(ListNode head) {
        return reverse(head,null);
    }

    public ListNode reverse(ListNode cur, ListNode pre){
        if (cur == null){
            return pre;
        }
        ListNode tmp = cur.next;
        cur.next = pre;
        return reverse(tmp,cur);
    }
}
