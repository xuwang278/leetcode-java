/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    // Solution 1: Recursion
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        dfs(root, ans);
        return ans;
    }
    
    private void dfs(Node root, List<Integer> ans) {
        if (root == null) return;
        ans.add(root.val);
        for (Node n : root.children) dfs(n, ans);
    }
    
    // Solution 2: Iteration
    public List<Integer> preorder(Node root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            ans.add(cur.val);
            for (int i = cur.children.size() - 1; i >= 0; i--)
                stack.push(cur.children.get(i));
        }
        return ans;
    }
}












