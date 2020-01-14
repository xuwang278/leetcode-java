class Solution {
    // Time: O(n)
    // Space: O(n)
    public int[] twoSum(int[] nums, int target) {
      	if (nums == null || nums.length == 0) 
      		return new int[2];

      	int[] result = new int[2];
      	// HashMap can only return values, not keys
      	// HashMap: key(nums[index]), value (index)
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
        	if (map.containsKey(target - nums[i])) {
        		result[0] = i;
        		result[1] = map.get(target - nums[i]);
        		return result;
        	} else map.put(nums[i], i);
        }
        return result;
    }
    
}