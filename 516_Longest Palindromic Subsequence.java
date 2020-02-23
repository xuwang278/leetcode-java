class Solution {
	// https://www.youtube.com/watch?v=_nCsPn7_OgI
	// T: O(n^2)
	// S: O(n^2)
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++)
            dp[i][i] = 1;

        for (int l = 2; l <= n; l++) {
        	for (int i = 0; i <= n - l; i++) {
        		int j = i + l - 1;
        		if (s.charAt(i) == s.charAt(j))
        			dp[i][j] = dp[i + 1][j - 1] + 2; // count two ends
        		else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]); // larger one in middle
        	}
        }
        
        return dp[0][n - 1];
    }

}


