public class Solution {
	// Solution 1:  double revursion
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return dfs(root, sum) + 
        	   pathSum(root.left, sum) + 
        	   pathSum(root.right, sum);
    }
    
    // count from root
    private int dfs(TreeNode node, int sum) {
        if (node == null) return 0;
        return (node.val == sum ? 1 : 0) + 
               dfs(node.left, sum - node.val) + 
               dfs(node.right, sum - node.val);
    }

    // Solution 2: prefix sum
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // prefix sum -> # of ways to get the sum
        return dfs(root, 0, sum, map);
    }

    private int dfs(TreeNode root, int curSum, int target, Map<Integer, Integer> map) {
        if (root == null) return 0;

        // update the prefix sum by adding the current val
        curSum += root.val;

        // get the number of valid path, ended by the current node
        int res = map.getOrDefault(curSum - target, 0);

        // update the map with the current sum, so the map is good to be passed to the next recursion
        map.put(curSum, map.getOrDefault(curSum, 0) + 1);

        res += dfs(root.left, curSum, target, map) + dfs(root.right, curSum, target, map);
        
        
        map.put(curSum, map.get(curSum) - 1);
        return res;
    }

}