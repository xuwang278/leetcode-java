class Solution {
    // serialize dfs path problem
    // find cc while building up string that represents the outline of island
    // when it's impossible to expand from an island, make sure to mark it as 'n'
    public int numDistinctIslands(int[][] grid) {
        Set<String> set = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();

                    dfs(grid, i, j, sb);
                    System.out.println(sb.toString());
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }
    
    // version 2
    private void dfs(int[][] grid, int i, int j, StringBuilder sb) {
        grid[i][j] = 0;
        int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // r, d, l, u
        String[] d = new String[] {"r", "d", "l", "u"};
        for (int k = 0; k < 4; k++) {
            int di = i + dirs[k][0];
            int dj = j + dirs[k][1];
            if (di < 0 || di >= grid.length || dj < 0 || dj >= grid[0].length || grid[di][dj] == 0) {
                sb.append("b");
                continue;
            }
            dfs(grid, di, dj, sb.append(d[k]));
        }
    }

    // version 1
    private void dfs(int[][] grid, int i, int j, StringBuilder sb) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) {
            //sb.append("b"); not necessary, why?
            return;
        }
        
        grid[i][j] = 0;
        int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // r, d, l, u
        String[] d = new String[] {"r", "d", "l", "u"};
        for (int k = 0; k < 4; k++) {
            int di = i + dirs[k][0];
            int dj = j + dirs[k][1];
            dfs(grid, di, dj, sb.append(d[k]));
        }        
    }
   
}