class Solution {
	// Solution 1:
	// T: O()
	// S: O()
    public int coinChange(int[] coins, int amount) {
    	return coinChange(coins, amount, new int[amount + 1]);
    }

    private int coinChange(int[] coins, int amount, int[] dp) {
    	if (amount < 0) return -1; // no solution
    	if (amount == 0) return 0; // done
    	if (dp[amount] != 0) return dp[amount];

    	int min = Integer.MAX_VALUE;
    	for (int coin : coins) {
    		int res = coinChange(coins, amount - coin, dp);
    		if (res != -1) min = Math.min(min, 1 + res);
    	}
    	dp[amount] = min == Integer.MAX_VALUE ? -1 : min;
    	return dp[amount];
    }

	// Solution 2: Bottom-up 
	// T: O(coins.length * amount)
	// S: O(amount)
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++)
                if (dp[i - coin] != Integer.MAX_VALUE)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    // slower than solution 2: beat 85%
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE)
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    // sort coins: beats 85%
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = coins.length - 1; j >= 0; j-- ){
                if (coins[j] <= i && dp[i - coins[j]] != Integer.MAX_VALUE)
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

}



