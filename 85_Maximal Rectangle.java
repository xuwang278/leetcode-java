class Solution {
    // T: O(row * col)
    // S: O(col)
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        
        int row = matrix.length;
        int col = matrix[0].length;
        int res = 0;
        int area = 0;
        int[] rows = new int[col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == '0') rows[j] = 0;
                else rows[j] += Character.getNumericValue(matrix[i][j]); 
            }
            area = largestRectangleArea(rows);
            res = Math.max(res, area);
        }

        return res;
    }

    // T: O(2n)
    // S: O(n)
    private int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int area = 0;
        int i = 0;
        while (i < heights.length) {
            if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
                stack.push(i++);
            } else {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    area = heights[top] * i;
                } else {
                    area = heights[top] * (i - stack.peek() - 1);
                }
                maxArea = Math.max(maxArea, area);
            }
        }

        while (!stack.isEmpty()) {
            int top = stack.pop();
            if (stack.isEmpty()) {
                area = heights[top] * i;
            } else {
                area = heights[top] * (i - stack.peek() - 1);
            }
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }
}