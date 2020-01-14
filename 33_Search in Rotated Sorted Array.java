class Solution {

    // Sol 4:
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int minIndex = findMinIndex(nums);
        if (nums[minIndex] == target) return minIndex;
        int ans = binarySearch(nums, target, 0, minIndex - 1);
        if (ans != -1) return ans;
        return binarySearch(nums, target, minIndex + 1, nums.length - 1);
    }
    
    // LC 153
    private int findMinIndex(int[] nums) {     
        int lo = 0, hi = nums.length - 1;
        while (lo + 1 < hi) {
            if (nums[lo] < nums[hi]) return lo; // searching has been reduced to sorted sub-range
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[lo]) lo = mid; // approach to min by halving, mid is at increasing area but the min is on the right side
            else if (nums[mid] < nums[lo]) hi = mid; // min is on the left side
            else lo++; // handle duplicates
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

    
    // T: O(logn)
	// S:O(1)
    // Template 1:
    public int search(int[] nums, int target) {
    	if (nums == null || nums.length == 0) return -1;
    	
        int len = nums.length;
    	int lo = 0, hi = len - 1; // [lo, hi]
    	while (lo + 1 < hi) { 
            int mid = lo + (hi - lo) / 2;
    		if (nums[mid] == target) return mid;
            if (nums[mid] > nums[lo]) { // mid can't equal to 0
                if (nums[lo] <= target && target < nums[mid]) 
                    hi = mid; // Monotonic interval: apply binary search to narrow range
                else lo = mid;
            } else if (nums[mid] < nums[lo]) { 
                if (nums[mid] < target && target <= nums[hi]) 
                    lo = mid; // Monotonic interval: apply binary search to narrow range
                else hi = mid;
            }
        }
        // now lo and hi have not been checked
        if (nums[lo] == target) return lo;
        if (nums[hi] == target) return hi;
        return -1;
    }

    // Template 2:
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int lo = 0, hi = nums.length - 1; // [lo, hi]
        int len = nums.length;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] >= nums[lo]) { // left part; >= works when lo = hi, otherwise loop won't terminate
                if (nums[lo] <= target && target < nums[mid])
                    hi = mid - 1;
                else lo = mid + 1;
            } else { // right part
                if (nums[mid] < target && target <= nums[hi]) 
                    lo = mid + 1;
                else hi = mid - 1;
            }
        }
        // now lo == 0, hi == -1 or lo == len, hi = len - 1
        return -1;
    }

    // Template 3:
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;

        int len = nums.length;
        int lo = 0, hi = len; // [lo, hi)
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] >= nums[lo]) {
                if (nums[lo] <= target && target < nums[mid])
                    hi = mid;
                else lo = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[hi - 1]) 
                    lo = mid + 1;
                else hi = mid;
            }
        }
        // now lo == hi == nums.length
        return -1;
    }

    // Sol 4: lower_bound？
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (nums[m] >= nums[l]) {
                if (target <= nums[m] && target >= nums[l]) r = m;
                else l = m + 1;
            } else {
                if (target > nums[m] && target <= nums[r]) l = m + 1;
                else r = m;
            }
        }
        return nums[l] == target ? l : -1;
    }

    

}


