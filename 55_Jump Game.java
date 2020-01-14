class Solution {
    // Greedy I
    // T: O(n)
    // S: O(1)
    public boolean canJump(int[] nums) {
    	int max = 0; // maxt index that can be reached so far
    	for (int i = 0; i < nums.length; i++) {
    		if (i > max) return false; // cannot make a progress
    		max = Math.max(max, nums[i] + i);
    	}
    	return true;
    }

    // Greedy II
    // T: O(n)
    // S: O(1)
    public boolean canJump (int[] nums) {
        int reach = -1; // maxt index that can be reached so far
        for (int i = 0; i < nums.length && i <= reach; i++) {
            reach = Math.max(reach, nums[i] + i);
            if (reach >= nums.length - 1) return true;
        }
        return false;
    }

}