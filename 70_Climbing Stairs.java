class Solution {
	// 递归法: 
    public int climbStairs(int n) {
        if (n <= 2) return n; // Fib: return 1
        else return climbStairs(n-1) + climbStairs(n-2);
    }

    // 迭代法
    public int climbStairs(int n) {
    	if (n <= 1) return 1;
    	int oneStep = 1, twoStep = 1, res = 0;
    	for (int i = 2; i <= n; i++) {
    		res = oneStep + twoStep;
    		twoStep = oneStep;
    		oneStep = res;
    	}
    	return res;
    }

}