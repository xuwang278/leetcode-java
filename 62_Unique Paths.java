class Solution {
	// T: O(m * n)
	// S: O(m * n)
	// 建立递推式
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = 1; // 1st col
        for (int j = 0; j < n; j++) dp[0][j] = 1; // 1st row
        for (int i = 1; i < m; i++) { // other place
        	for (int j = 1; j < n; j++) {
        		dp[i][j] = dp[i - 1][j] + dp[i][j - 1]; //务必考虑右端是否已经提前求解
        	}
        }
        return dp[m - 1][n - 1];
    }

	// Method 2
	// T: O(m * n)
	// S: O(m * n)
  	public int uniquePaths(int m, int n) {
  		int[][] dp = new int[m][n];
  		return uniquePaths(m - 1, n - 1, dp);
  	}

  	private int uniquePaths(int x, int y, int[][] dp) {
  		// if (x < 0 || y < 0) return 0;
  		// if (x == 0 && y == 0) return 1;
  		if (x == 0 || y == 0) return 1;
  		if (dp[x][y] == 0)
  			dp[x][y] = uniquePaths(x - 1, y, dp) + uniquePaths(x, y - 1, dp);
  		return dp[x][y];
  	}

  	// Method 3
  	// T: O(m * n)
	  // S: O(n)
  	public int uniquePaths(int m, int n) {
  		int[] res = new int[n]; // contain steps by which to reach entry in each row
  		res[0] = 1;
  		for (int i = 0; i < m; i++) {
  			for (int j = 1; j < n; j++) {
  				res[j] = res[j] + res[j - 1]; // res[j]旧值相当于dp[i - 1][j]
  			}
  		}
  		return res[n - 1];
  	}

}