class Solution {

	dp[i][j]: the minimum cost in range [i, j]
	
	我们想，如果只有一个数字，那么我们不用猜，cost为0。
	如果有两个数字，比如1和2，我们猜1，即使不对，我们cost也比猜2要低。
	如果有三个数字1，2，3，那么我们就先猜2，根据对方的反馈，就可以确定正确的数字，所以我们的cost最低为2。

	四个数字1，2，3，4，那么情况就有点复杂了，那么我们的策略是用k来遍历所有的数字，然后再根据k分成的左右两个区间，取其中的较大cost加上k。
		当k为1时，左区间为空，所以cost为0，而右区间2，3，4，根据之前的分析应该取3，所以整个cost就是1+3=4。
		当k为2时，左区间为1，cost为0，右区间为3，4，cost为3，整个cost就是2+3=5。(至少花3元才能保证赢：如果幸运，目标数是1，那么直接命中，再花0元；如果目标数是3或4，再花3元可以保证命中。综上，第一手如果猜2，需要再花3元才能保证命中！)
		当k为3时，左区间为1，2，cost为1，右区间为4，cost为0，整个cost就是3+1=4。
		当k为4时，左区间1，2，3，cost为2，右区间为空，cost为0，整个cost就是4+2=6。
		综上k的所有情况，此时我们应该取整体cost最小的，即4 （第一手猜3），为最后的答案

	http://www.cnblogs.com/grandyang/p/5677550.html

	// Recursion
	// Top-down DP
    public int getMoneyAmount(int n) {
    	int[][] dp = new int[n + 1][n + 1];
    	return dfs(1, n, dp);
    }
 
    private int dfs(int lo, int hi, int[][] dp) {
    	if (lo >= hi) return 0; // only one number in range[i, j], no guess needed
    	if (dp[lo][hi] != 0) return dp[lo][hi];
    	int res = Integer.MAX_VALUE;
    	// divide
    	for (int i = lo; i <= hi; i++) {
    		int temp = i + Math.max(dfs(lo, i - 1, dp), dfs(i + 1, hi, dp)); // get max to guarantee a win
    		res = Math.min(res, temp); // merge
    	}
    	dp[lo][hi] = res;
    	return res;
    }

    private int dfs(int lo, int hi, int[][] dp) {
        if (lo >= hi) return 0;
        if (dp[lo][hi] != 0) return dp[lo][hi];
        if (lo + 1 == hi) {
            dp[lo][hi] = lo;
            return lo;
        }
        int res = Integer.MAX_VALUE;
        for (int i = lo + 1; i < hi; i++) {
            int temp = i + Math.max(dfs(lo, i - 1, dp), dfs(i + 1, hi, dp));
            res = Math.min(temp, res);
        }
        dp[lo][hi] = res;
        return res;
    }

    // Iterative (bottom-up DP)
    // i j sequence matters!
    // j = 2: [1,2]
    // j = 3: [2,3] [1,3]
    // j = 4: [3,4] [2,4] (k=3) [1,4] (k=2,3)
    public int getMoneyAmount(int n) {
    	int[][] dp = new int[n + 1][n + 1];
    	// upper range
    	for (int j = 2; j <= n; j++) {
    		// lower range
    		for (int i = j - 1; i > 0; i--) {
    			int globalMin = Integer.MAX_VALUE;
    			for (int k = i + 1; k < j; k++) {
    				int localMax = k + Math.max(dp[i][k - 1], dp[k + 1][j]);
    				globalMin = Math.min(globalMin, localMax);
    			}
    			dp[i][j] = i + 1 == j ? i : globalMin; // if i+1==j, the k-indexed loop doesn't run
    		}
    	}
    	return dp[1][n];
    }

    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        // upper range
        for (int j = 2; j <= n; j++) {
            // lower range
            for (int i = j - 1; i > 0; i--) {
                if (i + 1 == j) {
                    dp[i][j] = i;
                    continue; // continue to use next i value, instead of breaking the inner loop
                }
                int globalMin = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    int localMax = k + Math.max(dp[i][k - 1], dp[k + 1][j]);
                    globalMin = Math.min(globalMin, localMax);
                }
                dp[i][j] = globalMin;
            }
        }
        return dp[1][n];
    }

}