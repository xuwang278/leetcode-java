// Solution 1
class BSTIterator {
    
    private int index;
    private List<Integer> list;

    public BSTIterator(TreeNode root) {
        int index = 0;
        list = new ArrayList<>();
        dfs(root);
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }
    
    /** @return the next smallest number */
    public int next() {
        return list.get(index++);
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return index < list.size();
    }

}

// Solution 2
class BSTIterator {

    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack =  new Stack<>();

        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }
    
    /** @return the next smallest number */
    public int next() {
        TreeNode last = stack.pop();
        TreeNode cur = last.right;
        while (cur != null) {
            stac.push(cur);
            cur = cur.left;
        }
        return last.val;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
