class Solution {
	// Solution 1: DWG DFS 
	// T: O(e + q * e) e - # of equations
	// S: O(e)
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> g = new HashMap<>(); // Wrighted Directed Graph (WDG)
        
        // build WDG
        for (int i = 0; i < equations.size(); i++) {
            String x = equations.get(i).get(0);
            String y = equations.get(i).get(1);
            double v = values[i];
            if (!g.containsKey(x)) g.put(x, new HashMap<>()); // g.putIfAbsent(x, new HashMap<>());
            g.get(x).put(y, v);
            if (!g.containsKey(y)) g.put(y, new HashMap<>());
            g.get(y).put(x, 1.0 / v);
            // g.get(a).put(a, 1.0); // not necessary, a/a = a/b * b/c * c/a
            // g.get(b).put(b, 1.0);
        }
        
        double[] ans = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String x = queries.get(i).get(0);
            String y = queries.get(i).get(1);
            ans[i] = dfs(g, x, y, new HashSet<String>());
        }
        return ans;
    }
    
    private double dfs(Map<String, Map<String, Double>> g, String x, String y, Set<String> visited) {
        if (!g.containsKey(x)) return -1.0; // reject case
        if (g.get(x).containsKey(y)) return g.get(x).get(y); // accept case
        // if (cur.equals(target)) return 1; // can replace upper code
        // if (visited.contains(x)) return 1; // if return type is void, we can just return here;but here is double type, so ignore in loop
        visited.add(x);
        for (String s : g.get(x).keySet()) {
            if (visited.contains(s)) continue; // already consider it on path: x is always a new vertex for search
            // visited.add(s); // redundant as next level recursion will handle it 
            double d = dfs(g, s, y, visited); // d = s / y
            if (d != -1.0) return d * g.get(x).get(s); // (s / y) * (x / s) = x / y
        }
        return -1.0; // no path x -> y even after trying x' neighbors to y, return -1.0;
    }
    
}












