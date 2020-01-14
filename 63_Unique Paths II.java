class Solution {
	// T: O(m * n)
	// S: O(m * n)
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    	if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) 
    		return 0;
    	
    	int height = obstacleGrid.length;
    	int width = obstacleGrid[0].length;

        int[][] dp = new int[height][width];

        // 1st col
        for (int i = 0; i < height; i++) {
        	if (obstacleGrid[i][0] != 1) {
        		dp[i][0] = 1;
        	} else break;
        }
        
        // 1st row
        for (int j = 0; j < width; j++) {
        	if (obstacleGrid[0][j] != 1) {
        		dp[0][j] = 1;
        	} else break;
        }

        // other place
        for (int i = 1; i < height; i++) {
        	for (int j = 1; j < width; j++) {
        		if (obstacleGrid[i][j] != 1) {
        			dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        		}
        	}
        }

        return dp[height - 1][width - 1];
    }


    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int height = obstacleGrid.length;
        int width = obstacleGrid[0].length;
        int[][] dp = new int[height][width];
        return uniquePaths(height - 1, width - 1, dp, obstacleGrid);
    }

    private int uniquePaths(int x, int y, int[][] dp, int[][] obstacleGrid) {
        if (x == 0 || y == 0) return 1;
        if (dp[x][y] == 0)
            dp[x][y] = uniquePaths(x - 1, y, dp) + uniquePaths(x, y - 1, dp);
        return dp[x][y];
    }
}


