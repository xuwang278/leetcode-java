class Solution {
	int N;
	int[] ans, count;
	List<Set<Integer>> g;
	

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        this.N = N;
        ans = new int[N];
        count = new int[N];
        g = new ArrayList<>();
        Arrays.fill(count, 1);

        for (int i = 0; i < N; i++) g.add(new HashSet<>());
        for (int[] e : edges) {
        	g.get(e[0]).add(e[1]);
        	g.get(e[1]).add(e[0]);
        }

        dfs(0, -1);
        dfs2(0, -1);

        return ans;
    }

    private void dfs(int node, int parent) {
    	for (int child : g.get(node)) {
    		if (child != parent) {
    			dfs(child, node);
    			count[node] += count[child];
    			ans[node] += ans[child] + count[child];
    		}
    	}
    }

    public void dfs2(int node, int parent) {
        for (int child: graph.get(node))
            if (child != parent) {
                ans[child] = ans[node] - count[child] + N - count[child];
                dfs2(child, node);
            }
    }
}