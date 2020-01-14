class Solution {
    // T: O(n)
    // S: O(n)
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        
        Queue<TreeNode> q = new LinkedList<>();
        boolean reverse = false;
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            for (int s = 0; s < size; s++) {
                TreeNode top = q.poll();
                
                // extra work compare to regular BFS level traversal
                if (!reverse) {
                    list.add(top.val);
                } else {
                    list.add(0, top.val);
                }
                
                if (top.left != null) q.offer(top.left);
                if (top.right != null) q.offer(top.right);
                    
            }
            ans.add(list);
            reverse = !reverse;
            
        }
        return ans;
    }

}

