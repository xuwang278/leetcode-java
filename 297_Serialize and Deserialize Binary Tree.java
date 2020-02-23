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
	// Encodes a tree to a single string.
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

    // Decodes your encoded data to tree.
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

    // Solution 2: Iterative DFS (Preorder)
    // Stack is used for back track
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                sb.append(cur.val).append(SPLITER);
                stack.push(cur); // before going deeper, save cur to 'memory'
                cur = cur.left; // go next level
            } else {
                sb.append(NN).append(SPLITER);
                cur = stack.pop(); // back to upper level (find parent)
                cur = cur.right; // when encounter a null left , move to right child
            }
        }
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if (data.length()==0) return null;
        String[] node = data.split(SPLITER);
        int n = node.length;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(Integer.valueOf(node[0]));
        TreeNode cur = root;
        stack.push(cur);
        
        int i = 1;
        while (i < n) {
        	// next one is left child
        	// keep on adding left children until null is touched
            while (i < n && !node[i].equals(NN)) {
                cur.left = new TreeNode (Integer.valueOf(node[i++]));
                cur = cur.left;
                stack.push(cur); 
            }
            
            while (i < n && node[i].equals(NN)) {
                cur = stack.pop(); // back track
                i++;
            }
            
            // add right right from deeper to upper level
            if (i < n) {
                cur.right = new TreeNode(Integer.valueOf(node[i++]));
                cur = cur.right;
                stack.push(cur);
            }
        }
        return root;
    }

    // Solution 3: BFS (level traversal: from L to R)
    // Queue is used for 
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(root.val).append(SPLITER);
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur.left == null) sb.append(NN).append(SPLITER);
            else {
                sb.append(cur.left.val).append(SPLITER);
                q.offer(cur.left); // 
            }
            
            if (cur.right == null) sb.append(NN).append(SPLITER);
            else {
                sb.append(cur.right.val).append(SPLITER);
                q.offer(cur.right);
            }
        }
        return sb.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] node = data.split(SPLITER);
        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.valueOf(node[0]));
        q.offer(root);
        int i = 1;
        while (!q.isEmpty()) {
            Queue<TreeNode> nextQ = new LinkedList<>(); // nodes to be on next level
            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                if (node[i].equals(NN)) cur.left = null;
                else {
                    cur.left = new TreeNode(Integer.valueOf(node[i]));
                    nextQ.offer(cur.left);
                }
                i++;
                
                if (node[i].equals(NN)) cur.right = null;
                else {
                    cur.right = new TreeNode(Integer.valueOf(node[i]));
                    nextQ.offer(cur.right);
                }
                i++;
            }
            q = nextQ;
        }
        return root;
    }

}

