// Sol 1, inspired by algs4
// first, last both initialize to be 0
class MyCircularQueue {
    private int[] q;
    private int n;
    private int cap;
    private int first;
    private int last;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        q = new int[k];
        n = 0;
        cap = k;
        first = 0;
        last = 0;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()) return false;
        q[last++] = value;
        if (last == cap) last = 0;
        n++;
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) return false;
        first++;
        if (first == cap) first = 0;
        n--;
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()) return -1;
        return q[first];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()) return -1;
        if (last == 0) return q[cap - 1];
        return q[last - 1];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return n == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return n == cap;
    }
}

// Sol 1': 
// initialize: head = 0, tail = -1;
class MyCircularQueue {
    private int[] q;
    private int cap;
    private int head;
    private int tail;
    private int n;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        q = new int[k];
        cap = k;
        head = 0;
        tail = -1;
        n = 0;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()) return false;
        tail = (tail + 1) % cap;
        q[tail] = value;
        n++;
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) return false;
        head = (head + 1) % cap;
        n--;
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()) return -1;
        return q[head];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()) return -1;
        return q[tail];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return n == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return n == cap;
    }
}

// Sol 2: singly linked list + real first/last node
class MyCircularQueue {
    private Node first;
    private Node last;
    private int n;
    private int cap;
    
    // singly linked list
    private class Node {
        private int val;
        private Node next;
    }

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        first = null;
        last = null;
        n = 0;
        cap = k;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()) return false;
        Node oldLast = last;
        last = new Node();
        last.val = value;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        n++;
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) return false;
        first = first.next;
        n--;
        if (isEmpty()) last = null;
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if (first == null) return -1; // isEmpty()
        return first.val;
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if (last == null) return -1; // isEmpty()
        return last.val;
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return n == 0; // first == null
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return n == cap;
    }
}

// Sol 2': Linked List: doubly linked list + dummy head/tail node
class MyCircularQueue {
    private Node pre;
    private Node post;
    private int n;
    private int cap;
    
    private class Node {
        private int val;
        private Node prev;
        private Node next;
    }
    
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        pre = new Node();
        post = new Node();
        pre.next = post;
        post.prev = pre;
        n = 0;
        cap = k;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()) return false;
        Node last = post.prev;
        Node x = new Node();
        x.val = value;
        x.prev = last;
        x.next = post;
        post.prev = x;
        last.next = x;
        n++;
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) return false;
        Node first = pre.next;
        first.next.prev = pre;
        pre.next = first.next;
        n--;
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()) return -1;
        return pre.next.val;
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()) return -1;
        return post.prev.val;
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return n == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return n == cap;
    }
}

// Sol 2': Linked List: doubly linked list + dummy head/tail node
// Both head and tail are dummy ListNodes:
// enQueue adds a new node before the dummy tail node;
// deQueue removes the node next to the dummy head node.
class MyCircularQueue {
    private ListNode head;
    private ListNode tail;
    private int cap;
    private int n;

    private static class ListNode {
        private int val;
        private ListNode prev;
        private ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    /** Initialize your data structure here. Set the size of the queue to be k. */
    // head.prev = null
    // head.next = tail;
    // tail.prev = head;
    // tail.next = null
    public MyCircularQueue(int k) {
        cap = k;
        n = 0;
        head = new ListNode(-1);
        tail = new ListNode(-1);
        head.next = tail;
        tail.prev = head;
    }
    // head -> -1 -> <- -1 <- tail

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    // insert newNode between last node and tail
    public boolean enQueue(int value) {
        if (isFull()) return false;
        ListNode newNode = new ListNode(value);
        newNode.next = tail;
        newNode.prev = tail.prev;
        tail.prev.next = newNode;
        tail.prev = newNode;
        n++;
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) return false;
        ListNode toBeDeleted = head.next;
        toBeDeleted.next.prev = head;
        head.next = toBeDeleted.next;
        toBeDeleted.next = null;
        toBeDeleted.prev = null;
        n--;
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()) return -1;
        return head.next.val;
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()) return -1;
        return tail.prev.val;
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return n == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return n == cap;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */