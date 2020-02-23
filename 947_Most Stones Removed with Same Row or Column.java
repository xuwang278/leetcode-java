class Solution {
	private class UF {
		private int[] parent;
		private int[] rank;
		private int count;

		public UF(int n) {
			parent = new int[n];
			rank = new int[n];
			count = n;
			for (int i = 0; i < n; i++)
				parent[i] = i;
		}

		public int find(int s) {
			if (s != parent[s])
				parent[s] = find(parent[s]);
			return parent[s];
		}

		public void union(int s, int t) {
			int rootS = find(s);
			int rootT = find(t);
			if (rootS == rootT) return;
			if (rank[rootS] < rank[rootT]) parent[rootS] = rootT;
			else if (rank[rootS] > rank[rootT]) parent[rootT] = rootS;
			else {
				parent[rootS] = rootT;
				rank[rootT]++;
			}
			count--;
		}

		public int count() {
			return count;
		}

	}

	// Solution 1: UF
    public int removeStones(int[][] stones) {
    	int n = stones.length;
        UF uf = new UF(n);
        for (int i = 0; i < n; i++) {
        	for (int j = i + 1; j < n; j++) {
        		if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1])
        			uf.union(i, j);
        	}
        }
        return n - uf.count();
    }

    // Solution 2: DFS
    // Ans = # of stones – # of islands
    public int removeStones(int[][] stones) {
    	Set<int[]> visited = new HashSet<>();
    	int cc = 0;
    	for (int[] s : stones) {
    		if (!visited.contains(s)) {
    			dfs(s, visited, stones);
    			cc++;
    		}
    	}
    	return stones.length - cc;
    }

    private void dfs(int[] stone, Set<int[]> visited, int[][] stones) {
    	visited.add(stone);
    	for (int[] s : stones) {
    		if (!visited.contains(s)) 
    			if (stone[0] == s[0] || stone[1] == s[1])
    				dfs(s, visited, stones);
    	}
    }

}