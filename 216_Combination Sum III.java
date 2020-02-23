class Solution {

	public List<List<Integer>> combinationSum3(int k, int n) {
		if (k == 0 || n <= 0) {
			return new ArrayList<>();
		}

        List<List<Integer>> ans = new ArrayList<>();
        dfs(k, n, 0, 1, new ArrayList<>(), ans);
        return ans;
    }
    
    private void dfs(int k, int n, int sum, int start, List<Integer> list, List<List<Integer>> ans) {
        if (list.size() == k) {
        	if (sum == n) {
        		ans.add(new ArrayList<>(list));
        	}
            return;
        }
    
        for (int i = start; i < 10; i++) {
            list.add(i);
            dfs(k, n, sum + i, i + 1, list, ans);
            list.remove(list.size() - 1);
        }
    }


    // Refer: 40, 39
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(k, n, 1, new ArrayList<Integer>(), res);
        return res;
    }

    private void dfs(int k, int n, int start, List<Integer> cur, List<List<Integer>> res) {
        if (n < 0) return;
        
        if (cur.size() == k && n == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int i = start; i <= 9; i++) {
            cur.add(i);
            dfs(k, n - i, i + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }

    // beat 100%
    // 剪枝
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (k == 0 || n <= 0) return res;
        
        List<Integer> list = new ArrayList<>();
        dfs(k, n, 1, list, res);
        return res;
    }
    
    private void dfs(int k, int n, int start, List<Integer> list, List<List<Integer>> res) {
        if (list.size() == k && n == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
    
        for (int i = start; i <= 9; i++) {
            if (i > n) break;
            
            list.add(i);
            dfs(k, n - i, i + 1, list, res);
            list.remove(list.size() - 1);
        }
    }
}