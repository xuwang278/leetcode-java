class Solution {
    // Sol 1: BFS
    // T: O(n 2^n) tree expansion -> 2^n, for each check valid takes O(n)
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null) return ans; // s.length() == 0 is not handled here
        if (s.length() == 0) {
            ans.add("");
            return ans;
        }

        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        q.offer(s);
        visited.add(s);
        boolean found = false;
        while (!q.isEmpty() && !found) {
            int size = q.size();
            // (1) explore all modifications that are same step away from the original s
            // (2) once we find a valid modification, after searching all the other
            // ones in this level, searching is done
            for (int j = 0; j < size; j++) {
                String top = q.poll();
                if (isValid(top)) {
                    ans.add(top);
                    found = true; // 如果该层有合法结果,把这些放ans中,然后就结束 while loop
                }
                
                // expand
                for (int i = 0; i < top.length(); i++) {
                    if (top.charAt(i) != '(' && top.charAt(i) != ')') continue;
                    String next = top.substring(0, i) + top.substring(i + 1);
                    if (visited.contains(next)) continue;
                    visited.add(next);
                    q.offer(next);
                }
            }

        }
        
        return ans;
    }

    private boolean isValid(String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') cnt++;
            if (c == ')') cnt--;
            if (cnt < 0) return false;
        }
        return cnt == 0;
    }

    // Sol 2: DFS - huahua version
    // https://www.youtube.com/watch?v=2k_rS_u6EBk
    统计需要删掉几个左括号l, 几个右括号r
    尝试全部删除方案: 先删除右括号, 再删除左括号
    同一层的递归, 遇到相同括号, 只考虑删除第一个, 防止重复
    最后判断经过删减的s是否合法, 放入ans中

    // T:O(2^(l+r))
    // S:O((l+r)^2) ~ O(n^2)
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null) return ans; // s.length() == 0 is not handled here
        if (s.length() == 0) {
            ans.add("");
            return ans;
        }

        // s.length() == 0 is handled by code
        int l = 0; // 最少要删除的左括号数量
        int r = 0; // 最少要删除的右括号数量

        for (char c : s.toCharArray()) {
            if (c == '(') l++; // 目前没有看到可以与它匹配的
            if (c == ')') {
                if (l == 0) r++; // 无法匹配
                else l--; // 匹配
            }
        }

        dfs(s, 0, l, r, ans);
        return ans;
    }

    private void dfs(String s, int start, int l, int r, List<String> ans) {
        if (l == 0 && r == 0) {
            if (isValid(s)) ans.add(s);
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (i > start && s.charAt(i) == s.charAt(i - 1)) continue;
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                String next = s.substring(0, i) + s.substring(i + 1);
                if (r > 0 && s.charAt(i) == ')') dfs(next, i, l, r - 1, ans); // 注意从i开始, 原来i位已经删除
                else if (l > 0 && s.charAt(i) == '(') dfs(next, i, l - 1, r, ans); // else 是精髓: 先把)删尽
            }
        }
    }

    private boolean isValid(String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') cnt++;
            if (c == ')') cnt--;
            if (cnt < 0) return false;
        }
        return cnt == 0;
    }

    // Sol 3: DFS
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        dfs(s, 0, 0, ans, new char[] {'(', ')'});  // () is valid
        return ans;
    }

    private void dfs(String s, int start, int lastRemove, List<String> ans, char[] pattern) {
    	int cnt = 0;
    	for (int i = start; i < s.length(); i++) {
    		if (s.charAt(i) == pattern[0]) cnt++;
    		if (s.charAt(i) == pattern[1]) cnt--;
    		// unbalanced - find extra )
    		if (cnt < 0) {
    			// fid a ) and delete it; avoid duplicates
    			for (int j = lastRemove; j <= i; j++) {
    				char c = s.charAt(j);
    				if (c == pattern[1] && (j == lastRemove || c != s.charAt(j - 1))) {
                        String next = s.substring(0, j) + s.substring(j + 1);
    					dfs(next, i, j, ans, pattern);
    				}
    			}
    			return;
    		}
    	}

    	String reversed = new StringBuilder(s).reverse().toString();
    	if (pattern[0] == '(') 
    		dfs(reversed, 0, 0, ans, new char[] {')', '('}); // )( is valid
    	else ans.add(reversed);
    }

}