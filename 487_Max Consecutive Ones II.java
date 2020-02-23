class Solution {
	// T: O(n)
	// S: O(1)
	// two pointers
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0, zero = 0, k = 1;
        int lo = 0, hi = 0;
        while (hi < nums.length) {
            if (nums[hi] == 0) {
                zero++;
            }
            hi++;
            
            while (zero > k) {
                if (nums[lo] == 0) zero--;
                lo++;
            }
            max = Math.max(max, hi - lo);
        }
        return max;
    }
}