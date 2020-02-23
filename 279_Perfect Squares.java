class Solution {

	DP:
	1. Definition
		- dp[i]: the least number of perfect square numbers of given integer i
		- dp[12] = 3
		- dp[13] = 2
	2. Answer
		- dp[n]
	3. State transfer equation
		- dp[i] = min(dp[i] + 1, dp[i + j * j]);
	4. Initialization
		- dp[i * i] = 1;

    public int numSquares(int n) {
        if (n <= 0) return 0;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
        	int local_res = Integer.MAX_VALUE;
        	int j = 1;
        	while (i - j * j >= 0) {
        		local_res = Math.min(local_res, dp[i - j * j] + 1);
                j++;
        	}
        	dp[i] = local_res;
        }
        return dp[n];
    }
}