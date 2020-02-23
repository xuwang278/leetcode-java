class Solution {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < 10; i++) 
        	dfs(i, n, res);
        return res;
    }

    private void dfs(int cur, int n, List<Integer> res) {
    	if (cur > n) return; // initially cur may larger (envoking from for loop in main)
    	res.add(cur);
    	for (int i = 0; i < 10; i++) {
    		if (10 * cur + i > n) break; // break loop, no need to check bacause i is in ascending order
    		dfs(10 * cur + i, n, res);
    	}
    }
}