class Solution {
    
    public List<String> generateParenthesis(int n) {
        if (n <= 0) return new ArrayList<>();
        List<String> ans = new ArrayList<>();
        
        dfs(n, n, "", ans);
        return ans;
    }
    
    private void dfs(int l, int r, String s, List<String> ans) {
        if (r < 0 || l < 0 || r < l) return;
        if (l == 0 && r == 0) {
            ans.add(s);
            return;
        }
        
        dfs(l - 1, r, s + '(', ans);
        dfs(l, r - 1, s + ')', ans);
    }


    // version 2: using StringBuilder
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(n, n, new StringBuilder(), ans);
        return ans;
    }
    
    private void dfs(int l, int r, StringBuilder sb, List<String> ans) {
        // reject cases
        if (r < 0 || l < 0 || l > r) return;
        
        // accept case
        if (l == 0 && r == 0) {
            ans.add(sb.toString());
            return;
        }

        // expand
        sb.append("(");
        dfs(l - 1, r, sb, ans);
        sb.deleteCharAt(sb.length() - 1);
        
        sb.append(")");
        dfs(l, r - 1, sb, ans);
        sb.deleteCharAt(sb.length() - 1);
    }

    public static void main(String[] args) {

    }
    
	//T: O(n!) (2^n)
	//S: O(n)
	//卡特兰数：
	//（0， n-1) (1, n-2) (2, n-3) ... (n-1, 0)
    public List<String> generateParenthesis(int n) {
    	List<String> res = new ArrayList<>();
    	if (n == 0) return res;
    	dfs(res, "", n, n);
    	return res;
    }

    // left: # of "(" left
    // right: # of ")" left
    private void dfs(List<String> res, String s, int left, int right) {
    	if (left > right) return; // no chance to generate valid s
    	if (left == 0 && right == 0) {
    		res.add(s); // s is valid and complete
    		return;
    	}
    	// pass in a new String, no need to recover in the end
    	if (left > 0) dfs(res, s + "(", left - 1, right); // left must come first!
    	if (right > 0) dfs(res, s + ")", left, right - 1);
    }


    

}









