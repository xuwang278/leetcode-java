class Solution {
	private int[] costs;
	private int[] memo;
	private Set<Integer> dayset;

    public int mincostTickets(int[] days, int[] costs) {
    	this.costs = costs;
    	memo = new int[366];
    	dayset = new HashSet<>();
    	for (int d : days) dayset.add(d);
    	return dfs(1);
    }	

    private int dfs(int i) {
    	if (i > 365) return 0;
    	if (memo[i] != 0) return memo[i];

    	int res;
    	if (dayset.contains(i)) 
    		res = Math.min(dfs(i + 1) + costs[0], Math.min(dfs(i + 7) + costs[1], dfs(i + 30) + costs[2]));
    	else res = dfs(i + 1);

    	memo[i] = res;
    	return res;
    }
}

class Solution {
	int[] days, costs;
	int[] memo;
	int[] durations = new int[] {1, 7, 30};

	public int mincostTickets(int[] days, int[] costs) {
		this.days = days;
	 	this.costs = costs;
	 	memo = new int[days.length];
	 	return dfs(0);
	}

	private int dfs(int i) {
	 	if (i >= days.length) return 0;
	 	if (memo[i] != 0) return memo[i];

	 	int res = Integer.MAX_VALUE;
	 	int j = 1;
	 	for (int k = 0; k < 3; ++k) {
            while (j < days.length && days[j] < days[i] + durations[k])
                j++;
            res = Math.min(res, dfs(j) + costs[k]);
        }
        memo[i] = res;
        return res;
     }

}

class Solution {
	public int mincostTickets(int[] days, int[] costs) {
		int[] minCosts = new int[366];
		boolean[] isDays = new boolean[366];
		for (int day : days) isDays[day] = true;

		for (int i = 1; i <= 365; i++) {
			if (!isDays[i]) {
				minCosts[i] = minCosts[i - 1];
				continue;
			}

			int minCost;
			minCost = minCosts[i - 1] + costs[0];
			minCost = Math.min(minCost, minCosts[Math.max(0, i - 7)] + costs[1]);
			minCost = Math.min(minCost, minCosts[Math.max(0, i - 30)] + costs[2]);

			minCosts[i] = minCost;
		}
		return minCosts[365];
	}










}