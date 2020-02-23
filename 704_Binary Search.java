class Solution {
    public int search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) lo = mid;
            else hi = mid;
        }
        if (nums[lo] == target) return lo;
        if (nums[hi] == target) return hi;
        return -1;
    }

    public int search(int[] nums, int target) {
        int lo = 0, hi = nums.length; // [lo, hi)
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] >= target) hi = mid;
            else lo = mid + 1;
        }
        // lo is the first i such that nums[i] >= target
        if (lo == nums.length) return -1;
        if (nums[lo] != target) return -1;
        return lo; 
    }
}