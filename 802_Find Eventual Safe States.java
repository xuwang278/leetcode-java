class Solution {
	// T: O(V + E) 由于剪枝，查看过的vertex直接返回结果：因此，此算法从每个vertex开始dfs，每个vertex遍历一遍edges
	// S: O(V + E) 最深情况为，一个节点与其他全部节点相连
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> list = new ArrayList<>();
        if (graph == null || graph.length == 0) return list;
        int v = graph.length; // # of vertices
        int[] state = new int[v]; // 0 - unvisited, 1 - visiting (unsafe), 2 - visited (safe)
        
        for (int i = 0; i < v; i++) 
            dfs(graph, state, i);

        for (int i = 0; i < v; i++)
            if (state[i] == 2) list.add(i); 

        return list;
    }
    
    // is there a cycle in graph?
    private boolean dfs(int[][] graph, int[] state, int v) {
        if (state[v] == 1) return true;
        if (state[v] == 2) return false;
        state[v] = 1;
        for (int child : graph[v]) 
            if (dfs(graph, state, child)) return true; // if vertex i is on the path or just on a cycle, state[i] keeps to be 1
        state[v] = 2; 
        return false; 
    }

    // Sol 2
    // unsafe nodes: the one on the cycle or on the way to cycle, i.e. state == 1
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> ans = new ArrayList<>();
        int n = graph.length;
        int[] state = new int[n];
        for (int i = 0; i < n; i++) {
            if (!dfs(graph, i, state)) // no cycle detected
                ans.add(i);
        }
        return ans;
    }
    
    // a cycle?
    private boolean dfs(int[][] graph, int i, int[] state) {
        if (state[i] == 1) return true;
        if (state[i] == 2) return false;
        state[i] = 1;
        for (int next : graph[i])
            if (dfs(graph, next, state)) return true; 
        
        state[i] = 2;
        return false;
    }

}