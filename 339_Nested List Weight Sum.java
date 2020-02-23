class Solution {
	// DFS
    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> nestedList, int depth) {
    	int sum = 0;
    	for (NestedInteger n : nestedList) {
    		if (n.isInteger()) sum += n.getInteger() * depth;
    		else 
    			sum += dfs(n.getList(), depth + 1);
    	}
    	return sum;
    }

    // BFS
    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null) return 0;

        int sum = 0;
        int step = 1;
        Queue<NestedInteger> q = new LinkedList<>();
        for (NestedInteger n : nestedList)
        	q.offer(n);

        while (!q.isEmpty()) {
        	int size = q.size();
        	for (int s = 0; s < size; s++) {
        		NestedInteger top = q.poll();
        		if (top.isInteger()) sum += top.getInteger() * step;
        		else {
        			for (NestedInteger n : top.getList())
        				q.offer(n); 
        		}
        	}
        	step++;
        }

        return sum;
    }

}