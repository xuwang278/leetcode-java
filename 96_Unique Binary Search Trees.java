class Solution {
    // SOL 1: recursion: Top-down
    public int numTrees(int n) {
        if (n == 0 || n == 1) return 1;
        int total = 0;
        for (int i = 1; i <= n; i++) {
            total += numTrees(i - 1) * numTrees(n - i);
        }
        return total;
    }

    // SOL 2: recursion with memorization: Top-down
    public int numTrees(int n) {
        int[] mem = new int[n + 1];
        mem[0] = 1;
        mem[1] = 1;
        return dfs(n, mem);
    }

    private int dfs(int n, int[] mem) {
        if (mem[n] != 0) return mem[n];
        for (int i = 1; i <= n; i++) // root
            mem[n] += dfs(i - 1, mem) * dfs(n - i, mem); // left * right
        return mem[n];
    }

    

    // DP: Bottom-up
    // T: O(n^2)
    // S: O(n)

    // n = 3
    // root: 1    left: 0, right: 2    dp[0] * dp[2]
    // root: 2    left: 1, right: 1    dp[1] * dp[1]
    // root: 3    left: 2, right: 0    dp[2] * dp[0]
    public int numTrees(int n) {
        int[] dp = new int[n + 1]; // dp[i]: num of trees can formed from continuous values: 1...i
        dp[0] = 1; // design per func
       // dp[1] = 1;
        for (int i = 1; i <= n; i++) { //: i: total nodes
            for (int j = 0; j < i; j++) { //j:  num of left children
                dp[i] += dp[j] * dp[i - j - 1]; // i - j - 1: nums of right children (minus root)
            }
        }
        return dp[n];
    }

}