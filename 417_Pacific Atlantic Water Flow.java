class Solution {
	// Solution 1: for each node, can it go to P or A or both?
	// TLE: https://www.youtube.com/watch?v=zV3o4XVoU8M
	// T: O(4^mn)
	// S: O()
    public List<int[]> pacificAtlantic(int[][] matrix) {
        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
        	for (int j = 0; j < matrix[0].length; j++) {
        		if (dfs(matrix, i, j, matrix[i][j]) == 3) 
        			ans.add(new int[] {i, j});
        	}
        }
        return ans;
    }

    // return 1 if (x, y) connects to Pacific
    // return 2 if (x, y) connects to Atlantic
    // return 3 if (x, y) connects to both (01 | 10 = 11)
    // return 0 if (x, y) water can't flow over from [x, y] (not contribute overral result)
    private int dfs(int[][] matrix, int x, int y, int h) {
    	if (x < 0 || y < 0) return 1; 
    	if (x == matrix.length || y == matrix[0].length) return 2; 
    	if (matrix[x][y] > h) return 0; // 
    	h = matrix[x][y];
    	matrix[x][y] = Integer.MAX_VALUE; // block visited site, when later dfs touches it, return 0
    	int valid = dfs(matrix, x + 1, y, h) |
    				dfs(matrix, x - 1, y, h) |
    				dfs(matrix, x, y + 1, h) |
    				dfs(matrix, x, y - 1, h);
    	matrix[x][y] = h; // back track to get ready for dfs starting from a new position
    	return valid;
    }

    // Solution 2: dfs to find all reachable nodes from P and A
    // T: O((m+n) + mn)
    // S: O(m, n)
    public List<int[]> pacificAtlantic(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) 
            return new ArrayList<>();
        
    	int row = matrix.length;
    	int col = matrix[0].length;
    	boolean[][] p = new boolean[row][col];
    	boolean[][] a = new boolean[row][col];
    	for (int j = 0; j < col; j++) {
    		dfs(matrix, 0, j, 0, p); // top
    		dfs(matrix, row - 1, j, 0, a); // bottom
    	}

    	for (int i = 0; i < row; i++) {
    		dfs(matrix, i, 0, 0, p); // left
    		dfs(matrix, i, col - 1, 0, a); // right
    	}

    	List<int[]> ans = new ArrayList<>();
    	for (int i = 0; i < row; i++)
    		for (int j = 0; j < col; j++)
    			if (p[i][j] && a[i][j])
    				ans.add(new int[] {i, j});
    	return ans;
    }

    private void dfs(int[][] matrix, int x, int y, int h, boolean[][] visited) {
    	if (x < 0 || y < 0 || x == matrix.length || y == matrix[0].length) return;
    	if (visited[x][y] || matrix[x][y] < h) return; // can't overcome, stop flow
    	visited[x][y] = true;
    	dfs(matrix, x + 1, y, matrix[x][y], visited);
    	dfs(matrix, x - 1, y, matrix[x][y], visited);
    	dfs(matrix, x, y + 1, matrix[x][y], visited);
    	dfs(matrix, x, y - 1, matrix[x][y], visited);
    }

    // Solution 3: bfs
    private int[][]dir = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

    public List<int[]> pacificAtlantic(int[][] matrix) {
    	if (matrix == null || matrix.length == 0 || matrix[0].length == 0) 
            return new ArrayList<>();

        int n = matrix.length, m = matrix[0].length;
        boolean[][] p = new boolean[n][m];
        boolean[][] a = new boolean[n][m];
        Queue<int[]> pq = new LinkedList<>();
        Queue<int[]> aq = new LinkedList<>();
        for (int i = 0; i < n; i++) {
        	pq.offer(new int[]{i, 0});
        	aq.offer(new int[]{i, m - 1});
        	p[i][0] = true;
        	a[i][m - 1] = true;
        }
        for (int i = 0; i < m; i++) {
        	pq.offer(new int[]{0, i});
        	aq.offer(new int[]{n - 1, i});
        	p[0][i] = true;
        	a[n - 1][i] = true;
        }

        bfs(matrix, pq, p);
        bfs(matrix, aq, a);

        List<int[]> ans = new ArrayList<>();
    	for (int i = 0; i < n; i++)
    		for (int j = 0; j < m; j++)
    			if (p[i][j] && a[i][j])
    				ans.add(new int[] {i, j});
    	return ans;
    }

    private void bfs(int[][] matrix, Queue<int[]> q, boolean[][] visited) {
    	int n = matrix.length, m = matrix[0].length;
    	while (!q.isEmpty()) {
    		int[] cur = q.poll();
    		for (int[] d : dir) {
    			int x = cur[0] + d[0];
    			int y = cur[1] + d[1];
    			if (x < 0 || y < 0 || x == n || y == m || 
    				visited[x][y] || matrix[x][y] < matrix[cur[0]][cur[1]]) 
    				continue;
    			visited[x][y] = true;
    			q.offer(new int[]{x, y});
    		}
    		
    	}
    }

    // Solutuon 4: DP
    public List<int[]> pacificAtlantic(int[][] matrix) {

    }


}