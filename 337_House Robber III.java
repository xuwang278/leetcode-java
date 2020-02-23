class Solution {
    // Solution 1:
    // Recurion
    public int rob(TreeNode root) {
        if (root == null) return 0;

        int val = 0;
        if (root.left != null) val += rob(root.left.left) + rob(root.left.right);
        if (root.right != null) val += rob(root.right.left) + rob(root.right.right);
        
        // rob root and grandchildren or not rob root, go to children 
        return Math.max(root.val + val, rob(root.left) + rob(root.right));
    }

    // Solution 2:
    // Recurion with memorization (top-down DP)
    public int rob(TreeNode root) {
        return rob(root, new HashMap<>());
    }

    private int rob(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) return 0;
        if (map.containsKey(root)) return map.get(root);

        int val = 0;
        if (root.left != null) val += rob(root.left.left) + rob(root.left.right);
        if (root.right != null) val += rob(root.right.left) + rob(root.right.right);
        
        // rob root and grandchildren or not rob root, go to children 
        val = Math.max(root.val + val, rob(root.left, map) + rob(root.right, map));
        map.put(root, val);
        return val;
    }

    // Solution 3:
    // Recurion (top-down DP)
    public int rob(TreeNode root) {
        int[] num = dfs(root);
        return Math.max(num[0], num[1]);
        // num[0]: max value rob root 
        // num[1]: max value not rob root
    }
    
    private int[] dfs(TreeNode root) {
        if (root == null) return new int[2];

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        int[] res = new int[2];
        res[0] = left[1] + right[1] + root.val; /// rob root, must not rob children
        res[1] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]); // not rob root, may rob two children or not

        return res;
    }
}