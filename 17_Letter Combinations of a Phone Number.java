class Solution {
    private String[] dict = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    
    // Sol 1: dfs
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) return ans;
        
        dfs(digits, 0, new StringBuilder(), ans);
        return ans;
    }
    
    private void dfs(String digits, int idx, StringBuilder sb, List<String> ans) {
        if (idx == digits.length()) {
            ans.add(sb.toString());
            return;
        }
        
        String letters = dict[digits.charAt(idx) - '0'];
        for (char c : letters.toCharArray()) {
            sb.append(c);
            dfs(digits, idx + 1, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    // Sol 2: bfs
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>();
        if (digits == null || digits.length() == 0) return ans;

        ans.add("");
        for (char digit : digits.toCharArray()) {
            List<String> list = new ArrayList<>();
            for (String s : ans) {
                String letters = dict[digit - '0'];
                for (char c : letters.toCharArray()) {
                    list.add(s + c);
                }
            }
            ans = list;
        }
        
        return ans;
    }
    
    
}