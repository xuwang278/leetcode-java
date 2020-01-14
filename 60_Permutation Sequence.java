class Solution {
	// T: O(2^n)
	// S: O(n)
    public String getPermutation(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
		dfs(n, new boolean[n + 1], new ArrayList<>(), list);
		List<Integer> res = list.get(k - 1);
		StringBuilder sb = new StringBuilder();
		for (int num : res) sb.append(num);
		return sb.toString();
    }

    private void dfs(int n, boolean[] used, List<Integer> cur, List<List<Integer>> list) {
    	if (cur.size() == n) {
    		list.add(new ArrayList<>(cur));
    		return;
    	}

    	for (int i = 1; i <= n; i++) {
    		if (used[i]) continue;
    		used[i] = true;
    		cur.add(i);
    		dfs(n, used, cur, list);
    		cur.remove(cur.size() - 1);
    		used[i] = false;
    	}
    }

    // T: O(n^2)
    // S: O(n)
    public String getPermutation(int n, int k) {
    	List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) list.add(i); // 1,2 ... n
        int[] f = new int[n];
        f[0] = 1;
        for (int i = 1; i < n; i++) f[i] = f[i - 1] * i; // factorial sequence: number of permutation from i items
        k--; // counting from 0, 2, ... (k-1)
        StringBuilder sb = new StringBuilder();
        for (int i = n; i > 0; i--) {
            int index = k / f[i - 1]; 
            k = k % f[i - 1];
            sb.append(list.get(index));
            list.remove(index); // used 
        }
        return sb.toString();
    }

}