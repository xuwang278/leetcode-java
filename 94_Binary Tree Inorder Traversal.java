class Solution {
    // left -> root -> right
    // T: O(n)
    // S: O(logn)
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(TreeNode root, List<Integer> res) {
        if (root == null) return;
        dfs(root.left, res);
        res.add(root.val);
        dfs(root.right, res);
    }

    // Solution 2: Iterative DFS
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;

        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (cur != null || !stack.isEmpty()) {
            // try to go as left as possible  
            // load cur to call stack before going left as deep as possible
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            // when cur == null -> need to back track
            // back track cur's parent (at upper level)
            cur = stack.pop(); 
            res.add(cur.val); // HANDLE ROOT IN MIDDLE (INORDER)

            // go right
            cur = cur.right;
        }
        return res;
    }

}