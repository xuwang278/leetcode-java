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
    
	// T: O(n)
	// S: O(n)
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) return new int[]{};
        
		Map<Integer, Integer> map = new HashMap<>(); // sum -> freq
        int[] max = new int[]{Integer.MIN_VALUE}; // most freq
        
		dfs(root, max, map); // build map
		
		List<Integer> list = new ArrayList<>(); 
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int sum = entry.getKey();
            int freq = entry.getValue();
            if (freq != max[0]) continue;
            list.add(sum);
        }
		
		int[] ans = new int[list.size()];
		for (int i = 0; i < ans.length; i++)
            ans[i] = list.get(i);
        
		return ans;
	}

	private int dfs(TreeNode root, int[] max, Map<Integer, Integer> map) {
		if (root == null) return 0;
		int l = dfs(root.left, max, map);
		int r = dfs(root.right, max, map);
		int sum = l + r + root.val;
        int freq = map.getOrDefault(sum, 0) + 1;
        map.put(sum, freq);
        max[0] = Math.max(max[0], freq);
		return sum;
	}
}