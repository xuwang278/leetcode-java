class Solution {

	https://www.youtube.com/watch?v=Ggzbo9eVrLU&t=13s

	prices[i]: 第i天股票价格，问最大收益。交易规则如下：
		- 买前须卖掉
		- 每次只能买卖一股
		- 不限制总共的买卖次数
		- 卖完股票第二天不能买股票（要‘休息‘一天）

	Assumption: 股票价格非负

	e.g.
		prices = {1, 2, 3, 0, 2}
		maxProfit = 3

		transactions = {buy, sell, cooldown, buy, sell}
		profit：         -1    1       1      1     3  


	Solution 1: Naive Recursion (TLE) 
		对于每一天，可以做三种操作：买，卖，休息。Bruete Force的解出全部可能的
		transaction的profit，返回最大profit。
	T:O(3^n) 三叉树节点数
	S: O(n) 三叉树高度

	Solution 2： DP 
		1. 定义状态
			- hold[i]: 第i天hold股票的最大profit
			- unhold[i]: 第i天不hold股票的最大profit
		2. Target: unhold[n-1]
		3. Base Case:
			- hold[0] = -prices[0] (只能买prices[i])
			- hold[1] = max(-prices[1], -prices[0]) (前两天选一个花钱少的)
			- unhold[0] = 0 （没啥可卖的）
			- unhold[1] = Math.max(hold[0] + prices[1], unhold[0]); （买卖一次，或者什么都不做）
		4. 状态转移：
			- hold[i]取以下情况最大值
				1) 第i天买入：   unhold[i - 2] - prices[i] (如果第i天，状态由前天转移而来 - 买前必须卖光)
				2) 第i天没有买入：hold[i - 1] （状态由昨天而来）
			- unhold[i]取以下情况最大值
				1）第i天卖出：    hold[i - 1] + prices[i] 
				2) 第i天没有卖出：unhold[i - 1]

	T:O(n) 
	S: O(n) 
    public int maxProfit(int[] prices) {
    	if (prices == null || prices.length <= 1) return 0;
    	int n = prices.length;
    	int[] hold = new int[n]; // no padding: ith day, i starting from 0
    	int[] unhold = new int[n];

    	// first two days:
        hold[0] = -prices[0];
    	unhold[0] = 0;
    	hold[1] = Math.max(-prices[0], -prices[1]);
    	unhold[1] = Math.max(prices[1] - prices[0], 0);
    	
    	for (int i = 2; i < n; i++) {
    		hold[i] = Math.max(unhold[i - 2] - prices[i], hold[i - 1]);
    		unhold[i] = Math.max(hold[i - 1] + prices[i], unhold[i - 1]);
    	}
    	return unhold[n - 1];
    }

    今天的状态只与昨天和前天有关，所以采用滚动数组，空间复杂度降为O(1)
    T:O(n) 
	S: O(1) 
    public int maxProfit(int[] prices) {
    	if (prices == null || prices.length <= 1) return 0;

    	// state and base case: first 2 days
    	int pre_hold = -prices[0];
    	int pre_unhold = 0;
    	int hold = Math.max(-prices[0], -prices[1]);
    	int unhold = Math.max(prices[1] - prices[0], 0);

    	// state transformation
    	for (int i = 2; i < prices.length; i++) {
    		int next_hold = Math.max(pre_unhold - prices[i], hold);
    		int next_unhold = Math.max(hold + prices[i], unhold);
    		pre_hold = hold;
    		pre_unhold = unhold;
    		hold = next_hold;
    		unhold = next_unhold;
    	}

    	// target
    	return unhold;

    }























}