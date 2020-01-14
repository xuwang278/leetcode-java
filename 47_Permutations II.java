class Solution {
	// T: O(2^n) -> O(n! * n) 
	// S: O(n)
	// Back Tracking
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) return ans;

        Arrays.sort(nums); // bring dup together

        dfs(nums, new ArrayList<>(), new boolean[nums.length], ans);
        return ans;
    }
    
    private void dfs(int[] nums, List<Integer> list, boolean[] visited, List<List<Integer>> ans) {
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue; // when consider a dup as a new start

            if (visited[i]) continue;
            visited[i] = true;
            list.add(nums[i]);
            dfs(nums, list, visited, ans);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }

    // using set to avoid duplicates
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) 
            return new ArrayList<>();
        
        Set<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);
        dfs(nums, new ArrayList<>(), new boolean[nums.length], set);
        
        List<List<Integer>> ans = new ArrayList<>();
        ans.addAll(set);
        return ans;
    }
    
    private void dfs(int[] nums, List<Integer> list, boolean[] visited, Set<List<Integer>> set) {
        if (list.size() == nums.length) {
            set.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            //if (i > 0 && nums[i] == nums[i - 1] && visited[i - 1]) continue;
            if (visited[i]) continue;
            
            visited[i] = true;
            list.add(nums[i]);
            dfs(nums, list, visited, set);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }
    
}