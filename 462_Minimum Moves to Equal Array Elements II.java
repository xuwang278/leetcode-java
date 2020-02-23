class Solution {
	// Solution 1: Quick Select
	// T: O(n)
	public int minMoves2(int[] nums) {
		int sum = 0;
		int median = select(nums, nums.length / 2);
		for (int i = 0; i < nums.length; i++) 
			sum += Math.abs(nums[i] - median);
		return sum;
	}

	private int select(int[] a, int k) {
		int lo = 0, hi = a.length - 1;
		while (lo < hi) {
			int i = partition(a, lo, hi);
			if (i > k) hi = i - 1;
			else if (i < k) lo = i + 1; // else if !!
			else return a[i];
		}
		return a[lo];
	}

	private int partition(int[] a, int lo, int hi) {
		int i = lo, j = hi + 1;
		int v = a[lo];
		while (true) {
			while (a[++i] < v) {
				if (i == hi) break;
			}
			while (v < a[--j]) {
				if (j == lo) break;
			}
			if (i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j; // j is at the ordered position, and a[0...j-1] <= a[j] <= a[j+1...a.length-1]
	}

	private void exch(int[] a, int i, int j) {
		int swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	// Sol 2: Sort
	// T: O(nlogn)
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        int cnt = 0;
        while (i < j) {
        	cnt += nums[j] - nums[i];
        	i++;
        	j--;
        }
        return cnt;
    }

}