class Solution {
	// Pre:
    // Search an element in sorted Matrix
    // https://www.youtube.com/watch?v=dsPdwhRR_84

	// Solution 1: PriorityQueue - max heap
	// not take advantage of sorting feature of matrix
	// T: O(logk * n^2) = O(n^2)
	// S: O(k) = O(1)
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        Queue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
        	@Override
        	public int compare(Integer a, Integer b) {
        		return b - a;
        	}
        });

        // Queue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int n = matrix.length;
        for (int row = 0; row < n; row++) {
        	for (int col = 0; col < n; col++) {
        		pq.offer(matrix[row][col]);
        		if (pq.size() > k) pq.poll();
        	}
        }
        return pq.peek();
    }

    // Solution 2: Binary Search
    public int kthSmallest(int[][] matrix, int k) {
    	int n = matrix.length;
    	int lo = matrix[0][0], hi = matrix[n - 1][n - 1] + 1; // [lo, hi)
    	while (lo < hi) {
    		int mid = lo + (hi - lo) / 2;
    		int count = 0;
            for (int i = 0; i < n; i++) 
                count += upper_bound(matrix[i], mid);
            if (count >= k) hi = mid;
            else lo = mid + 1; 
    		
    	}
    	return lo;
    }

    // first index of i such that nums[i] > target
    // also equals to the # of items <= target in nums
    private int upper_bound(int[] nums, int target) {
        int lo = 0, hi = nums.length; // [lo, hi)
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > target) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

}