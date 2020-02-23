class Solution {
	// T: O(V + E)
	// S: O (V)
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n];
        dfs(rooms, 0, visited);
        // bfs(rooms, 0, visited);
        for (int i = 0; i < n; i++)
            if (!visited[i]) return false;
        return true;
    }
    
    private void dfs(List<List<Integer>> rooms, int i, boolean[] visited) {
        if (visited[i]) return;
        visited[i] = true;
        for (int next : rooms.get(i))
            dfs(rooms, next, visited);
    }

    private void bfs(List<List<Integer>> rooms, int i, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        visited[i] = true; // mark and offer
        q.offer(i);
        while (!q.isEmpty()) {
            int top = q.poll();
            for (int next : rooms.get(top)) {
                if (visited[next]) continue;
                visited[next] = true;
                q.offer(next);
            }
        }
    }
    
}