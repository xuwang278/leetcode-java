class Solution {
    // DP 递推
    // f[i][j] = minTotalOf(i, j) min sum from the very top to item at row i col j
    // f[i][j] = min(f[i - 1][j], f[i - 1][j - 1]) + triangle[i-1][j-1] 

    // triangle:             f:
    // [[2]                  [[2]
    //  [3, 4]                [5, 6]
    //  [6, 5, 7]             [11, 10, 13]
    //  [4, 1, 8, 3]]         [15, 11, 18, 16]] 
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[n + 1][n + 1]; // padding gives triangle[0][0] upper and upper-left items
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[0].length; j++) {
                f[i][j] = Integer.MAX_VALUE;
            }
        }
        
        for (int i = 1; i <= n; i++) { // row
            for (int j = 1; j <= i; j++) { // col
                f[i][j] = triangle.get(i - 1).get(j - 1); // padding
                if (i == 1 && j == 1) continue; // 1st item
                if (j == 1) f[i][j] += f[i - 1][j]; // left-most item in each row
                else if (j == i) f[i][j] += f[i - 1][j - 1]; // right-most item in each row
                else f[i][j] += Math.min(f[i - 1][j], f[i - 1][j - 1]); // other items
            }
        }
        return findMin(f[n]);
    }

    private int findMin(int[] nums) {
		int min = nums[0];
		for (int i = 1; i < nums.length; i++)
			min = Math.min(min, nums[i]);
		return min;
    }
    
    // without padding!
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[n][n]; // padding gives triangle[0][0] upper and upper-left items
        
        for (int i = 0; i < n; i++) { // row
            for (int j = 0; j < triangle.get(i).size(); j++) { // col
                f[i][j] = triangle.get(i).get(j); // padding
                if (i == 0 && j == 0) continue; // 1st item
                if (j == 0) f[i][j] += f[i - 1][j]; // left-most item in each row
                else if (j == triangle.get(i).size() - 1) f[i][j] += f[i - 1][j - 1]; // right-most item in each row
                else f[i][j] += Math.min(f[i - 1][j], f[i - 1][j - 1]); // other items
            }
        }
        return findMin(f[n - 1]);
    }

    private int findMin(int[] nums) {
		int min = nums[0];
		for (int i = 1; i < nums.length; i++)
			min = Math.min(min, nums[i]);
		return min;
    }
    
    // DP
    // T: O(n^2)
    // S: O(n)
    // 自底向上，每次比较该节点两个孩子的数值大小，将小的那个孩子节点的值再加上本身节点的值即为该节点以下的最小路径和，
    // 以此类推直到顶点。
    public int minimumTotal(List<List<Integer>> triangle) {
        int [] dp = new int[triangle.size() + 1];
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }

    // without padding!
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n-1).get(i);
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }

    
   
