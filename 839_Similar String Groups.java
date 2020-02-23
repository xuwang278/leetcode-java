class Solution {
    // Solution 1: Graph dfs
    public int numSimilarGroups(String[] A) {
        Map<String, Set<String>> g = new HashMap<>();
        buildGraph(g, A);
        
        Set<String> visited = new HashSet<>();
        int size = 0;
        for (String key : g.keySet()) {
            if (visited.contains(key)) continue;
            dfs(g, key, visited);
            size++;
        }
        return size;
    }
    
    private void buildGraph(Map<String, Set<String>> g, String[] A) {
        for (String s : A) 
            g.put(s, new HashSet<>());
        
        int n = A.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                String first = A[i];
                String second = A[j];
                if (isSimilar(first, second)) {
                    g.get(first).add(second);
                    g.get(second).add(first);
                }
            }
        }
    }
    
    private boolean isSimilar(String a, String b) {
        int n = a.length();
        int diff = 0;
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
                if (diff > 2) return false;
            }
        }
        return true;
    }
    
    private void dfs(Map<String, Set<String>> g, String key, Set<String> visited) {
        visited.add(key);
        for (String next : g.get(key)) {
            if (visited.contains(next)) continue;
            dfs(g, next, visited);
        }
    }

    // Solution 2: UF
    private class UF {
        private int[] parent;
        private byte[] rank;
        private int count;

        public UF(int n) {
            count = n;
            parent = new int[n];
            rank = new byte[n];
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

        // O(1)
        public int count() {
            return count;
        }

        // O(1)
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

    }

    // Do  O(n^2) comparison over all strings, 
    // and if they are similar, union them
    public int numSimilarGroups(String[] A) {
        int n = A.length;
        UF uf = new UF(n);
        for (int i = 0; i < n - 1; i++) {
            for (int j = i; j < n; j++) {
                String first = A[i];
                String second = A[j];
                if (isSimilar(first, second))
                    uf.union(i, j);
            }
        }
        return uf.count();
    }

    private boolean isSimilar(String a, String b) {
        int n = a.length();
        int diff = 0;
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                diff++;
                if (diff > 2) return false;
            }
        }
        return true;
    }


}