class Solution {
	// T: O(logm) + O(logn)
	// S: O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int height = matrix.length;
        int width = matrix[0].length;
        int lo = 0, hi = height - 1;
        while (lo + 1 < hi) {
        	int mid = lo + (hi - lo) / 2;
        	if (matrix[mid][0] < target) lo = mid;
        	else if (matrix[mid][0] > target) hi = mid;
        	else return true;
        }
        if (matrix[lo][0] == target) return true;
        if (matrix[hi][0] == target) return true;
        int row = 0;
        if (matrix[hi][0] < target) row = hi;
        else row = lo;

        int left = 0, right = width - 1;
        while (left + 1 < right) {
        	int mid = left + (right - left) / 2;
        	if (matrix[row][mid] < target) left = mid;
        	else if (matrix[row][mid] > target) right = mid;
        	else return true;
        }

        if (matrix[row][left] == target) return true;
        if (matrix[row][right] == target) return true;
        return false;
    }

    // Treat the 2D array as a 1D array. matrix[index / cols][index % cols], 
    // where index : 0 - mn-1
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        
        int m = matrix.length, n = matrix[0].length;
        int lo = 0, hi = m * n - 1;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (matrix[mid / n][mid % n] == target) return true;
            if (matrix[mid / n][mid % n] < target) lo = mid;
            else hi = mid;
        }
        return matrix[lo / n][lo % n] == target || matrix[hi / n][hi % n] == target;
    }

    // Search a 2D Matric I & II 通解
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int row = 0, col = n - 1;
        while (row < m && col >= 0) {
            int cmp = target - matrix[row][col];
            if (cmp == 0) return true;
            if (cmp > 0) row++;
            else col--;
        }
        return false;
    }
} 