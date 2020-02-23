class Solution {
    public int fib(int N) {
        if (N == 0) return 0;
        if (N == 1) return 1;
        return fib(N - 1) + fib(N - 2);
    }

    public int fib(int N) {
    	Map<Integer, Integer> map = new HashMap<>();
    	return dfs(N, map);
    }

    private int dfs(int n, Map<Integer, Integer> map) {
    	if (map.containsKey(n)) return map.get(n);
    	if (n == 0 || n == 1) {
    		map.put(n, n);
    		return n;
    	}

    	int p1 = dfs(n - 1, map);
    	int p2 = dfs(n - 2, map);
    	map.put(n, p1 + p2);
    	return p1 + p2;
    }
}