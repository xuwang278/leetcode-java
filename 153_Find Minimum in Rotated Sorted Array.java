class Solution {
	// refer 33
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        
     	int lo = 0, hi = nums.length - 1;
     	while (lo + 1 < hi) {
            if (nums[lo] < nums[hi]) return nums[lo]; // searching has been reduced to sorted sub-range
     		int mid = lo + (hi - lo) / 2;
     		if (nums[mid] > nums[lo]) lo = mid; // approach to min by halving
     		else if (nums[mid] < nums[lo]) hi = mid;
     	}
        // now lo and hi haven't been checked
     	return Math.min(nums[lo], nums[hi]);
    }
    
    // T: T(n) = O(1) + O(n/2) = O(logn)
    // S: O(1)
    public int findMin(int[] nums) {
        //if (nums == null || nums.length == 0) return -1;
     	return findMin(nums, 0, nums.length - 1);
    }

    private int findMin(int[] nums, int lo, int hi) {
     	// only 1 or 2 elements
     	if (lo + 1 >= hi) return Math.min(nums[lo], nums[hi]);

     	// sorted but not rotated
     	if (nums[lo] < nums[hi]) return nums[lo];

     	// on the next recursion, one of them is sorted but not rotated: T(n) = O(1) + T(n/2) = O(lgn)
     	int mid = lo + (hi - lo) / 2;
     	return Math.min(findMin(nums, lo, mid - 1), findMin(nums, mid, hi)); 
    }
    
}