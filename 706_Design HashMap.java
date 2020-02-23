class MyHashMap {
    private static class ListNode {
        private int key, val;
        private ListNode next;

        public ListNode(int key, int val, ListNode next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    private ListNode[] map;

    // look for node with key starting from x as head
    private ListNode find(ListNode x, int key) {
        if (x == null) return null;
        if (x.key == key) return x;
        return find(x.next, key);
    }

    // iterative version
    private ListNode find(ListNode x, int key) {
        while (x != null) {
            if (x.key == key) return x;
            x = x.next;
        }
        return null;
    }

    // remove node with key starting from x as head
    private ListNode remove(ListNode x, int key) {
        if (x == null) return null;
        if (x.key == key) return x.next;
        x.next = remove(x.next, key);
        return x;
    }

    // iterative version
    private ListNode remove(ListNode x, int key) {
        ListNode dummy = new ListNode(-1, -1, x);
        ListNode pre = dummy;
        while (x != null) {
            if (x.key == key) {
                pre.next = x.next;
                return dummy.next;
            } else {
                pre = x;
                x = x.next;
            }
        }
        return dummy.next;
    }

    /** Initialize your data structure here. */
    public MyHashMap() {
        map = new ListNode[10000];
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hash = key % 10000;
        ListNode x = find(map[hash], key);
        if (x != null) x.val = value; // key was there; update value
        else map[hash] = new ListNode(key, value, map[hash]); // insert new node to head
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int hash = key % 10000;
        ListNode x = find(map[hash], key);
        if (x != null) return x.val;
        return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hash = key % 10000;
        map[hash] = remove(map[hash], key);
    }
}