class Solution {
	// T: O(n2^l) l - # of letters
	// S: O(n + n2^l) sols + stack
    public List<String> letterCasePermutation(String S) {
        List<String> ans = new ArrayList<>();
        dfs(S.toCharArray(), 0, ans);
        return ans;
    }

    private void dfs(char[] S, int i, List<String> ans) {
    	if (i == S.length) {
    		ans.add(new String(S));
    		return;
    	}

    	dfs(S, i + 1, ans); // 1st branch: directly go to next

    	// 2nd branch:
    	if (!Character.isLetter(S[i])) return;
    	S[i] ^= 1<< 5;
    	dfs(S, i + 1, ans);
    	S[i] ^= 1<< 5;
    }

    // Solution 2: DFS
    public List<String> letterCasePermutation(String S) {
    	List<String> ans = new ArrayList<>();
    	if (S == null || S.length() == 0) 
    		return ans;

    	dfs(S.toCharArray, 0, ans);
    	return ans;
    }

    private void dfs(char[] S, int i, List<String> ans) {
    	if (i == S.length) {
    		ans.add(new String(S)); // when S reaches here, it is a valid answer, String.valueOf(S)
    		return;
    	}

    	if (Character.isDigit(S[i])) {
    		dfs(S, i + 1, ans);
    	} else {
    		S[i] = Character.toLowerCase(S[i]);
    		dfs(S, i + 1, ans);
    		S[i] = Character.toUpperCase(S[i]);
    		dfs(S, i + 1, ans);
    	}
    }

    // using StringBuilder
    public List<String> letterCasePermutation(String S) {
        List<String> ans = new ArrayList<>();
        dfs(S, 0, new StringBuilder(), ans);
        return ans;
    }
    
    private void dfs(String S, int i, StringBuilder sb, List<String> ans) {
        if (i == S.length()) {
            ans.add(sb.toString());
            return;
        }
        
        // Take only one char each level so for loop doesn't need
        char c = S.charAt(i);
        if (Character.isDigit(c)) {
            sb.append(c);
            dfs(S, i + 1, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        } else {
            sb.append(Character.toLowerCase(c));
            dfs(S, i + 1, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
                
            sb.append(Character.toUpperCase(c));
            dfs(S, i + 1, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // Solution 2: BFS
 	// abc
	// abc Abc 0
	// abc aBc Abc ABc 1
	// abc abC aBc aBC Abc AbC ABc ABC 2
    public List<String> letterCasePermutation(String S) {
    	List<String> ans = new ArrayList<>();
    	if (S == null || S.length() == 0) 
    		return ans;

    	Queue<String> q = new LinkedList<>();
    	q.offer(S);

    	for (int i = 0; i < S.length(); i++) {
    		if (Character.isDigit(S.charAt(i))) continue;
    		int size = q.size();
    		for (int j = 0; j < size; j++) {
    			String cur = q.poll();
    			char[] chs = cur.toCharArray();
    			chs[i] = Character.toUpperCase(chs[i]);
    			q.offer(String.valueOf(chs));
    			chs[i] = Character.toLowerCase(chs[i]);
    			q.offer(String.valueOf(chs));
    		}
    	}
    	return new ArrayList<>(q);
    }

}