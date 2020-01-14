
class Solution {
	// T: O(n)
    // S: O(n)
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> list = new ArrayList<>();
        dfs(root, sum, list, new ArrayList<>());
        return list;
    }
    
    // inorder traverse
    private void dfs(TreeNode root, int sum, List<List<Integer>> list, List<Integer> path) {
        if (root == null) return;
        if (root.left == null && root.right == null && root.val == sum) {
            path.add(root.val);
            list.add(new ArrayList(path));
            path.remove(path.size() - 1); // 为同一级倒空
            return;
        }
        
        path.add(root.val);
        dfs(root.left, sum - root.val, list, path);
        dfs(root.right, sum - root.val, list, path);
        path.remove(path.size() - 1); // 为上一级倒空
    }
    

    注意与LC46 Permutations的区别:
    (1) Permutation: success case里没有删掉list最后一位, 因为回溯的工作每一层做一次就可以了
    (2) 此题不同, 比如到了某左子树是合法的叶子, 如果不立刻回溯, 同一层还需要查看右子树; 两个子树都看完了, 再回溯父数.

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, 0, new boolean[nums.length], new ArrayList<>(), ans);
        return ans;
    }
    
    private void dfs(int[] nums, int d, boolean[] visited, List<Integer> list, List<List<Integer>> ans) {
        if (d == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            list.add(nums[i]);
            dfs(nums, d + 1, visited, list, ans);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
}