class Solution {
    public int removeElement(int[] nums, int val) {
    	// T: O(n)
		// S: O(1)
    	if (nums == null || nums.length == 0) return 0;
    	int v = 0;
    	for (int i = 0; i < nums.length; i++) {
    		
    		// find num other than val and place to v
    		if (nums[i] != val) {
    			nums[v++] = nums[i];
    		} 
    	}
    	return v;
    }

}

