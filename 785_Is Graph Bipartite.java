class Solution {
	// Solution 1: DFS
	// T: O(v + e)
	// S: O(v + e)
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        boolean[] colored = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i])
                if (dfs(graph, i, colored, visited)) return false;
                // if (bfs(graph, i, colored, visited)) return false;
        }
        return true;
    }
    
    // is there an odd-vertices cycle, i.e. non-Bipartite?
    private boolean dfs(int[][] graph, int i, boolean[] colored, boolean[] visited) {
        visited[i] = true;
        for (int next : graph[i]) {
            if (!visited[next]) {
                colored[next] = !colored[i];
                if (dfs(graph, next, colored, visited)) return true;
            } else if (colored[next] == colored[i]) return true;
        }
        return false;
    }

    // Solution 2: BFS
    // is there an odd-vertices cycle, i.e. non-Bipartite?
    private boolean bfs(int[][] graph, int i, boolean[] colored, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        visited[i] = true;
        q.offer(i);
        while (!q.isEmpty()) {
            int top = q.poll();
            for (int next : graph[top]) {
                if (!visited[next]) {
                    colored[next] = !colored[top];
                    visited[next] = true;
                    q.offer(next);
                } else if (colored[next] == colored[top]) return true;    
            }
        }
        return false;
    }
}