class Solution {
    // DFS
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
    	List<List<Integer>> list = new ArrayList<>();
    	levelOrderBottom(list, root, 0); // root at height 0
    	return list; // return an empty list of lists instead of null per requirement
    }

    private void levelOrderBottom(List<List<Integer>> list, TreeNode node, int height) {
    	if (node == null) return;
    	// nodes at each height stores in separate LinkedList
    	if (height == list.size()) list.add(0, new ArrayList<>()); 
    	// add nodes to a proper list
        list.get(list.size() - 1 - height).add(node.val);
        levelOrderBottom(list, node.left, height + 1);
    	levelOrderBottom(list, node.right, height + 1);
    }

    // BFS
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size(); // save it as it'll change in for loop
            List<Integer> list = new ArrayList<>();

            // size is the # to poll
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
                list.add(cur.val);
            }
            res.add(0, list); // add to head
        }
        return res;
    }
}