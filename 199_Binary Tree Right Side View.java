class Solution {
    
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
                if (i == size - 1) res.add(cur.val);
            }
        }
        return res;
    }
    
    // preorder: root -> right -> left 
    // add first node at each level
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    private void dfs(TreeNode root, List<Integer> res, int depth) {
        if (root == null) return;
        if (depth == res.size()) res.add(root.val);
        dfs(root.right, res, depth + 1);
        dfs(root.left, res, depth + 1);
    }
    

}



