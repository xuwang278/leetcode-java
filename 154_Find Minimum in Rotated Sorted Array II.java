class Solution {
	// T: T(n) = O(logn), worst case O(n)
    // S: O(1)
    public int findMin(int[] nums) {
        //if (nums == null || nums.length == 0) return -1;
     	int lo = 0, hi = nums.length - 1;
     	while (lo + 1 < hi) {
     		if (nums[lo] < nums[hi]) return nums[lo];
     		int mid = lo + (hi - lo) / 2;
     		if (nums[mid] > nums[lo]) {
     			lo = mid;
     		} else if (nums[mid] < nums[lo]) {
     			hi = mid;
     		} else lo++; // handle dup: increment only 1 step rather than cut off half -> O(n) in worst case
     	}
     	return Math.min(nums[lo], nums[hi]);
    }

    public int findMin(int[] nums) {
        //if (nums == null || nums.length == 0) return -1;
     	return findMin(nums, 0, nums.length - 1);
    }

    private int findMin(int[] nums, int lo, int hi) {
     	// only 1 or 2 elements
     	if (lo + 1 >= hi) return Math.min(nums[lo], nums[hi]);

     	// sorted but not rotated
     	if (nums[lo] < nums[hi]) return nums[lo];

     	// on the next recursion, one of them MAY sorted OR rotated
     	int mid = lo + (hi - lo) / 2;
     	return Math.min(findMin(nums, lo, mid - 1), findMin(nums, mid, hi)); 
    }
}