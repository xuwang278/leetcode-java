class Solution {
    // DFS - algs4
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (int i = 0; i < numCourses; i++) g.put(i, new HashSet<>());
        for (int[] pre : prerequisites) {
            int from = pre[1];
            int to = pre[0];
            g.get(from).add(to);
        }
        
        boolean[] onStack = new boolean[numCourses];
        boolean[] visited = new boolean[numCourses];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (dfs(g, i, onStack, visited, stack)) return new int[] {};
            }
        }
        
        int[] ans = new int[stack.size()]; // reverse postorder
        for (int i = 0; i < ans.length; i++)
            ans[i] = stack.pop();
        return ans;
    }
    
    private boolean dfs(Map<Integer, Set<Integer>> g, int i, boolean[] onStack, boolean[] visited, Stack<Integer> stack) {
        onStack[i] = true;
        visited[i] = true;
        for (int next : g.get(i)) {
            if (!visited[next]) {
                if (dfs(g, next, onStack, visited, stack)) return true;
            } else if (onStack[next]) return true;
        }
        stack.push(i);
        onStack[i] = false;
        return false;
    }

    // DFS
    // T: O(V+E)
    // S: O(V+E)
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (int i = 0; i < numCourses; i++) g.put(i, new HashSet<>());
        for (int[] pre : prerequisites) {
            int from = pre[1];
            int to = pre[0];
            g.get(from).add(to);
        }
        
        int[] state = new int[numCourses]; // 0 - unknow; 1 - visiting; 2 - visited
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < numCourses; i++)
            if (dfs(g, i, state, stack)) return new int[] {};
        
        int[] ans = new int[stack.size()];
        for (int i = 0; i < ans.length; i++)
            ans[i] = stack.pop();
        return ans;
    }
    
    private boolean dfs(Map<Integer, Set<Integer>> g , int i, int[] state, Stack<Integer> stack) {
        if (state[i] == 1) return true; // detect a cycle
        if (state[i] == 2) return false; // no clcye
        state[i] = 1;
        for (int next : g.get(i))
            if (dfs(g, next, state, stack)) return true;
        state[i] = 2;
        stack.push(i); // topology order == reverse postorder
        return false;
        
    }



    // BFS
    // T: O(V+E)
    // S: O(1)
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> g = new HashMap<>();
        for (int i = 0; i < numCourses; i++) g.put(i, new HashSet<>());
        int[] inDegree = new int[numCourses]; // pre # for each course
        for (int[] pre : prerequisites) {
            int from = pre[1];
            int to = pre[0];
            g.get(from).add(to);
            inDegree[to]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> preOrder = new LinkedList<>();
        // enqueue courses that can be taken right away
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
                preOrder.offer(i);
            }
        }
        
        while (!q.isEmpty()) {
            int top = q.poll();
            for (int next : g.get(top)) {
                if (--inDegree[next] == 0) {
                    q.offer(next);
                    preOrder.offer(next);
                }
            }
        }
        
        // check if there is any courses that are not ready to take
        for (int i = 0; i < numCourses; i++) 
            if (inDegree[i] != 0) return new int[]{};
        
        int[] ans = new int[preOrder.size()];
        for (int i = 0; i < ans.length; i++)
            ans[i] = preOrder.poll();

        return ans;
    }
}