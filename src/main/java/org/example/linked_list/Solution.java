package org.example.linked_list;

public class Solution {
    public static void main(String[] args) {

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
