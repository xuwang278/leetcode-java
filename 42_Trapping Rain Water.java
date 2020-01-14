class Solution {
	// for each bar, the water it can trap = min(highest_lhs, highest_rhs) - bar height
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int ans = 0;

        int n = height.length;
        int[] left_max = new int[n];
        int[] right_max = new int[n];
        
        left_max[0] = height[0];
        for (int i = 1; i < n; i++)
        	left_max[i] = Math.max(left_max[i - 1], height[i]);
        
        right_max[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--)
        	right_max[i] = Math.max(right_max[i + 1], height[i]);
   	
   		for (int i = 1; i < n - 1; i++) {
   			ans += Math.min(left_max[i], right_max[i]) - height[i];
   		}
   		
   		return ans;
   }

  public int trap(int[] height) {
    if (height == null || height.length < 3) return 0;

    int ans = 0;
    int leftMax = 0, rightMax = 0;
    int lo = 0, hi = height.length - 1;
    while (lo < hi) {
      leftMax = Math.max(leftMax, height[lo]);
      rightMax = Math.max(rightMax, height[hi]);
      if (leftMax < rightMax) {
        ans += leftMax - height[lo];
        lo++;
      } else {
        ans += rightMax - height[hi];
        hi--;
      }
    }
    return ans;
  }

}