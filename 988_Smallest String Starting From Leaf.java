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
    // postorder
    // Fails for case [4, 0, 1, 1]
    public String smallestFromLeaf(TreeNode root) {
        if (root == null) return null;
        return dfs(root);
    }
    
    private String dfs(TreeNode node) {
        char c = (char) (node.val + 'a'); // new element
        String l = null, r = null;
        if (node.left != null) l = dfs(node.left);
        if (node.right != null) r = dfs(node.right);
        
        // create a new String object and return it 
        if (l == null && r == null) return "" + c; // leaf node just return root.val
        if (l == null) return r + c; // append c to result from r
        if (r == null) return l + c; // append c to result from l
        return l.compareTo(r) > 0 ? r : l + c; // append to smaller one
    }

    // Solution 1: Back tracking + sort
    public String smallestFromLeaf(TreeNode root) {
        if (root == null) return "";
        
        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(root, sb, list);
        Collections.sort(list);
        return list.get(0);
    }
    
    private void dfs(TreeNode root, StringBuilder sb, List<String> list) {
        if (root == null) return;
        sb.append((char) ('a' + root.val));
        if (root.left == null && root.right == null) {
            sb.reverse();
            list.add(sb.toString());
            sb.reverse();
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
        
        dfs(root.left, sb, list);
        dfs(root.right, sb, list);
        sb.deleteCharAt(sb.length() - 1);
    }

    // Solution 2: Back tracking + select
    public String smallestFromLeaf(TreeNode root) {
        String ans = ""; // ans always points to the "" string;
        dfs(root, ans, new StringBuilder());
        return ans;
    }

    private void dfs(TreeNode root, String ans, StringBuilder sb) {
        if (root == null) return;
        sb.append((char) ('a' + root.val));

        if (root.left == null && root.right == null) {
            sb.reverse();
            String s = sb.toString();
            sb.reverse();

            if (s.compareTo(ans) < 0) ans = s;
        }

        dfs(root.left, ans, sb);
        dfs(root.right, ans, sb);
        sb.deleteCharAt(sb.length() - 1);
    }
    
}