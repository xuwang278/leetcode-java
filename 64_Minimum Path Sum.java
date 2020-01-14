class Solution {
	// T: O(m * n)
	// S: O(m * n)
    public int minPathSum(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        int[][] dp = new int[height][width];
        dp[0][0] = grid[0][0];
        // 1st col
        for (int i = 1; i < height; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }

        // 1st row
        for (int j = 1; j < width; j++) {
        	dp[0][j] = grid[0][j] + dp[0][j - 1];
        }

        // other places
        for (int i = 1; i < height; i++) {
        	for (int j = 1; j < width; j++) {
        		dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
        	}
        }
        return dp[height - 1][width - 1];
    }

    // T: O(m * n)
    // S: O(n)
    public int minPathSum(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        int[] dp = new int[width];
        dp[0] = grid[0][0];
        for (int i = 1; i < width; i++) 
        	dp[i] = Integer.MAX_VALUE;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 && j == 0) continue;
                else if (j == 0) dp[j] = dp[j] + grid[i][j];
                else dp[j] = Math.min(dp[j], dp[j - 1]) + grid[i][j];
            }
        }
        return dp[width - 1];
    }

    // T: O(m * n)
	// S: O(1)
    public int minPathSum(int[][] grid) {
        int height = grid.length;
        int width = grid[0].length;
        for (int i = 0; i < height; i++) {
        	for (int j = 0; j < width; j++) {
        		if (i == 0 && j == 0) continue;
        		if (i == 0) grid[i][j] += grid[i][j - 1];
        		else if (j == 0) grid[i][j] += grid[i - 1][j];
        		else grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
        	}
        }
        return grid[height - 1][width - 1];
    }

    // T: O(m * n)
	// S: O(m * n)
    public int minPathSum(int[][] grid) {
    	int height = grid.length;
        int width = grid[0].length;
        int[][] dp = new int[height][width];
        return minPathSum(grid, height - 1, width - 1, dp);
    }

    private int minPathSum(int[][] grid, int i, int j, int[][] dp) {
    	if (i < 0 || j < 0) return Integer.MAX_VALUE;
    	if (i == 0 && j == 0) return grid[i][j];
    	if (dp[i][j] == 0)
    		dp[i][j] = grid[i][j] + Math.min(minPathSum(grid, i - 1, j, dp), minPathSum(grid, i, j - 1, dp));
    	return dp[i][j];
    }
}

















