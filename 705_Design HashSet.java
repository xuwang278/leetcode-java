class MyHashSet {
    private static class ListNode {
        private int key;
        private ListNode next;

        public ListNode(int key, ListNode next) {
            this.key = key;
            this.next = next;
        }
    }    

    private ListNode[] set;

    // look for node with key starting from x as head
    private ListNode find(ListNode x, int key) {
        if (x == null) return null;
        if (x.key == key) return x;
        return find(x.next, key);
    }

    // remove node with key starting from x as head
    private ListNode remove(ListNode x, int key) {
        if (x == null) return null;
        if (x.key == key) return x.next;
        x.next = remove(x.next, key);
        return x;
    }

    /** Initialize your data structure here. */
    public MyHashSet() {
        set = new ListNode[10000];
    }
    
    public void add(int key) {
        int hash = key % 10000;
        ListNode x = find(set[hash], key);
        if (x != null) return; // key was there;
        set[hash] = new ListNode(key, set[hash]); // insert new node to head
    }
    
    public void remove(int key) {
        int hash = key % 10000;
        set[hash] = remove(set[hash], key);
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hash = key % 10000;
        return find(set[hash], key) != null;
    }
}