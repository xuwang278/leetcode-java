class Solution {
	// Solution 0: TEL
	// T: O(2^n)
	// S: O(n)
	public int integerBreak(int n) {
        if(n == 1) return 1; 
        int res = -1;
        for(int i=1;i<=n-1;i++)
            res = Math.max(res, Math.max(i*(n-i),i*integerBreak(n-i))); 
        return res;
    }

	// Solution 1: bottom-up DP
	// T: O(n^2)
	// S: O(n)
    public int integerBreak(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        return dfs(n, map);
    }

    private int dfs(int n, Map<Integer, Integer> map) {
    	if (n == 1) return 1;
    	if (mao.containsKey(n)) return map.get(n);

    	int res = Integer.MIN_VALUE;
    	for (int i = 1; i < n; i++)
    		// 拆一次或往下走再说
    		res = Math.max(res, Math.max(i * (n - i), i * dfs(n - i, map))); // res is built in as it changes for each iteration

    	map.put(n,res);
    	return res;
    }

    // Solution 2: top-down DP
	// T: O(n^2)
	// S: O(n)
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        // dp[i]: max product from breaking i
        // all products must > 0, so initialize array with 0 is OK
        dp[1] = 1;
        for (int i = 2; i <= n; i++) 
        	for (int j = 1; j < i; j++)
        		dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
        return dp[n];
    }

    // Solution 3: Math
	// T: O(n)
	// S: O(1)
    public int integerBreak(int n) {
        
    }
}