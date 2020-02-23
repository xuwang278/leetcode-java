class Solution {
    public String tree2str(TreeNode t) {
        if (t == null) return "";
        String ans = t.val + "";
        String l = tree2str(t.left);
        String r = tree2str(t.right);

        if (l == "" && r == "") return ans;
        if (l == "") return ans + "()" + "(" + r + ")";
        if (r == "") return ans + "(" + l + ")";
        return ans + "(" + l + ")" + "(" + r + ")";
    }
    
    
    
}