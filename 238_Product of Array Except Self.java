class Solution {

    public int[] productExceptSelf(int[] nums) {
        int[] ans = new int[nums.length];
        Arrays.fill(ans, 1);
        for (int i = 1; i < nums.length; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }
        
        int r = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            ans[i] *= r;
            r *= nums[i];
        }
        return ans;
    }

    // Sol 1
	// T: O(n)
	// S: O(n)
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] lefts = new int[n];
        int[] rights = new int[n];
        int[] ans = new int[n];

        lefts[0] = 1;
        for (int i = 1; i < n; i++) {
            lefts[i] = lefts[i - 1] * nums[i - 1];
        }
        
        rights[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            rights[i] = rights[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            ans[i] = lefts[i] * rights[i];
        }

        return ans;
    }
    
    // Sol 2
    // T: O(n)
    // S: O(1)
    public int[] productExceptSelf(int[] nums) {
    	int n = nums.length;
        int[] ans = new int[n]; // not count as extra space
        ans[0] = 1;
        for (int i = 1; i < n; i++)
            ans[i] = ans[i - 1] * nums[i - 1];

        int right = 1; // accumulate product from rhs
        for (int i = n - 1; i >= 0; i--) {
            ans[i] *= right;
            right *= nums[i];
        }
        return ans;
    }

