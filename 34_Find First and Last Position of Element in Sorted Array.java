class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return new int[] {-1, -1};
        }

        int l = lower_bound(nums, target);
        if (l == nums.length || nums[l] != target) {// 没找到
            return new int[] {-1, -1};
        }
   
        int r = upper_bound(nums, target); // 若r==nums.length 即没找到, 说明从l开始到array最后都是target
        return new int[] {l, r - 1};
    }
    
    // find the minimum index such that nums[idx] >= target
    private int lower_bound(int[] nums, int target) {
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
    
    // find the minimum index such that nums[idx] > target
    private int upper_bound(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > target) hi = mid;
            else lo = mid;
        }
        if (nums[lo] > target) return lo;
        if (nums[hi] > target) return hi;
        return hi + 1;
    }

}