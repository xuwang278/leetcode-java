class Solution {
    // T: O(n)
	// S: O(1)
    // 找到能挣钱的全部机会: 前者大于后者
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                sum += prices[i] - prices[i - 1];
            }
        }
        return sum;
    }

}