class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> list = new ArrayList<>();
            while (size-- > 0) {
                Node cur = q.poll();
                list.add(cur.val);
                for (Node n : cur.children) {
                    q.offer(n);
                }
            }
            
            ans.add(list);
        }
        return ans;
    }
}