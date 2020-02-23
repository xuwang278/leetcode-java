class Solution {
    public int maxCount(int m, int n, int[][] ops) {
        if (ops == null || ops.length == 0) return m * n;

        int row = Integer.MAX_VALUE, col = Integer.MAX_VALUE;
        for (int[] op : ops) {
            row = Math.min(row, op[0]);
            col = Math.min(col, op[1]);
        }
        return row * col;
    }

    // Memory Limit Exceeded
    public int maxCount(int m, int n, int[][] ops) {
        int[][] grid = new int[m][n];
        for (int[] op : ops) {
            int row = op[0];
            int col = op[1];
            for (int i = 0; i < row; i++)
                for (int j = 0; j < col; j++)
                    grid[i][j]++;
        }
        
        Map<Integer, Integer> map = new HashMap<>(); // int -> freq
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                max = Math.max(max, grid[i][j]);
                map.put(grid[i][j], map.getOrDefault(grid[i][j], 0) + 1);
            }
        
        return map.get(max);
                
    }
}