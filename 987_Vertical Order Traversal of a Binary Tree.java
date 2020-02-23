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
    // Code from LC 314 won't work because it only consider sort by x
    // it should be sorted by position and those on the same (x, y) are sorted by vals
    
    // Solution 1: treeMap
    // sort by x and then by y and then by values
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        dfs(root, 0, 0, map);
        
        List<List<Integer>> ans = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> nodesWithSameX : map.values()) {
            // x坐标相同的nodes是同一列的, 因此一定是放在一个list里
            // 放的顺序要控制: y从小到大; 若y相同, 按数值从小到大
            List<Integer> list = new ArrayList<>();
            for (PriorityQueue<Integer> pq : nodesWithSameX.values()) {
                while (!pq.isEmpty()) list.add(pq.poll());
            }
            ans.add(list);
        }
        return ans;
    }
    
    private void dfs(TreeNode root, int x, int y, TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map) {
        if (root == null) return;
        map.putIfAbsent(x, new TreeMap<>());
        map.get(x).putIfAbsent(y, new PriorityQueue<>());
        map.get(x).get(y).offer(root.val);
        dfs(root.left, x - 1, y + 1, map);
        dfs(root.right, x + 1, y + 1, map);
    }


    // Solution 2: can be extended to LC 314
    private class Node implements Comparable<Node> {
        private int x;
        private int y;
        private int val;
        
        public Node(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }

        @Override
        public int compareTo(Node that) {
            if (this.x != that.x) return Integer.compare(this.x, that.x);
            if (this.y != that.y) return Integer.compare(this.y, that.y);
            return Integer.compare(this.val, that.val);
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<Node> nodes = new ArrayList<>();
        dfs(root, 0, 0, nodes);
        Collections.sort(nodes);

        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        int prev = nodes.get(0).x; // the samllest x

        for (Node node : nodes) {
            // need to create a new sublist to store nodes with the same x
            if (node.x != prev) {
                prev = node.x;
                ans.add(new ArrayList<>());
            } 
            ans.get(ans.size() - 1).add(node.val); // nodes are sorted so just add one by one
        }
        return ans;
    }

    private void dfs(TreeNode root, int x, int y, List<Node> nodes) {
        if (root == null) return;
        nodes.add(new Node(x, y, root.val));
        dfs(root.left, x - 1, y + 1, nodes);
        dfs(root.right, x + 1, y + 1, nodes);
    }
    

}


