class Solution {
    public int[][] imageSmoother(int[][] M) {
        if (M == null || M.length == 0) return new int[][] {};
        
        int row = M.length, col = M[0].length;
        int[][] ans = new int[row][col];

        int[] dirs = new int[] {-1, 0, 1};
        for (int i = 0; i < row; i++) {
        	for (int j = 0; j < col; j++) {
        		int count = 0;
        		int sum = 0;
        		for (int di : dirs) {
        			for (int dj : dirs) {
        				if (isValid(i + di, j + dj, row, col)) {
        					count++;
        					sum += M[i + di][j + dj];
        				}
        			}
        		}
        		ans[i][j] = sum / count;
        	}
        }
        return ans;
    }

    private boolean isValid(int x, int y, int rows, int cols) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}