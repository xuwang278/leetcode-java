class Solution {
    // Solution 2:
    // T: (n)
    // S: O(h)
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode root, int s) {
        if (root == null) return 0;
        int sum = s * 10 + root.val;
        if (root.left == null && root.right == null) return sum;
        int l = dfs(root.left, sum);
        int r = dfs(root.right, sum);
        return l + r;
    }

    // Solution 1:
    // T: (n)
    // S: O(h)
    public int sumNumbers(TreeNode root) {
        List<Integer> nums = new ArrayList<>(); // to store every num built from root to leaf
        int[] num = new int[] {0}; // num[0] to store on-building num
        dfs(root, num, nums); // preorder to construct num
        int sum = 0; // sum of num in nums
        for (int n : nums) sum += n;
        return sum;
    }
    
    private void dfs(TreeNode root, int[] num, List<Integer> nums) {
        if (root == null) return; // do noting
        
        num[0] *= 10;
        num[0] += root.val;
        
        if (root.left == null && root.right == null) { // leaf node, num is complete, add to nums
            nums.add(num[0]);
            return;
        }
        
        if (root.left != null) {
            dfs(root.left, num, nums); // extend to left
            num[0] -= root.left.val; // back track
            num[0] /= 10;
        }
        
        if (root.right != null) {
            dfs(root.right, num, nums); // extend to right
            num[0] -= root.right.val; // back track
            num[0] /= 10;
        } 
        
    }
}