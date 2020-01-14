class Solution {

	// Solution 2: faster
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) 
            return ans;
        
        ans.add(new ArrayList<>());
        dfs(nums, 0, new ArrayList<>(), ans);
        
        return ans;
    }
    
    private void dfs(int[] nums, int start, List<Integer> list, List<List<Integer>> ans) {
        // if (start == nums.length) {
        //     //ans.add(new ArrayList<>(list)); // base case is unnecessary
        //     return;
        // }
        
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);

            ans.add(new ArrayList<>(list));

            dfs(nums, i + 1, list, ans);
            list.remove(list.size() - 1);
        }
    }

    // Solution 1: 
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length == 0) return list;
        for (int i = 0; i <= nums.length; i++) {
        	dfs(nums, i, 0, new ArrayList<>(), list);
        }
        return list;
    }

    private void dfs(int[] nums, int k, int start, List<Integer> cur, List<List<Integer>> res) {
    	if (cur.size() == k) {
    		res.add(new ArrayList<>(cur));
    		return;
    	}

    	for (int i = start; i < nums.length; i++) {
    		cur.add(nums[i]);
    		dfs(nums, k, i + 1, cur, res);
    		cur.remove(cur.size() - 1);
    	}
    }

    // Handle Dup - LC 90
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length == 0) return list;

        Arrays.sort(nums);

        for (int i = 0; i <= nums.length; i++) {
            dfs(nums, i, 0, new ArrayList<>(), list);
        }
        return list;
    }

    private void dfs(int[] nums, int k, int start, List<Integer> cur, List<List<Integer>> res) {
        if (cur.size() == k) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = start; i < nums.length; i++) {

            if (i > start && nums[i] == nums[i - 1]) continue;
            
            cur.add(nums[i]);
            dfs(nums, k, i + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }




}