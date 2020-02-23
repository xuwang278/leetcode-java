class Solution {
    // T: O(m + n)
    // S: O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
    	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) 
    		return false;
        int row = 0, col = matrix[0].length - 1; // 1st row, last item
        while (col >= 0 && row <= matrix.length - 1) {
        	if (matrix[row][col] == target) return true;
        	else if (matrix[row][col] > target) col--; 
        	else row++;
        }
        return false;
    }
}