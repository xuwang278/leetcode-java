class Solution {
    // Solution 1: BFS
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> g = new HashMap<>();
        for (int i = 0; i < n; i++) g.put(i, new HashMap<>());
        for (int[] f : flights) g.get(f[0]).put(f[1], f[2]);

        Queue<int[]> q = new LinkedList<>(); // support layer by layer iteration
        q.offer(new int[] {src, 0}); // vertex, layer
        int steps = 0;
        int min = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int city = cur[0];
                int cost = cur[1];
                if (city == dst) min = Math.min(min, cost); // may be the answer
                for (int next : g.get(city).keySet()) { // expand
                    if (cost + g.get(city).get(next) > min) continue; // pruning: no need to go further
                    q.offer(new int[] {next, cost + g.get(city).get(next)});
                }
            }
            if (steps++ > K) break;
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    } 

    // Solution 2: DFS (permutation of nodes - try all paths from src to dst within K steps)
    private int min = Integer.MAX_VALUE;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> g = new HashMap<>();
        for (int i = 0; i < n; i++) g.put(i, new HashMap<>());
        for (int[] f : flights) g.get(f[0]).put(f[1], f[2]);
        
        dfs(g, src, dst, K, 0, 0, new boolean[n]);
        return min == Integer.MAX_VALUE ? -1 : min;
    }
    
    private void dfs(Map<Integer, Map<Integer, Integer>> g, int city, int dst, int K, int stops, int price, boolean[] visited) {
        if (stops > K + 1) return; // reject
        if (city == dst) { // accept
            min = Math.min(min, price); // min = price is OK because of pruning 
            return;
        }
        
        for (int next : g.get(city).keySet()) { // expand
            if (visited[next]) continue;
            if (price + g.get(city).get(next) > min) continue;
            visited[next] = true; // try next
            dfs(g, next, dst, K, stops + 1, price + g.get(city).get(next), visited);
            visited[next] = false; // release next
        }
    }
    
    // Solution 3: Dijkstra (Lazy)
    // T: O(eloge)
    // S: O(eloge)
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> g = new HashMap<>();
        for (int i = 0; i < n; i++) g.put(i, new HashMap<>());
        for (int[] f : flights) g.get(f[0]).put(f[1], f[2]);
        
        // min heap by price
        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        
        // 如果像Dij SP模板那样使用visited，得到的一定从src到dst的最小价格；
        // 但是，此最理论上的最小路径可能超过K次停留，仍不是答案。
        // 所以，本题不使用visited，而是用K来剪枝，即，即使某一节点已经被访问过了，也仍再访问一遍，
        // 因为之前的也许导致超过K此停留

        // Set<Integer> visited = new HashSet<>();
        // visited.add(src); wrong
        pq.offer(new int[] {src, 0, 0});
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int city = top[0];
            int price = top[1];
            int stops = top[2];
            if (city == dst) return price;
            if (stops == K + 1) continue; // no expansion
            if (visited.contains(city)) continue;
            visited.add(city);
            for (int next : g.get(city).keySet()) {
                // if (visited.contains(next)) continue; // wrong
                // visited.add(next); // wrong
                pq.offer(new int[] {next, price + g.get(city).get(next), stops + 1});
            }
        }
        return -1;
    }

    

}