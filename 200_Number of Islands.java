class Solution {
    // Solution 1: DFS
    // T: O(mn) check each item on grid
    // S: O(mn) 一整块grid是一个大岛
    public int numIslands(char[][] grid) {
       if (grid == null || grid.length == 0 || grid[0].length == 0)
           return 0;
        
        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    // bfs(grid, i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }
    
    private void dfs(char[][] grid, int i, int j) {
        // invalid or visited
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0')
            return;
        
        grid[i][j] = '0'; // mark it
        int[] dirs = new int[] {-1, 0, 1, 0, -1}; // expand to 4 directions
        for (int k = 0; k < 4; k++) 
            dfs(grid, i + dirs[k], j + dirs[k + 1]); // reject case in next level will handle invalid vertex
    }

    // version 2: consistent wi bfs but slower than 1st version dfs
    private void dfs(char[][] grid, int i, int j) {        
        grid[i][j] = '0'; // mark it
        int[] dirs = new int[] {-1, 0, 1, 0, -1}; // expand to 4 directions
        for (int k = 0; k < 4; k++) {
            int di = i + dirs[k];
            int dj = j + dirs[k + 1];
            if (di < 0 || di >= grid.length || dj < 0 || dj >= grid[0].length || grid[di][dj] == '0')
                continue;
            dfs(grid, di, dj); // feed in unvisted & valid vertex
        }      
    }

    // Solution 2: BFS
    // T: O(mn)
    // S: O(mn)
    private void bfs(char[][] grid, int i, int j) {
        Queue<int[]> q = new LinkedList<>();
        grid[i][j] = '0'; // mark and offer to q 
        q.offer(new int[] {i, j});
        int[] dirs = new int[] {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] top = q.poll();
            for (int k = 0; k < 4; k++) {
                int di = top[0] + dirs[k];
                int dj = top[1] + dirs[k + 1];
                // 因为在此检查是否visited，所以下面要立刻mark di,dj，而不是在poll下面mark
                if (di < 0 || di >= grid.length || dj < 0 || dj >= grid[0].length || grid[di][dj] == '0')
                    continue;
                grid[di][dj] = '0'; // mark and offer to q
                q.offer(new int[] {di, dj});
            }
        }
    }

}