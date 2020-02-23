class Solution {
    public int largestIsland(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int row = grid.length;
        int col = grid[0].length;
        int id = 2; // 0: water, 1: unvisited land
        Map<Integer, Integer> map = new HashMap<>(); // id -> area
        map.put(0, 0); // water -> 0
        int ans = 0; // max merging area
        
        // look for CC and build id -> area mapping
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j, id);
                    ans = Math.max(ans, area); // No 0 may leave after the 1st nested loop
                    map.put(id++, area);
                }
            }
        }
        
        // for each 0, look for its neighbors' id, calculate merging area
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> ids = new HashSet<>(); // id of CC nearby (no duplicates)
                    ids.add(getId(grid, i + 1, j));
                    ids.add(getId(grid, i - 1, j));
                    ids.add(getId(grid, i, j + 1));
                    ids.add(getId(grid, i, j - 1));
                    int area = 1;
                    for (int n : ids) {
                        area += map.get(n); // merge
                    }
                    ans = Math.max(ans, area);
                }
            }
        } 
        
        return ans;
        
    }
    
    private int getId (int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return 0;
        return grid[i][j];
    }
    
    private int dfs(int[][] grid, int i, int j, int id) {
        int row = grid.length;
        int col = grid[0].length;
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] != 1) return 0;
        grid[i][j] = id;
        return 1 + dfs(grid, i + 1, j, id) + 
                   dfs(grid, i - 1, j, id) +
                   dfs(grid, i, j + 1, id) +
                   dfs(grid, i, j - 1, id);
    }
}