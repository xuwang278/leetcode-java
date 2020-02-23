class MyLinkedList {
    // Solution 1: Doubly Linked List
    // helper data type
    private class ListNode {
        private int val;
        private ListNode next;
        private ListNode prev;
    }
    
    private ListNode pre; // sentinel before first item
    private ListNode post; // sentinel after last item
    private int n; // # of items on list
    
    /** Initialize your data structure here. */
    public MyLinkedList() {
        pre = new ListNode();
        post = new ListNode();
        pre.next = post;
        post.prev = pre;
        n = 0;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= n) return -1;
        ListNode cur = pre;
        while (cur != null && index >= 0) {
            cur = cur.next;
            index--;
        }
        return cur.val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    // O (1)
    public void addAtHead(int val) {
        ListNode first = pre.next;
        ListNode x = new ListNode();
        x.val = val;
        x.prev = pre;
        x.next = first;
        pre.next = x;
        first.prev = x;
        n++;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    // O(1)
    public void addAtTail(int val) {
        ListNode last = post.prev;
        ListNode x = new ListNode();
        x.val = val;
        x.next = post;
        x.prev = last;
        post.prev = x;
        last.next = x;
        n++;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    // O(n)
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > n) return;
        if (index == 0) {
            addAtHead(val);
            return;
        }

        if (index == n) {
            addAtTail(val);
            return;
        }

        ListNode cur = pre;
        while (cur != null && index >= 0) {
            cur = cur.next;
            index--;
        }
        
        ListNode before = cur.prev;
        ListNode x = new ListNode();
        x.val = val;
        x.prev = before;
        x.next = cur;
        before.next = x;
        cur.prev = x;
        n++;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    // O(n)
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= n) return;
        
        ListNode cur = pre;
        while (cur != null && index >= 0) {
            cur = cur.next;
            index--;
        }
        
        ListNode before = cur.prev;
        ListNode after = cur.next;
        before.next = after;
        after.prev = before;
        n--;
    }

}

// Solution 2: Singly Linked List
class MyLinkedList {
    
    private class Node {
        private int val;
        private Node next;
        public Node(int val) {
            this.val = val;
        }
        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
    
    private Node head;
    private Node tail;
    private int size;
    
    /** Initialize your data structure here. */
    public MyLinkedList() {
        size = 0;
    }
    
    private Node getNode(int index) {
        Node n = new Node(0, head);
        while (index-- >= 0) 
            n = n.next;
        return n;
    }
    
    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        return getNode(index).val;
    }
    
    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        head = new Node(val, head);
        if (size++ == 0) tail = head;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node n = new Node(val);
        if (size++ == 0) head = tail = n;
        else tail = tail.next = n;
    }
    
    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index < 0 || index > this.size) return;
        if (index == 0)  { this.addAtHead(val); return; }
        if (index == size) { this.addAtTail(val); return; }
        Node prev = this.getNode(index - 1);
        prev.next = new Node(val, prev.next);
        ++this.size;
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= this.size) return;
        Node prev = this.getNode(index - 1);
        prev.next = prev.next.next;
        if (index == 0) this.head = prev.next;
        if (index == this.size - 1) this.tail = prev;
        --this.size;
    }
}

