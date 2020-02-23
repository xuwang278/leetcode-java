/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
	// Solution 1: DFS - preorder 
	// T: O(n) n - # of nodes in tree
	// S: O(n)
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return dfs(root, k, set);
    }
    private boolean dfs(TreeNode root, int k, Set<Integer> set) {
    	if (root == null) return false;
    	if (set.contains(k - root.val)) return true; // this node and that node in set
    	set.add(root.val);
    	return dfs(root.left, k, set) || dfs(root.right, k, set);
    }

    // Solution 2: DFS - inorder
    // T: O(n) 
	// S: O(n)
    public boolean findTarget(TreeNode root, int k) {
    	List<Integer> nums = new ArrayList<>();
    	dfs(root, nums);
    	for (int i = 0, j = nums.size() - 1; i < j; ) {
    		if (nums.get(i) + nums.get(j) == k) return true;
    		if (nums.get(i) + nums.get(j) < k) i++;
    		else j--;
    	}
    	return false;
    }

    // inorder -> nums is sorted
    private void dfs(TreeNode root, List<Integer> nums) {
    	if (root == null) return;
    	dfs(root.left, nums);
    	nums.add(root.val);
    	dfs(root.right, nums);
    }

    // Solution 3: Tree operations
    // T: O(nh)
    // S: O(h) recursion floor/ceil or O(1) iterative
    public boolean findTarget(TreeNode root, int k) {
    	if (root == null) return false;
    	TreeNode min = min(root);
    	TreeNode max = max(root);

    	for (; min != max; ) {
    		int sum = min.val + max.val;
    		if (sum > k) max = floor(root, max);
    		else if (sum < k) min = ceiling(root, min);
    		else return true;
    	}
    	return false;
    }

    // smallest node, the one on the very left
    private TreeNode min(TreeNode root) {
    	if (root.left == null) return root;
    	return min(root.left);
    }

    // largest node, the one on the very right
    private TreeNode max(TreeNode root) {
    	if (root.right == null) return root;
    	return max(root.right);
    }


    // largest one that is smaller than node
    // T: O(h) - height of tree
    // S: O(h)
    private TreeNode floor(TreeNode root, TreeNode node) {
    	if (root == null) return null;
    	// if (node.val == root.val) return root;
    	if (node.val <= root.val) return floor(root.left, node); // as root > node, node's floor must on left
    	TreeNode t = floor(root.right, node); // root is a candidate but it may also on the right, i.e. t
    	if (t != null) return t;
    	else return root;
    }

    // smallest one that is larger than node
    // T: O(h)
    // S: O(h)
	private TreeNode ceiling(TreeNode root, TreeNode node) {
		if (root == null) return null;
		// if (root.val == node.val) return root;
		if (node.val >= root.val) return ceiling(root.right, node);
		TreeNode t = ceiling(root.left, node);
		if (t != null) return t;
		else return root;
    }

    // T: O(h) 
    // S: O(1)
    private TreeNode floor2(TreeNode root, TreeNode node) {
    	TreeNode pre = null;
    	TreeNode cur = root;
    	while (cur != null) {
    		if (cur.val < node.val) {
    			pre = cur;
    			cur = cur.right;
    		} else cur = cur.left;
    	}
    	return pre;
    }

    // T: O(h) 
    // S: O(1)
    private TreeNode ceiling2(TreeNode root, TreeNode node) {
    	TreeNode next = null;
    	TreeNode cur = root;
    	while (cur != null) {
    		if (cur.val > node.val) {
    			next = cur;
    			cur = cur.left;
    		} else cur = cur.right;
    	}
    	return next;
    }















}