Initialize: head = k - 1, tail = 0;
insertFront: q[head] = val, head--;
insertTail: q[tail] = val, tail++;
deleteFront: head++;
deleteLast: tail--;
getFront: q[head + 1]
getLast: q[tail - 1]

class MyCircularDeque {
    private int[] q;
    private int cap;    
    private int head;
    private int tail;
    private int n;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        q = new int[k];
        cap = k;
        head = k - 1;
        tail = 0;
        n = 0;
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull()) return false;
        q[head] = value;
        head = (head - 1 + cap) % cap; // move left
        n++;
        return true;
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull()) return false;
        q[tail] = value;
        tail = (tail + 1) % cap; // move right
        n++;
        return true;
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) return false;
        head = (head + 1) % cap;
        n--;
        return true;
    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) return false;
        tail = (tail -1 + cap) % cap;
        n--;
        return true;
    }
    
    /** Get the front item from the deque. */
    public int getFront() {
        return isEmpty() ? -1 : q[(head + 1) % cap];
    }
    
    /** Get the last item from the deque. */
    public int getRear() {
        return isEmpty() ? -1 : q[(tail - 1 + cap) % cap];
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return n == 0;
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return n == cap;
    }
}

Sol 2: Linked List
class MyCircularDeque {
    private static class ListNode {
        private int val;
        private ListNode prev;
        private ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    private ListNode head;
    private ListNode tail;
    private int cap;
    private int n;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        cap = k;
        n = 0;
        head = new ListNode(-1);
        tail = new ListNode(-1);
        head.next = tail;
        tail.prev = head;
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    // insert new node between head and head.next
    public boolean insertFront(int value) {
        if (isFull()) return false;
        ListNode newNode = new ListNode(value);
        newNode.prev = head;
        newNode.next = head.next;
        head.next.prev = newNode;
        head.next = newNode;
        n++;
        return true;
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
     // insert new node between tail and tail.prev
    public boolean insertLast(int value) {
        if (isFull()) return false;
        ListNode newNode = new ListNode(value);
        newNode.prev = tail.prev;
        newNode.next = tail;
        tail.prev.next = newNode;
        tail.prev = newNode;
        n++;
        return true;
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) return false;
        ListNode first = head.next;
        head.next = first.next;
        first.next.prev = head;
        first.next = null;
        first.prev = null;
        n--;
        return true;
    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) return false;
        ListNode last = tail.prev;
        tail.prev = last.prev;
        last.prev.next = tail;
        last.next = null;
        last.prev = null;
        n--;
        return true;
    }
    
    /** Get the front item from the deque. */
    public int getFront() {
        if (isEmpty()) return -1;
        return head.next.val;
    }
    
    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty()) return -1;
        return tail.prev.val;
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return n == 0;
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return n == cap;
    }
}


/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */