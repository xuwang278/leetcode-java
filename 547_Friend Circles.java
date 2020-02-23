class Solution {
    // Solution 1: Graph + DFS (Build g)
    // T: O(VE), S: O(V + VE) V = n
    public int findCircleNum(int[][] M) {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        buildGraph(M, g);
        int cnt = 0;
        boolean[] visited = new boolean[M.length]; // # of vertices
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                dfs(g, i, visited);
                // bfs(g, i, visited);
                cnt++;
            }
        }
        return cnt;
    }
    
    private void buildGraph(int[][] M, Map<Integer, Set<Integer>> g) {
        for (int i = 0; i < M.length; i++) g.put(i, new HashSet<>()); // safe to initialize set for each vertex; otherwise g.get(v) may equals to null
        
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                if (i == j) continue;
                if (M[i][j] == 1) { // i and j are friends
                    g.get(i).add(j);
                    g.get(j).add(i);
                }
            }
        }
    }
    
    private void dfs(Map<Integer, Set<Integer>> g, int i, boolean[] visited) {
        visited[i] = true;
        for (int next : g.get(i))
            if (!visited[next])
                dfs(g, next, visited);
    }

    // Solution 2: Graph + BFS
    // T: O(n^2), S: O(n + n^2 + n) V + E + q
    private void bfs(Map<Integer, Set<Integer>> g, int i, boolean[] visited) {
        visited[i] = true;
        Queue<Integer> q = new LinkedList<>();
        q.offer(i);
        while (!q.isEmpty()) {
            int top = q.poll();
            for (int next : g.get(top)) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }
    }

    // Solution 3: UF
    // T: O(n^2)
    // S: O(n)
    public int findCircleNum(int[][] M) {
        int n = M.length;
        UF uf = new UF(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (M[i][j] == 1) uf.union(i, j);
            }
        }
        return uf.count();
    }
    
    private class UF {
        private int[] parent;
        private int[] rank;
        private int cnt;
        
        public UF(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
            cnt = n;
        }
        
        public int find(int s) {
            if (s != parent[s])
                parent[s] = find(parent[s]);
            return parent[s];
        }
        
        public void union(int p, int q) {
            int rp = find(p);
            int rq = find(q);
            if (rp == rq) return;
            else if (rank[rp] < rank[rq]) parent[rp] = rq;
            else if (rank[rp] > rank[rq]) parent[rq] = rp;
            else {
                parent[rq] = rp;
                rank[rp]++;
            }
            cnt--;
        }
        
        public int count() {
            return cnt;
        }
    }
    
}