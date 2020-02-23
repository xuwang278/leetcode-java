class Solution {
    // Sol 1: UF
	public boolean equationsPossible(String[] equations) {
        UF uf = new UF(26);
        for (String eq : equations) {
            char l = eq.charAt(0);
            char r = eq.charAt(3);
            char c = eq.charAt(1);
            if (c == '=') uf.union(hash(l), hash(r));
        }
        
        for (String eq : equations) {
            char l = eq.charAt(0);
            char r = eq.charAt(3);
            char c = eq.charAt(1);
            if (c == '!' && uf.connected(hash(l), hash(r))) return false;;
        }
        return true;
    }
    
    private int hash(char c) {
        return c - 'a';
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
            if (rank[rp] < rank[rq]) parent[rp] = rq;
            else if (rank[rp] > rank[rq]) parent[rq] = rp;
            else {
                parent[rp] = rq;
                rank[rq]++;
            }
            cnt--;
        }
        
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }
    }

    // Solution 2: Graph + DFS
    // T: O()
    // S: O()
    public boolean equationsPossible(String[] equations) {
        Map<Character, Set<Character>> g = new HashMap<>();
        for (String eq : equations) {
            if (eq.charAt(1)  == '=') {
                char l = eq.charAt(0);
                char r = eq.charAt(3);
                g.putIfAbsent(l, new HashSet<>());
                g.get(l).add(r);
                g.putIfAbsent(r, new HashSet<>());
                g.get(r).add(l);
            }
        }
        
        for (String eq : equations) {
            if (eq.charAt(1) == '!') {
                char l = eq.charAt(0);
                char r = eq.charAt(3);
                if (l == r) return false;
                if (dfs(g, l, r, new HashSet<>())) return false; 
            }
        }
        return true;
    }
    
    private boolean dfs(Map<Character, Set<Character>> g, char cur, char target, Set<Character> visited) {
        if (!g.containsKey(cur)) return false;
        if (g.get(cur).contains(target)) return true;
        
        visited.add(cur);
        for (char next : g.get(cur)) {
            if (visited.contains(next)) continue;
            if (dfs(g, next, target, visited)) return true;
        }
        return false;
    }












}