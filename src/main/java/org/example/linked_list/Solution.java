package org.example.linked_list;

public class Solution {
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(7);
        myLinkedList.addAtHead(2);
        myLinkedList.addAtHead(1);
        myLinkedList.addAtIndex(3, 0);
        myLinkedList.deleteAtIndex(2);
    }

    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return head;
        }
        ListNode point = head;
        while (point != null) {
            if (point.next == null)
                return head;
            if (point.next.val == val) {
                point.next = point.next.next;
                continue;
            }
            point = point.next;
        }
        return head;
    }

    public ListNode removeElementsV2(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        ListNode dummy = new ListNode(-1, head);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode res = head.next;
        ListNode pre = head;
        ListNode cur = head.next;

        while (true) {
            pre.next = cur.next;
            cur.next = pre;
            cur = pre.next;
            if (cur == null) {
                break;
            }
            if (cur.next == null) {
                break;
            } else {
                pre.next = cur.next;
                pre = cur;
                cur = cur.next;
                if (cur == null) {
                    break;
                }
            }
        }

        return res;
    }

    public ListNode swapPairsV2(ListNode head) {
        ListNode dumyhead = new ListNode(-1); // 设置一个虚拟头结点
        dumyhead.next = head; // 将虚拟头结点指向head，这样方面后面做删除操作
        ListNode cur = dumyhead;
        ListNode temp; // 临时节点，保存两个节点后面的节点
        ListNode firstnode; // 临时节点，保存两个节点之中的第一个节点
        ListNode secondnode; // 临时节点，保存两个节点之中的第二个节点
        while (cur.next != null && cur.next.next != null) { //不能反过来
            temp = cur.next.next.next;
            firstnode = cur.next;
            secondnode = cur.next.next;
            cur.next = secondnode;       // 步骤一
            secondnode.next = firstnode; // 步骤二
            firstnode.next = temp;      // 步骤三
            cur = firstnode; // cur移动，准备下一轮交换
        }
        return dumyhead.next;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1,head);
        ListNode pre = dummy;
        ListNode slow = head;
        ListNode fast = slow;
        for (int i = 0; i < n; i++){
            fast = fast.next;
        }

        if (fast == null){
            return head.next;
        }

        while (fast != null){
            fast = fast.next;
            slow = slow.next;
            pre = pre.next;
        }

        pre.next = slow.next;

        return dummy.next;

    }

    public ListNode removeNthFromEndV2(ListNode head, int n){
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;

        ListNode fastIndex = dummyNode;
        ListNode slowIndex = dummyNode;

        //只要快慢指针相差 n 个结点即可
        for (int i = 0; i < n  ; i++){
            fastIndex = fastIndex.next;
        }

        while (fastIndex.next != null){
            fastIndex = fastIndex.next;
            slowIndex = slowIndex.next;
        }

        //此时 slowIndex 的位置就是待删除元素的前一个位置。
        //具体情况可自己画一个链表长度为 3 的图来模拟代码来理解
        slowIndex.next = slowIndex.next.next;
        return dummyNode.next;
    }

    //暴力
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode cura = headA;
        ListNode curb = headB;
        while (cura != null){
            curb = headB;
            while (curb != null){
                if (cura == curb){
                    return cura;
                }
                curb = curb.next;
            }
            cura = cura.next;
        }
        return null;
    }

    //减少遍历次数，相交点后面的节点必然相同，多出的那一段不需要遍历。
    public ListNode getIntersectionNodeV2(ListNode headA, ListNode headB) {
        ListNode cura = headA;
        ListNode curb = headB;
        int counta = 0;
        int countb = 0;
        while (cura != null){
            counta++;
            cura = cura.next;
        }
        while (curb != null){
            countb++;
            curb = curb.next;
        }

        cura = headA;
        curb = headB;

        if (counta > countb){
            int num = counta - countb;
            for (int i = 0; i < num; i++){
                cura = cura.next;
            }
        }else{
            int num = countb - counta;
            for (int i = 0; i < num; i++){
                curb = curb.next;
            }
        }

        while (cura != null){
            if (cura == curb){
                return cura;
            }
            cura = cura.next;
            curb = curb.next;
        }
        return null;
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {// 有环
                ListNode index1 = fast;
                ListNode index2 = head;
                // 两个指针，从头结点和相遇结点，各走一步，直到相遇，相遇点即为环入口
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
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
        if (index < 0 || index >= size) {
            return -1;
        }
        ListNode point = head;
        for (int i = 0; i <= index; i++) {
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
        while (point.next != null) {
            point = point.next;
        }
        point.next = new ListNode(val);
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index == size) {
            addAtTail(val);
            return;
        }
        if (index > size) {
            return;
        }
        if (index <= 0) {
            addAtHead(val);
            return;
        }
        ListNode point = head;
        for (int i = 0; i < index; i++) {
            point = point.next;
        }

        ListNode n = new ListNode(val);
        n.next = point.next;
        point.next = n;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        ListNode point = head;
        for (int i = 0; i < index; i++) {
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
        while (cur != null) {
            temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    //递归法
    public ListNode reverseListV2(ListNode head) {
        return reverse(head, null);
    }

    public ListNode reverse(ListNode cur, ListNode pre) {
        if (cur == null) {
            return pre;
        }
        ListNode tmp = cur.next;
        cur.next = pre;
        return reverse(tmp, cur);
    }
}
