class Solution {
    // T: O(logn)
    // S: O(1)
    // std
    public int searchInsert(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] > target) hi = mid;
            else lo = mid;
        }
        if (nums[lo] == target) return lo;
        if (nums[hi] == target) return hi;
        if (target < nums[lo]) return lo;
        if (nums[lo] < target && target < nums[hi]) return hi;
        return hi + 1;
    }

    // lower bound 1
    public int searchInsert(int[] nums, int target) {
        int lo = 0, hi = nums.length; // [lo, hi)
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] >= target) hi = mid;
            else lo = mid + 1;
        }
        return lo; 
    }

    // lower bound 2
    public int searchInsert(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1; // [lo, hi]
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] >= target) hi = mid - 1;
            else lo = mid + 1;
        }
        return lo; 
    }

    // lower bound 3
    public int searchInsert(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] >= target) hi = mid;
            else lo = mid;
        }
        if (nums[lo] >= target) return lo;
        if (nums[hi] >= target) return hi;
        return hi + 1;
    }

}