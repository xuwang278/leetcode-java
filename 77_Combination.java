class Solution {
    // time: O(2^n)
    // space: O(n)
    // C(k,n)
    public List<List<Integer>> combine(int n, int k) {
    	List<List<Integer>> ans = new ArrayList<>();
    	dfs(n, k, 1, new ArrayList<Integer>(), ans);
    	return ans;
    }

    private void dfs(int n, int k, int s, List<Integer> cur, List<List<Integer>> ans) {
    	if (cur.size() == k) {
    		ans.add(new ArrayList<>(cur));
    		return;
    	}

    	for (int i = s; i <= n; i++) {
    		cur.add(i);
    		dfs(n, k, i + 1, cur, res);
    		cur.remove(cur.size() - 1);
    	}
    }   


    // iterative
    public List<List<Integer>> combine(int n, int k) {


    }

}