class Solution {
	// T: O(n)
	// S: O(1)
    public int maxArea(int[] height) {
    	if (height == null || height.length == 0) return 0;
    	int max = 0;
    	int lo = 0, hi = height.length - 1;
    	while (lo < hi) {
    		int area = (hi - lo) * Math.min(height[lo], height[hi]);
    		max = Math.max(max, area);
    		if (height[lo] < height[hi]) lo++; // keep the taller plate
    		else hi--;
    	}
    	return max;
    }
}