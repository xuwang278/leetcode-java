class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) { // calculate area of each cc and update max
                    int area = dfs(grid, i, j);
                    max = Math.max(max, area);
                }
            }
        }
        return max;
    }

    private int dfs(int[][] grid, int i, int j) {
        // pre-condition: i,j are valid, unvisited land
        int area = 1;
        grid[i][j] = 0; // mark it
        
        int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int d = 0; d < 4; d++) {
            int di = i + dirs[d][0];
            int dj = j + dirs[d][1];
            if (di < 0 || di >= grid.length || dj < 0 || dj >= grid[0].length || grid[di][dj] == 0) continue;
            area += dfs(grid, di, dj);
        }
        
        return area;
    }


    // Sol 1: dfs
    private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0)
            return 0;
        grid[i][j] = 0; // mark it
        int[] dirs = new int[] {-1, 0, 1, 0, -1};
        int area = 1;
        for (int k = 0; k < 4; k++) {
            area += dfs(grid, i + dirs[k], j + dirs[k + 1]);
        }
        return area;
    }
    
    // Sol 2: bfs
    private int bfs(int[][] grid, int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        grid[i][j] = 0; // mark it
        q.offer(new int[] {i, j});
        int[] dirs = new int[] {-1, 0, 1, 0, -1};
        int area = 0;
        while (!q.isEmpty()) {
            int[] top = q.poll();
            area++;
            for (int k = 0; k < 4; k++) {
                int di = top[0] + dirs[k];
                int dj = top[1] + dirs[k + 1];
                if (di < 0 || di >= grid.length || dj < 0 || dj >= grid[0].length || grid[di][dj] == 0) continue;
                grid[di][dj] = 0; // mark it and offer to q
                q.offer(new int[] {di, dj});
            }
        }
        return area;
    }

}