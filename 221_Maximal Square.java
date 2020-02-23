class Solution {
    // https://www.youtube.com/watch?v=_Lf1looyJMU
    // T: O(row * col)
    // S: O(row * col)
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int row = matrix.length;
        int col = matrix[0].length;
        int res = 0;

        // dp[i][j]: size of square with matrix[i-1][j-1] considered
        // padding to let all dp[i][j] has 3 corners
        int[][] dp = new int[row + 1][col + 1]; 
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res * res;
    }

}