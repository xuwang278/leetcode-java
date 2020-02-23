class NumMatrix {

    // as input array matrix is immutable, 
    // we use sums[i][j] to save sum of the first j items (with padding) at row i (no padding)
	private int[][] sums; 
    
	// O(mn)
    public NumMatrix(int[][] matrix) {
    	if (matrix == null || matrix.length == 0) return;
    	int row = matrix.length;
    	int col = matrix[0].length;
        sums = new int[row][col + 1];
        for (int i = 0; i < row; i++) {
        	for (int j = 1; j <= col; j++) {
        		sums[i][j] = sums[i][j - 1] + matrix[i][j - 1];
        	}
        }
    }
    
    // O(m)
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
        	sum += sums[i][col2 + 1] - sums[i][col1];
        }
        return sum;
    }
    
}