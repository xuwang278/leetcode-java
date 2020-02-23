class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;

        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        //for each element in nums, find the length of largest subset
        for (int i = 1; i < nums.length; i++) {
        	for (int j = i - 1; j >= 0; j--) {
        		if (nums[i] % nums[j] == 0)
        			dp[i] = Math.max(dp[i], dp[j] + 1);
        	}
        }

        //pick the index of the largest element in dp.
	    int maxIndex = 0;
	    for (int i = 1; i < nums.length; i++) {
	        maxIndex = dp[i] > dp[maxIndex] ?  i :  maxIndex;
	    }

	    //from nums[maxIndex] to 0, add every element belongs to the largest subset.
	    int temp = nums[maxIndex];
	    int curDp = dp[maxIndex];
	    for (int i = maxIndex; i >= 0; i--){
	        if (temp % nums[i] == 0 && dp[i] == curDp) {
	            res.add(nums[i]);
	            temp = nums[i];
	            curDp--;
	        }
	    }
	    return res;
    }

    // Solution 2: Bottom-up DP
    // T: O(n^2)
    // S: O(n)
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        
        Arrays.sort(nums);

		// dp[i]: largest length of divisible set ending with nums[i]
        int[] dp = new int[nums.length]; 
        Arrays.fill(dp, 1);

        // subset consists of nums[i] with next index solution[i]
        int[] solution = new int[nums.length];
        Arrays.fill(solution, -1);

        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
        	for (int j = 0; j < i; j++) {
        		if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
        			dp[i] = dp[j] + 1;
        			solution[i] = j;
        		}
        	}

        	if (dp[i] > dp[ans]) ans = i;
        }

        for (int i = ans; i != -1; i = solution[i]) res.add(0, nums[i]);
        return res;
    }

    // Solution 3: Top-down DP
    // T: O()
    // S: O()
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        
    	Arrays.sort(nums);
    	HashMap<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
        List<Integer> maxSubset = null;
        for (int i = 0; i < nums.length; i++) {
        	List<Integer> subset = dfs(i, nums, map);
        	if (maxSubset == null || subset.size() > maxSubset.size()) 
                maxSubset = subset;
        }
        return maxSubset;
    }

    private List<Integer> dfs(int idx, int[] arr,HashMap<Integer,List<Integer>> map) {
    	if (map.containsKey(idx)) return map.get(idx);

    	List<Integer> res = null;
    	for (int i = idx + 1; i < arr.length; i++) { // subset can be built from uncontinuous elements
    		if (arr[i] % arr[idx] == 0) {
    			List<Integer> next = dfs(i, arr, map);
    			if (res == null || next.size() > res.size()) res = next;
    		}
    	}
    	if (res == null) res = new ArrayList<>();
        else res = new ArrayList<>(res);
        res.add(0, arr[idx]);
        map.put(idx, res);
    	return res; // pass to upper level where copy is modified and saved, so on and so forth...
    }

}







