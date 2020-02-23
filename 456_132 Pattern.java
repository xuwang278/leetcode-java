class Solution {
    public boolean find132pattern(int[] nums) {
        if (nums.length < 3) return false;
        Integer low = null, high = null;
        int lo = 0, hi = 0;
        while (lo < nums.length - 1) {
        	while (lo < nums.length - 1 && nums[lo] >= nums[lo + 1]) lo++; 
        	// lo is at valley
        	int m = lo + 1;
        	while (m < nums.length - 1 && nums[m] <= nums[m + 1]) m++;
        	// m is at peak
        	int j = m + 1;
        	while (j < nums.length) {
        		if (nums[j] > nums[lo] && nums[j] < nums[m])
        			return true;
        		j++;
        	}
        }
        return false;
    }
}