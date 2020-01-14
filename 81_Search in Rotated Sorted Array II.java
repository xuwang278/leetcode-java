class Solution {
  	// T: O(logn) - O(n)
	// S:O(1)
	public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
    	
    	int lo = 0, hi = nums.length - 1;
    	while (lo + 1 < hi) { 
    		int mid = lo + (hi - lo) / 2;
    		if (nums[mid] == target) return true;
            if (nums[mid] > nums[lo]) { 
                if (nums[lo] <= target && target < nums[mid]) hi = mid;
                else lo = mid;
            } else if (nums[mid] < nums[lo]) {
                if (nums[mid] < target && target <= nums[hi]) lo = mid;
                else hi = mid;
            } else lo++; // handle dup: increment only 1 step rather than cut off half -> O(n) in worst case
        }
        return nums[lo] == target || nums[hi] == target;
    }

    // Sol 2:
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;;
        int minIndex = findMinIndex(nums);
        if (nums[minIndex] == target) return true;
        int ans = binarySearch(nums, target, 0, minIndex - 1);
        if (ans != -1) return true;
        return binarySearch(nums, target, minIndex + 1, nums.length - 1) != -1;
    }
    
    // LC 153
    private int findMinIndex(int[] nums) {     
        int lo = 0, hi = nums.length - 1;
        while (lo + 1 < hi) {
            if (nums[lo] < nums[hi]) return lo; // searching has been reduced to sorted sub-range
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[lo]) lo = mid; // approach to min by halving
            else if (nums[mid] < nums[lo]) hi = mid;
            else lo++;
        }
        // now lo and hi haven't been checked
        return nums[lo] < nums[hi] ? lo : hi;
    }
    
    // perform binary search within [lo, hi] to find trget
    private int binarySearch(int[] nums, int target, int lo, int hi) {
        if (lo < 0 || hi >= nums.length || lo > hi) return -1;
     
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] > target) hi = mid;
            else lo = mid;
        }
        if (nums[lo] == target) return lo;
        if (nums[hi] == target) return hi;
        return -1;
    }
   
}