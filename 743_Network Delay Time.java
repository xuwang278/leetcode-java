class Solution {
    // Solution 1: Dij (lazy)
    // T: O(vlogv + e)
    // S: O(v + e)
    public int networkDelayTime(int[][] times, int N, int K) {
        // build graph
        Map<Integer, Map<Integer, Integer>> g = new HashMap<>();
        for (int i = 1; i <= N; i++) 
            g.put(i, new HashMap<>());
        for (int[] t : times) {
            int from = t[0];
            int to = t[1];
            int time = t[2];
            g.get(from).put(to, time);
        }
        
        // min heap by weight
        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return a[1] - b[1];
            }
        });
        pq.offer(new int[] {K, 0});
        Map<Integer, Integer> visited = new HashMap<>(); // vertex -> distance from K
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int v = cur[0];
            int d = cur[1];
            if (visited.containsKey(v)) continue; // SP to v has been found

            // pop后再mark，因为就物理意义而言，pop时刻才为访问时刻；pq用来维持访问先后顺序
            // 这点与用bfs找cc要区别开：mark在q.offer之前，即offer时刻为访问时刻，q用来维护按层顺序
            visited.put(v, d); // mark and save the shortest distance; 
            for (int next : g.get(v).keySet())
                pq.offer(new int[] {next, d + g.get(v).get(next)}); // pq is sorted by weight
        }

        if (visited.size() < N) return -1; // some vertices can't be reached from K
        int max = 0;
        for (int d : visited.values()) max = Math.max(max, d);
        return max;
    }
   
}



