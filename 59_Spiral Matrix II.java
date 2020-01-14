class Solution {
	// T: O(n * m)
	// S: O(n * m)
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        if (n <= 0) return matrix;

         // 初始化: 最左, 最右, 最上, 最下
        int left = 0, right = n - 1;
        int top = 0, bottom = n - 1;
        int k = 1;

        // 只要还能螺旋转圈
        while (top < bottom && left < right) {
            // 扫描: 从左到右, 从上到下, 从右到左, 从下到上
            // 注意: 右端点开口
        	for (int i = left; i < right; i++) matrix[top][i] = k++;
        	for (int i = top; i < bottom; i++) matrix[i][right] = k++;
        	for (int i = right; i > left; i--) matrix[bottom][i] = k++;
        	for (int i = bottom; i > top; i--) matrix[i][left] = k++;
        	left++;
        	right--;
        	top++;
        	bottom--;
        }

        // 剩下一个夹心
        // 注意: 右端点闭口
        if (left == right) {
        	for (int i = top; i <= bottom; i++) matrix[i][left] = k++;
        } else if (top == bottom) {
        	for (int i = left; i <= right; i++) matrix[top][i] = k++;
        }

    	return matrix;
    }
}