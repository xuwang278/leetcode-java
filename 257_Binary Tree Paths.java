class Solution {
    // T: O(n)
    // S: O(logn)
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        dfs(root, "", ans);
        return ans;
    }
    
    private void dfs(TreeNode root, String s, List<String> ans) {
        if (root == null) return;

        s += root.val;
        
        if (root.left == null && root.right == null) {
            ans.add(s);
        }
        
        dfs(root.left, s + "->", ans);
        dfs(root.right, s + "->", ans);
    }

    // StringBuilder
    // sb points to the SAME object at different level of recursion
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        dfs(root, new StringBuilder(), ans);
        return ans;
    }
    
    private void dfs(TreeNode root, StringBuilder sb, List<String> ans) {
        if (root == null) return;

        int start = sb.length();
        if (root.left == null && root.right == null) {
            sb.append(root.val);
            ans.add(sb.toString());
            sb.delete(start, sb.length()); // val若是12, 则占两位, 不能仅删除最后一位
            return;
        } 
        
        sb.append(root.val).append("->");
        dfs(root.left, sb, ans);
        dfs(root.right, sb, ans);
        sb.delete(start, sb.length());
    }

}