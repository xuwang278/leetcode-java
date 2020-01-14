class Solution {
    // DFS
    // T: O(n^2)
    // S: O(n)
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) return new ArrayList<TreeNode>();
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int min, int max) {
        List<TreeNode> result = new ArrayList<>();
        if (min == max) {
            result.add(new TreeNode(min));
            return result;
        }
        if (min > max) result.add(null);

        for (int rt = min; rt <= max; rt++) {
            List<TreeNode> leftList = generateTrees(min, rt - 1);
            List<TreeNode> rightList = generateTrees(rt + 1, max);

            for (TreeNode left : leftList) {
                for (TreeNode right : rightList) {
                    TreeNode root = new TreeNode(rt);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        return result;
    }

}