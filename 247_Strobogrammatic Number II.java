class Solution {
    private char[][] map = new char[][] {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'9', '6'}, {'8', '8'}};
    
    public List<String> findStrobogrammatic(int n) {
        List<String> ans = new ArrayList<>();
        dfs(new char[n], 0, n - 1, ans);
        return ans;
    }
    
    private void dfs(char[] chs, int lo, int hi, List<String> ans) {
        if (lo > hi) {
            if (chs.length != 1 && chs[0] == '0') return;
            ans.add(String.valueOf(chs));
            return;
        }
        
        for (char[] m : map) {
            if (lo == hi && m[0] != m[1]) continue;
            chs[lo] = m[0];
            chs[hi] = m[1];
            dfs(chs, lo + 1, hi - 1, ans);
        }
    }


}



