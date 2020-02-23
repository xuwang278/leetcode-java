class Solution {
    // http://www.cnblogs.com/grandyang/p/5000291.html
    // T: O(V)
    // S: O(V)
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) {
            List<Integer> res = new ArrayList<>();
            res.add(0);
            return res;
        }
        
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (int i = 0; i < n; i++) g.put(i, new HashSet<>());
        for (int[] e : edges) {
            g.get(e[0]).add(e[1]);
            g.get(e[1]).add(e[0]);
        }
        
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (g.get(i).size() == 1)
                leaves.add(i);
        }
        
        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            
            // exploit outter ring of graph until 1 or 2 left in the center, 
            // i.e root of MHT
            for (int l : leaves) {
                int neighbor = g.get(l).iterator().next(); // get the only one neighbor of the leaf
                g.get(l).remove(neighbor); // not necessary but more logical
                g.get(neighbor).remove(l); // remove the edge between l and neighbor
                if (g.get(neighbor).size() == 1) newLeaves.add(neighbor); // load new leaf
            }
            leaves = newLeaves;
        }
        
        return leaves;
    }

}