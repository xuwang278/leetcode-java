class Solution {
    // DFS - version 2: algs4
    // LC 785
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        
        buildGraph(numCourses, prerequisites, g);
        
        boolean[] onStack = new boolean[numCourses];
        boolean[] visited = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (dfs(g, i, onStack, visited)) return false;
            }
        }
        return true;
    }
    
    private void buildGraph(int numCourses, int[][] prerequisites, Map<Integer, Set<Integer>> g ) {
        for (int i = 0; i < numCourses; i++) {
            g.put(i, new HashSet<>());
        }
        
        for (int[] pre : prerequisites) {
            int from = pre[1];
            int to = pre[0];
            g.get(from).add(to);
        }
    }
    
    // Is there a cycle in graph? if yes, there is no topological order
    private boolean dfs(Map<Integer, Set<Integer>> g, int v, boolean[] onStack, boolean[] visited) {
        onStack[v] = true;
        visited[v] = true;
        for (int next : g.get(v)) {
            if (!visited[next]) {
                if (dfs(g, next, onStack, visited)) return true;
            } else if (onStack[next]) return true;
        }
        onStack[v] = false;
        return false;
    }

    // BFS
    // T: O(V+E)
    // S: O(1)
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        int[] inDegree = new int[numCourses]; // # of prerequisites for each course
        for (int i = 0; i < numCourses; i++) g.put(i, new HashSet<>());
        for (int[] pre : prerequisites) {
            int from = pre[1];
            int to = pre[0];
            g.get(from).add(to);
            inDegree[to]++;
        }
              
        // enqueue courses that can be taken right away
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            if (inDegree[i] == 0) q.offer(i);
        
        while (!q.isEmpty()) {
            int top = q.poll();
            for (int next : g.get(top))
                if (--inDegree[next] == 0) q.offer(next);
        }
        
        // check if there is any courses that are not ready to take
        for (int i = 0; i < numCourses; i++) 
            if (inDegree[i] != 0) return false;
        return true;
    }

}



