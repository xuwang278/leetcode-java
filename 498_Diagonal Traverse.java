class Solution {
    // i + j even, go up right (r - 1, c + 1)
    // i + j odd, go dpwn left (r + 1, c - 1)
    // 越界只需要改变一个
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return new int[] {};
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[] ans = new int[m * n];
        
       
        int r = 0, c = 0;
        for (int i = 0; i < ans.length; i++) {
            ans[i] = matrix[r][c];
            if ((r + c) % 2 == 0) {
                if (r - 1 >= 0 && c + 1 < n) { // both valid, move both
                    r--;
                    c++;
                } else if (r + 1 < m && c + 1 >= n) r++; // c is invalid, r++ 越界只需要改变一个
                else if (r - 1 < 0 && c + 1 < n) c++; // r is invalid, c++
            } else {
                if (r + 1 < m && c - 1 >= 0) {
                    r++;
                    c--;
                } else if (r + 1 < m && c - 1 < 0) r++;
                else if (r + 1 >= m && c + 1 < n) c++;
            }
        }
        return ans;
    }

    
    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return new int[] {};
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[] ans = new int[m * n];
        
        // i + j even, go up right (r - 1, c + 1)
        // i + j odd, go dpwn left (r + 1, c - 1)
        int r = 0, c = 0;
        for (int i = 0; i < ans.length; i++) {
            ans[i] = matrix[r][c];
            if ((r + c) % 2 == 0) {
                // if 语句顺序不能变
                if (c == n - 1) r++; // can't increment c
                else if (r == 0) c++; // can't decrement r
                else {r--; c++;}
            } else {
                if(r == m - 1) c++;
                else if (c == 0) r++;
                else {r++; c--;}
            }
        }
        return ans;
    }

    

    
}