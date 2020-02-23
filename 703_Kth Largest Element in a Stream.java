class KthLargest {
    
    private PriorityQueue<Integer> pq;
    private int capacity;
    
    public KthLargest(int k, int[] nums) {
        pq = new PriorityQueue<>();
        capacity = k;
        for (int n : nums) {
            pq.offer(n);
            if (pq.size() > capacity) pq.poll();
        }
    }
    
    public int add(int val) {
        pq.offer(val);
        if (pq.size() > capacity) pq.poll();
        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */