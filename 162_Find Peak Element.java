class Solution {
	// T: O(n)
	// S: O(n)
    public int findPeakElement(int[] nums) {
    	if (nums == null || nums.length == 0 || nums.length == 1) return 0;
    	if (nums.length == 2) return nums[0] > nums[1] ? 0 : 1;

    	int[] nums2 = new int[nums.length + 2];
    	nums2[0] = Integer.MIN_VALUE;
    	nums2[nums2.length - 1] = Integer.MIN_VALUE;
    	for (int i = 1; i <= nums2.length - 2; i++) {
    		nums2[i] = nums[i - 1];
    	}

    	for (int i = 1; i <= nums2.length - 2; i++) {
    		if (nums2[i] > nums2[i - 1] && nums2[i] > nums2[i + 1]) return i - 1;
    	}
    	return -1;
    }

    // T: O(logn)
	// S: O(1)
    public int findPeakElement(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[mid + 1]) hi = mid;
            else lo = mid;    
        }
        return nums[lo] > nums[hi] ? lo : hi;
    }

}

