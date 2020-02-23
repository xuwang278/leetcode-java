/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {
    // Solution 1: Same as 297
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString();
    }
    
    private void dfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null").append(",");
            return;
        }
        sb.append(root.val).append(",");
        dfs(root.left, sb);
        dfs(root.right, sb);
    }

    public TreeNode deserialize(String data) {
        Queue<String> q = new LinkedList<>();
        String[] nodes = data.split(",");
        for (String n : nodes) q.offer(n);
        return build(q);
    }
    
    private TreeNode build(Queue<String> q) {
        String top = q.poll();
        if (top.equals("null")) return null;
        TreeNode node = new TreeNode(Integer.valueOf(top));
        node.left = build(q);
        node.right = build(q);
        return node;
    }

    // Solution 2: 
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString();
    }
    
    private void dfs(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null").append(","); // still "null" for LC testing
            return;
        }
        sb.append(root.val).append(",");
        dfs(root.left, sb);
        dfs(root.right, sb);
    }

    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        TreeNode root = null;
        for (String node : nodes) {
            if (node.equals("null")) continue; // ignore
            root = insert(root, Integer.valueOf(node));
        }
           
        return root;
    }

    // using BST property
    private TreeNode insert(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insert(root.left, val);
        else if (val > root.val) root.right = insert(root.right, val);
        else root.val = val; // will not encounter in this problem
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));