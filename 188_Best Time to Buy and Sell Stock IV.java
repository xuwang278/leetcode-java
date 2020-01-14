class Solution {
	// Time: O(kn)
	// Space: O(kn)
    public int maxProfit(int k, int[] prices) {
        if (prices == null) return 0;

        int n = prices.length;
        if (n < 2 || k <= 0) return 0;
        
        
        // resolve MLE 
        // if k >= n/2, then you can make maximum number of transactions.
        // LC 122 Stock II
        if (k >= n / 2) {
            int max = 0;
            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1]) {
                    max += prices[i] - prices[i - 1];
                }
            }
            return max;
        }

        int[][] dp = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j < n; j++) {
                int maxVal = 0;
                for (int m = 0; m < j; m++) {
                    maxVal = Math.max(maxVal, (prices[j] - prices[m]) + dp[i - 1][m]);
                }
                dp[i][j] = Math.max(dp[i][j - 1], maxVal);
            }
        }
        return dp[k][prices.length - 1];
    }

    // Sol 2
    public int maxProfit(int k, int[] prices) {
    	if (prices == null) return 0;

    	int n = prices.length;
    	if (n < 2 || k <= 0) return 0;
    	
       	
       	// resolve MLE 
       	// if k >= n/2, then you can make maximum number of transactions.
       	// LC 122 Stock II
		if (k >= n / 2) {
			int max = 0;
			for (int i = 1; i < n; i++) {
				if (prices[i] > prices[i - 1]) {
					max += prices[i] - prices[i - 1];
				}
			}
			return max;
		}

        int[][] dp = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
        	int maxDiff = dp[i - 1][0] - prices[0];
        	for (int j = 1; j < n; j++) {
        		dp[i][j] = Math.max(dp[][], prices[j] + maxDiff);
        		maxDiff = Math.max(maxDiff, dp[i - 1][j] - prices[j]);
        	}
        }
        return dp[k][prices.length - 1];
    }

   
}
