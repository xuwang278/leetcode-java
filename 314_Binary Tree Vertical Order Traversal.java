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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        
        // 优先按照x排序, 若x相同, 则自上而下,从左到右排序 (BFS遍历顺序为从上到下 从左到右, 因此自然满足条件)
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>(); // support level order traversal
        q.offer(new Pair(root, 0));
        while (!q.isEmpty()) {
            Pair top = q.poll();
            TreeNode node = top.node;
            int hd = top.hd;
            map.putIfAbsent(hd, new ArrayList<>());
            map.get(hd).add(node.val); // same hd: left from right
            if (node.left != null) q.offer(new Pair(node.left, hd - 1));
            if (node.right != null) q.offer(new Pair(node.right, hd + 1));
        }
        
        while (!map.isEmpty()) {
            Map.Entry<Integer, List<Integer>> entry = map.pollFirstEntry(); // the one with smallest hd
            ans.add(entry.getValue());
        }
        
        return ans;
    }
    
    private class Pair {
        private TreeNode node;
        private int hd;
        
        public Pair(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }



}