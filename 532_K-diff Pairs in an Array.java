class Solution {
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0) return 0;
        
        Map<Integer, Integer> map = new HashMap<>(); // value -> freq
        int cnt = 0;
        for (int n : nums) map.put(n, map.getOrDefault(n, 0) + 1);

        for (int key : map.keySet()) {
        	if (k == 0) {
        		if (map.get(key) > 1) // 查看有多少重复元素 （freq > 1）
        			cnt++;
        	} else {
        		// 只考虑加k，防止重复；
        		// e.g. nums = {1,3}, k = 2, 答案为1
        		// 如果加减k都考虑，则1 + 2 = 3， 3 - 2 = 1，错误答案为2
        		if (map.containsKey(key + k)) 
        			cnt++;
        	}
        }
        return cnt;
    }
}