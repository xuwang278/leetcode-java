class Solution {
	// Time: O(n)
    // Space: O(1)
    // in a sorted array, the kth largest element is at index k - 1
    public int findKthLargest(int[] nums, int k) {
        int lo = 0, hi = nums.length - 1;
        // corner case: [1], 1
        while (hi > lo) {
        	int i = partition(nums, lo, hi);
        	if (i < k - 1) lo = i + 1;
        	else if (i > k - 1) hi = i - 1;
        	else return nums[i];
        }
        return nums[lo]; // until there is only one left, which must be nums[k-1]; when lo == hi, corner case: [2,1], 2
    }

    private int partition(int[] nums, int lo, int hi) {
    	int i = lo, j = hi + 1;
    	int v = nums[lo];
    	while (true) {
    		while (nums[++i] > v) 
    			if (i == hi) break;

    		while (nums[--j] < v); // it'll stop when j == lo (nums[lo] = v)
    			// if (j == lo) break;

    		if (i >= j) break; // coner case: [99,99], 1

    		exch(nums, i, j);
    	}

    	exch(nums, lo, j);
    	return j;
    }

    private void exch(int[] nums, int i, int j) {
    	int swap = nums[i];
    	nums[i] = nums[j];
    	nums[j] = swap;
    }

    // PriorityQueue
    // O(N) best case / O(N^2) worst case running time + O(1) memory
    public int findKthLargest(int[] nums, int k) {
        // min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n : nums) {
            pq.offer(n);
            if (pq.size() > k) pq.poll();
        }
        return pq.peek();
    }

}