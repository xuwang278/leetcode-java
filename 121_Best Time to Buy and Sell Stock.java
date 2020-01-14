class Solution {
    // T: O(n)
	// S: O(1)
    // 找到一次赚最大钱的机会
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int max = 0, min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            // today's price is larger that we can make a profit
            if (prices[i] > min) {
                max = Math.max(max, prices[i] - min);
            }
            // update current min price
            min = Math.min(min, prices[i]);
        }
        return max;
    }

    // T: O(n^2)
    // S: O(1)
    public int maxProfit(int[] prices) { 
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i; j < prices.length; j++) {
                profit = Math.max(profit, prices[j] - prices[i]);
            }
        }
        return profit;
    }
    
}