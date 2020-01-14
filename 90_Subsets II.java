class Solution {

	public List<List<Integer>> subsetsWithDup(int[] nums) {
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

    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
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