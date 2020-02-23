class Solution {
	// Solution 1: count #
	// add 4 for each land and remove 1 for each neighbor
    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        
        int islands = 0, neighbors = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    islands++;
                    if (i - 1 >= 0 && grid[i-1][j] == 1) neighbors++;
                    if (j - 1 >= 0 && grid[i][j-1] == 1) neighbors++;
                    if (i + 1 < grid.length && grid[i + 1][j] == 1) neighbors++;
                    if (j + 1 < grid[0].length && grid[i][j + 1] == 1) neighbors++;
                }
            }
        }

        return islands * 4 - neighbors;
    }

    // Solution 2: DFS
    private int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int islandPerimeter(int[][] grid) {
        if (grid == null) return 0;
        for (int i = 0 ; i < grid.length ; i++){
            for (int j = 0 ; j < grid[0].length ; j++){
                if (grid[i][j] == 1) {
                    return dfs(grid,i,j);
                }
            }
        }
        return 0;
    }
    
    public int dfs(int[][] grid, int i, int j){
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return 1; // 返回边界的那条边
        if (grid[i][j] == 0) return 1; // 返回与水相隔的那条边
        if (grid[i][j] == -1) return 0; // 已经算过了
        
        grid[i][j] = -1; // marked as visited
        int count = 0; // count from this point
        for (int k = 0; k < 4; k++) {
            int di = i + dirs[k][0];
            int dj = j + dirs[k][1];
            count += dfs(grid, di, dj);
        } 
        return count;
    }
    

}