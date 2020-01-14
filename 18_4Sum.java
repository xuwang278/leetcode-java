class Solution {
	// T: O(n^3)
	// S: O(n)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length < 4) return list;
        
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
        	if (i > 0 && nums[i] == nums[i - 1]) continue; // sequence is unique
        	for (int j = i + 1; j < nums.length - 2; j++) {
        		if (j > i + 1 && nums[j] == nums[j - 1]) continue;
        		int lo = j + 1, hi = nums.length - 1;
        		while (lo < hi) {
        			int sum = nums[i] + nums[j] + nums[lo] + nums[hi];
        			if (sum == target) {
        				list.add(Arrays.asList(nums[i], nums[j], nums[lo++], nums[hi--]));
        				while (lo < hi && nums[lo] == nums[lo - 1]) lo++;
    					while (lo < hi && nums[hi] == nums[hi + 1]) hi--;
        			} else if (sum < target) lo++;
        			else hi--;
        		}
        	}
        }
        return list;
    }


}