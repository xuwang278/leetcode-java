class Solution {
	// T:O(m^2 * n^2)
    int[][] dirs = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid[0].length == 0) return -1;
        
        int m = grid.length, n = grid[0].length;
        int[][] distance = new int[m][n]; // distance[i][j]: grid[i][j] == 0, the sum of shortest distance from this block to all reachable buildings
        int[][] canReach = new int[m][n]; // canReach[i][j]: grid[i][j] == 0, # of buildings that are reachable from this block

        int totalBuildings = 0;
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (grid[i][j] == 1) {
        			totalBuildings++; // find a unvisited biulding
        			bfs(grid, i, j, canReach, distance);
        		}
        	}
        }

        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
        	for (int j = 0; j < n; j++) {
        		if (canReach[i][j] == totalBuildings && distance[i][j] < minDist) {
        			minDist = distance[i][j];
        		}
        	}
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist;
    }

    // find shorest distance from this 1 (grid[i][j]) to all reachable 0
    private void bfs(int[][] grid, int i, int j, int[][] canReach, int[][] distance) {
    	int m = grid.length, n = grid[0].length;
    	Queue<int[]> q = new LinkedList<>();
    	boolean[][] visited = new boolean[m][n];
    	int d = 0;
    	q.offer(new int[] {i, j});
    	visited[i][j] = true;

    	while (!q.isEmpty()) {
    		int size = q.size();
    		for (int s = 0; s < size; s++) {
    			int[] top = q.poll();
    			for (int k = 0; k < 4; k++) {
    				int di = dirs[k][0] + top[0];
    				int dj = dirs[k][1] + top[1];
    				if (di < 0 || di >= m || dj < 0 || dj >= n 
    					|| visited[di][dj] || grid[di][dj] != 0) continue;
    				visited[di][dj] = true;
    				q.offer(new int[] {di, dj});

                    // distance of building grid[i][j] to grid[di][dj] is d + 1
                    // other building may also get grid[di][dj], so accumulate the distance
    				distance[di][dj] += d + 1;

                    // # of buildings that are reachable from this 0;
                    // empty land grid[di][dj] can be reached from this building grid[i][j]
    				canReach[di][dj]++;  
    			}
    		}
            d++;
    	}
    }


}