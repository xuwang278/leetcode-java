class Solution {
    // n - length of string
    // worst case: "aaaa"
    // T: O(n*2^n) isPalindrome -> O(n)
    // S: O(n) 
    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        dfs(s, 0, new ArrayList<>(), ans);
        return ans;
    }
    
    // find substes of continuous substring of s that each substring is a palindrome
    // to terminate dfs: start == s.length()
    private void dfs(String s, int start, List<String> list, List<List<String>> ans) {
        // partition of s is complete, list is one of valid lists
        if (start == s.length()) {
            ans.add(new ArrayList<>(list));
            return;
        }
        
        // expand 
        // try every possible substring that is available for this level
        for (int i = 1; start + i <= s.length(); i++) {
            String selected = s.substring(start, start + i);
            if (!isValid(selected)) continue;
            list.add(selected);
            dfs(s, start + i, list, ans);
            list.remove(list.size() - 1);
        }
    }
    
    // O(n)
    private boolean isValid(String s) {
        int lo = 0, hi = s.length() - 1;
        while (lo < hi) {
            if (s.charAt(lo++) != s.charAt(hi--))
                return false;
        }
        return true;
    }
}