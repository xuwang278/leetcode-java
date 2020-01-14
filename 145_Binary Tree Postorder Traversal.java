class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res);
        return res;
    }

    private void dfs(TreeNode root, List<Integer> res) {
        if (root == null) return;
        dfs(root.left, res);
        dfs(root.right, res);
        res.add(root.val);
    }

    // Solution 2: Iterative DFS
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                ans.add(0, cur.val); // reverse preorder
                cur = cur.right; // reverse preorder
            }

            cur = stack.pop();
            cur = cur.left; // reverse preorder
        }
        return ans;
    }
}