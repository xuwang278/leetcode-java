class Solution {
	// Sol 1: try all pairs using LC 461 - TLE

	// Sol 2: 
	// https://www.youtube.com/watch?v=fH9clXXrS2Q
    public int totalHammingDistance(int[] nums) {
        int ans = 0;
        int mask = 1; // 0001 (第i为为1)
        int n = nums.length;
        for (int i = 0; i < 32; i++) { 
        	int count = 0; // 看每位nums数中有几个1
        	for (int num : nums)
        		if ((num & mask) != 0) count++; // Wrong: == 1, why? mask会变动, !=0 说明目前这位是1 (例如:  14: 1110 & 0010 (mask) == 0010 != 0) 说明倒数第二位非零
        	ans += (n - count) * count;
        	mask <<= 1; // 0010, 0100, 1000
        }
        return ans;
    }

    // same idea, another version
    public int totalHammingDistance(int[] nums) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0; i < 32; i++) {
        	int count = 0;
        	for (int num : nums)
        		count += (num >> i) & 1;
        	ans += (n - count) * count;
        }
        return ans;
    }
}