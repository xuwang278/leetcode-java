class Solution {
    
    This is a multiple sources - multiple targets problem
    (1) load all srcs to q
    (2) process nodes layer by layer; when finished, step++
    (3) for each node, expand to 4 directions
        （a）out of boundary, ignore
        （b）non-target, ignore
         (c) shortest distance found, ignore (also necessary for single - multiple problem)
         (d) shortest distance = step + 1
         (e) load the node to q 

    public int[][] updateMatrix(int[][] matrix) {
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) matrix[i][j] = Integer.MAX_VALUE; // initialize as pretty far away
                else if (matrix[i][j] == 0) q.offer(new int[] {i, j});
            }
        }
        
        int[] dirs = new int[] {-1, 0, 1, 0, -1};
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int[] top = q.poll();
                int r = top[0];
                int c = top[1];
                for (int k = 0; k < 4; k++) {
                    int dr = r + dirs[k];
                    int dc = c + dirs[k + 1];
                    if (dr < 0 || dr >= matrix.length || dc < 0 || dc >= matrix[0].length) continue; // invalid
                    if (matrix[dr][dc] == 0) continue; // only consider 1
                    if (matrix[dr][dc] <= step + 1) continue; // shorter path was achieved from previous 0s
                    matrix[dr][dc] = step + 1; // get shortest distance from nearest 0
                    q.offer(new int[] {dr, dc}); // offer to q for expansion: used to calculate its 1-value neighbours
                }
            
            }
            step++;
        }
        return matrix;
    }

	// Solution 1: BFS from 1 to 0 - TLE
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new int[][] {};
        
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] ans = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int d = bfs(matrix, i, j, new boolean[row][col]);
                ans[i][j] = d;
            }
        }
        return ans;
    }
    
    private int bfs(int[][] matrix, int r, int c, boolean[][] visited) {
        if (matrix[r][c] == 0) return 0;
        
        int step = 0; 
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c});
        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int[] cur = q.poll();
                int i = cur[0];
                int j = cur[1];
                if (visited[i][j]) continue; // alread in q, no need to expand
                if (matrix[i][j] == 0) return step;
                visited[i][j] = true;
                if (i - 1 >= 0) q.offer(new int[] {i - 1, j});
                if (i + 1 < matrix.length) q.offer(new int[] {i + 1, j});
                if (j - 1 >= 0) q.offer(new int[] {i, j - 1});
                if (j + 1 < matrix[0].length) q.offer(new int[] {i, j + 1});
            }
            step++;
        }
        return -1;
    }

    // Solution 2: BFS from 0
    public int[][] updateMatrix(int[][] matrix) {
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) matrix[i][j] = Integer.MAX_VALUE;
                else if (matrix[i][j] == 0) q.offer(new int[] {i, j});
            }
        }
        
        int[] dirs = new int[] {-1, 0, 1, 0, -1};
        while (!q.isEmpty()) {
            int[] top = q.poll();
            int r = top[0];
            int c = top[1];
            for (int k = 0; k < 4; k++) {
                int dr = r + dirs[k];
                int dc = c + dirs[k + 1];
                if (dr < 0 || dr >= matrix.length || dc < 0 || dc >= matrix[0].length) continue; // invalid
                if (matrix[dr][dc] == 0) continue; // only consider 1
                if (matrix[dr][dc] <= matrix[r][c] + 1) continue; // has been visited from other 0 in a shorter path
                matrix[dr][dc] = matrix[r][c] + 1; // get shortest distance from nearest 0
                q.offer(new int[] {dr, dc}); // offer to q for expansion: used to calculate its 1-value neighbours
            }
        }
        return matrix;
    }

}