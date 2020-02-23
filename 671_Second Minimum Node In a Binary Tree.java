class Solution {
	// Solution 1: 
	// T: O(n + nlogn + n)
	// S: O(n + logn)
    public int findSecondMinimumValue(TreeNode root) {
        List<Integer> vals = new ArrayList<>();
        dfs(root, vals);
        Collections.sort(vals);
        int first = vals.get(0);
        for (int i = 1; i < vals.size(); i++)
        	if (vals.get(i) != first)
        		return vals.get(i);

        return -1;
    }

    private void dfs(TreeNode root, List<Integer> vals) {
    	if (root == null) return;
    	dfs(root.left, vals);
    	vals.add(root.val);
    	dfs(root.right, vals);
    }

    // Solution 2: Tree
    // T: O(logn)
    // S: O(logn)
    public int findSecondMinimumValue(TreeNode root) {
    	Set<Integer> tree = new TreeSet<>();
    	dfs(root, tree);
    	Iterator<Integer> iterator = tree.iterator();
    	int count = 0;
    	while (iterator.hasNext()) {
    		count++;
    		int res = iterator.next();
    		if (count == 2) return res;
    	}
    	return -1;
    }

    private void dfs(TreeNode root, Set<Integer> tree) {
    	if (root == null) return;
    	tree.add(root.val);
    	dfs(root.left, tree);
    	dfs(root.right, tree);
    }

    // Solution 3: PriorityQueue
    // T: O(logn)
    // S: O(logn)
    public int findSecondMinimumValue(TreeNode root) {
    	PriorityQueue<Integer> pq = new PriorityQueue<>();
    	dfs(root, pq);
    	int first = pq.poll();
    	while (!pq.isEmpty()) {
    		int next = pq.poll();
    		if (first != next) return next;
    	}
    	return -1;

    }

    private void dfs(TreeNode root, PriorityQueue<Integer> pq) {
    	if (root == null) return;
    	pq.add(root.val);
    	dfs(root.left, pq);
    	dfs(root.right, pq);
    }
}