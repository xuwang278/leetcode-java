class Solution {
	// Solution 1: total sort and merge
	// T: O(nlogn)
	// S: O(n)
    public void wiggleSort(int[] nums) {
    	int n = nums.length, mid = (n + 1) / 2; // fits odd/even when merging
    	int[] copy = Arrays.copyOf(nums, n);

    	Arrays.sort(copy);

    	// to scatter duplicates:
    	// copy each half of copy to nums backwards so that duplicates distribute
    	for (int i = mid - 1, j = 0; i >= 0; i--, j += 2) nums[j] = copy[i]; // valley
    	for (int i = n - 1, j = 1; i >= mid; i--, j += 2) nums[j] = copy[i]; // peak
    }

	// Solution 2: total sort is overkill
	// T: O(n)
	// S: O(n)
	public void wiggleSort(int[] nums) {	
		int n = nums.length, mid = (n + 1) / 2;
    	int[] copy = Arrays.copyOf(nums, n);

    	int median = findKthSmallest(copy, mid); 
        // copy is partially sorted: duplicates may on both sides
        
        // leetcode p75
        // bring duplicates together
        // copy = [< median] + [= median] + [> median], Ascending
        int lo = 0, hi = copy.length - 1, cur = 0;
		while (cur <= hi) {
			if (copy[cur] < median)
				exch(copy, lo++, cur++);
			else if (copy[cur] > median)
				exch(copy, hi--, cur);
			else cur++;
		}

		// merge
    	for (int i = mid - 1, j = 0; i >= 0; i--, j += 2) nums[j] = copy[i];
    	for (int i = n - 1, j = 1; i >= mid; i--, j += 2) nums[j] = copy[i];
	}
	
	// leetcode p215
    private int findKthSmallest(int[] nums, int k) {
        int lo = 0, hi = nums.length - 1;
        while (hi > lo) {
            int i = partition(nums, lo, hi);
            if (i < k) lo = i + 1;
            else if (i > k) hi = i - 1;
            else return nums[i];
        }
        return nums[lo];
    }

    // 2-way partition （Ascending）
    private int partition(int[] nums, int lo, int hi) {
        int i = lo, j = hi + 1, v = nums[lo];
        while (true) {
            while (nums[++i] < v) if (i == hi) break;
            while (nums[--j] > v) if (j == lo) break;
            if (i >= j) break; 
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

}