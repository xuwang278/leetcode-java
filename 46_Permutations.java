class Solution {
	// T: O(2^n) -> O(n! * n) 
	// S: O(n)
	// Back Tracking
    // Distinct items in nums
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) 
            return ans;
        
        dfs(nums, new ArrayList<>(), new boolean[nums.length], ans);
        return ans;
    }
    
    private void dfs(int[] nums, List<Integer> list, boolean[] visited, List<List<Integer>> ans) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            list.add(nums[i]);
            dfs(nums, list, visited, ans);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }

}