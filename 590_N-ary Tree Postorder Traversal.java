class Solution {
    // Solution 1: Recursion
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans);
        return ans;
    }
    
    private void dfs(Node root, List<Integer> ans) {
        if (root == null) return;
        for (Node n : root.children) dfs(n, ans);
        ans.add(root.val);
    }
    
    // Solution 2: Iteration
    public List<Integer> postorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            ans.add(0, cur.val); // reverse preorder
            for (int i = 0; i < cur.children.size(); i++)
                stack.push(cur.children.get(i));
        }
        return ans;
    }
}