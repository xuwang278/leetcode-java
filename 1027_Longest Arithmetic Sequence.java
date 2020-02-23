class Solution {
	// Sol 1: Hash Map
    // O(n^2)
    public int longestArithSeqLength(int[] A) {
        int n = A.length;
        if (n < 2) return n;
        int max = 0;
        Map<Integer, Integer>[] maps = new HashMap[n];
        // maps[i]: A[i]与其前方各数所构成的差值 -> 数列长度
        for (int i = 0; i < n; i++) {
        	maps[i] = new HashMap<>(); // initilize hash map
        	for (int j = 0; j < i; j++) { // 求与前面每个数的差值
        		int diff = A[i] - A[j];
        		if (maps[j].get(diff) == null) { 
        			maps[i].put(diff, 2); // 新等差数列
        		} else maps[i].put(diff, maps[j].get(diff) + 1); // 如果以j结尾所构成的差值中存在diff, 那么nums[i]放入该序列中
        		max = Math.max(max, maps[i].get(diff));
        	}
        }
        return max;
    }

    // Sol 2: DP
    public int longestArithSeqLength(int[] A) {
    	int n = A.length;
        if (n < 2) return n;
    	int[][] dp = new int[n][n]; // dp[i][j]: ends with nums[i], nums[j] and difference is (nums[i] - nums[j]) longest arithmetic sequence
    	int[] index = new int[20001]; // 
    	Arrays.fill(index, -1); // when a num show up, change -1 to its index
    	int ans = 2;

    	for (int i = 0; i < n; i++) {
    		Arrays.fill(dp[i], 2);
    		for (int j = i + 1; j < n; j++) {
    			int prev = A[i] * 2 - A[j];
    			if (prev < 0 || index[prev] == -1) continue;
    			dp[i][j] = dp[index[prev]][i] + 1;
    			ans = Math.max(ans, dp[i][j]);
    		}
    		index[A[i]] = i;
    	}

    	return ans;
    }
}


