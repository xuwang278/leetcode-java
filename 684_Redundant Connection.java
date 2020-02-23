class Solution {
	// Solution 1: UF
	// T: O(n log*n) = O(n) 查找n条边，每条O(1)时间
	// S: O(n) 构建parent array
    public int[] findRedundantConnection(int[][] edges) {
        UF uf = new UF(edges.length + 1); // shift 1
        for (int[] e : edges) {
            if (uf.connected(e[0], e[1])) return e;
            uf.union(e[0], e[1]);
        }
        return new int[] {};
    }

    private class UF {
        private int[] parent;
        private byte[] rank;

        public UF(int n) {
            parent = new int[n];
            rank = new byte[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find (int s) {
            if (parent[s] != s)
                parent[s] = find(parent[s]);
            return parent[s];
        }

        public void union (int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            if (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
            else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
            else {
                parent[rootQ] = rootP;
                rank[rootP]++;
            }
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

    }

    // Solution 2: Graph + DFS
    // T: O(n^2) 查找n条边，每条O(n)时间
    // S: O(n)
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (int[] e : edges) {
            int a = e[0];
            int b = e[1];
            if (dfs(g, a, b, new HashSet<>())) return e;
            g.putIfAbsent(a, new HashSet<>());
            g.putIfAbsent(b, new HashSet<>());
            g.get(a).add(b);
            g.get(b).add(a);
        }
        return new int[] {};
    }
    
    // is there a path fron cur to target?
    private boolean dfs(Map<Integer, Set<Integer>> g, int cur, int target, Set<Integer> visited) {
        if (!g.containsKey(cur)) return false;
        if (g.get(cur).contains(target)) return true;
        visited.add(cur);
        for (int next : g.get(cur)) {
            if (visited.contains(next)) continue;
            if (dfs(g, next, target, visited)) return true;
        }
        return false;
    }


}