class Solution {
    // valid IP address: 4 fields; 1-255, no leading zeros
    // T: O(3^4) -> O(1)
    // S: O(n)
    public List<String> restoreIpAddresses(String s) {
        // a valid IP address: 4 buckets; 1-255; no leading 0
        List<String> ans = new ArrayList<>();
        dfs(s, 0, 0, new StringBuilder(), ans);
        return ans;
    }

    // (1) find 4 subsets of continuous substrings that split the whole s;
    // in each subset, 1-255 and no leading 0
    private void dfs(String s, int start, int count, StringBuilder sb, List<String> ans) {
        if (count > 4) return;
        if (start == s.length() && count == 4) { // split entire s into 4 subsets
            ans.add(sb.toString());
            return;
        }
            
        for (int i = 1; i < 4 && start + i <= s.length(); i++) { // expand: at most 3 kinds
            String selected = s.substring(start, start + i); // candidate
            if ((i > 1 && selected.charAt(0) == '0') || 
                (i == 3 && Integer.valueOf(selected) > 255))
                continue; // filter out invalid ones
            
            int idx = sb.length();
            sb.append(selected);
            if (count != 3) sb.append(".");
            dfs(s, start + i, count + 1, sb, ans);
            sb.delete(idx, sb.length());
        }
    }


    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        dfs(s,"", 0, 0, res);
        return res;
    }

    private void dfs(String s, String cur, int start, int count, List<String> res) {
        if (count > 4) return;
        if (count == 4 && start == s.length()) {
            res.add(cur);
            return;
        }

        // expand: 1,2,3 digits
        for (int i = 1; i < 4; i++) {
            if (start + i > s.length()) break; 

            String selected = s.substring(start, start + i); // caididates
            if ((i > 1 && selected.charAt(0) == '0') 
                || (i == 3 && Integer.valueOf(selected) > 255)) 
                continue; 

            // a new String is passed to dfs for next level of recursion
            // while cur stills points to the original String, so no need to 
            // do explcit backtrack as using a StringBuilder
            dfs(s, cur + selected + (count == 3 ? "" : "."), start + i, count + 1, res);
        }
    }
}