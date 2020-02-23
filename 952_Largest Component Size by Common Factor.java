class Solution {
    private class UF {
		private int[] parent;
		private int[] rank;
		private int count;

		public UF(int n) {
			count = n;
			parent = new int[n];
			rank = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = i;
				rank[i] = 0;
			}
		}

		// O(1)* amorized
        // pass compression
		public int find(int s) {
			if (s != parent[s])
				parent[s] = find(parent[s]);
			return parent[s];
		}

		// O(1)* 
        // union by rank
		public void union(int p, int q) {
			int rootP = find(p); // pass compression
			int rootQ = find(q);
			if (rootP == rootQ) return;
			if (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
			else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
			else {
				parent[rootQ] = rootP;
				rank[rootP]++;
			}
			count--;
		}
    }
    
    // Solution 1: UF
    // T: O(n * sqrt(w)), w - max # in A
    // S: O(W)
    public int largestComponentSize(int[] A) {
        int max = Integer.MIN_VALUE;
        for (int a : A) max = Math.max(max, a);
        UF uf = new UF(max + 1); // make room to connect max item in A with all fctors no large than max
    	for (int a : A)
    		for (int k = 2; k <= Math.sqrt(a); k++) // sqrt is important, sqrt is a small # ~ constant; otherwise, O(n^2)
    			if (a % k == 0) {
    				uf.union(a, k);
    				uf.union(a, a / k);
    			}

    	Map<Integer, Integer> map = new HashMap<>(); // parent (cc id) -> freq
    	for (int a : A) {
    		int ra = uf.find(a);
    		if (!map.containsKey(ra)) map.put(ra, 1);
    		else map.put(ra, map.get(ra) + 1);
    	}

    	int ans = 0;
    	for (int v : map.values()) 
    		ans = Math.max(ans, v);

    	return ans;

    }

    // Solution 2: Graph + DFS
    // T: O(n * sqrt(w) + V + E), V = n + sqrt(w), E = n + sqrt(w)
    // S: O(w)
    public int largestComponentSize(int[] A) {
    	Map<Integer, Set<Integer>> g = new HashMap<>();
    	buildGraph(g, A);
        
    	int max = 0;
    	Set<Integer> visited = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        for (int a : A) set.add(a);
        
    	for (int a : A) {
    		if (!visited.contains(a)) {
    			int cnt[] = new int[] {0};
    			dfs(g, a, visited, cnt, set);
    			max = Math.max(max, cnt[0]);
    		}
    	}
    	return max;
    }

    // T: O(n * sqrt(w))
    // S: O(w)
    private void buildGraph(Map<Integer, Set<Integer>> g, int[] A) {
    	int max = Integer.MIN_VALUE;
        for (int a : A) max = Math.max(max, a);
        for (int i = 0; i <= max; i++) g.put(i, new HashSet<>());
        
    	for (int a : A) {
    		for (int k = 2; k <= Math.sqrt(a); k++) {
    			if (a % k == 0) {
    				g.get(a).add(k);
    				g.get(k).add(a);
    				g.get(a).add(a / k);
    				g.get(a / k).add(a);
    			}
    		}
    	}
    }

    private void dfs(Map<Integer, Set<Integer>> g, int a, Set<Integer> visited, int cnt[], Set<Integer> set) {
    	visited.add(a);
    	if (set.contains(a)) cnt[0]++; // ignore factor vertices
    	for (int next : g.get(a))
    		if (!visited.contains(next))
    			dfs(g, next, visited, cnt, set);
    }




































}