class Solution {
1. overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
2. sequence: because we can't have numbers with multiple digits started with zero, we have to deal with it too.
3. a little trick is that we should save the value that is to be multiplied in the next recursion.

    public List<String> addOperators(String num, int target) {
        List<String> ans = new ArrayList<>();
        if (num == null || num.length() == 0) return ans;        
        dfs(num, 0, 0, 0, target, new StringBuilder(), ans);
        return ans;
    }
    
    // sum - current accumulation
    // multi - the one going 
    // T: O(3^L)
    private void dfs(String num, int start, long sum, long multi, int target, StringBuilder sb, List<String> ans) {
        if (start == num.length()) {
            if (target == sum) 
                ans.add(sb.toString());
            return;
        }

        for (int i = start; i < num.length(); i++) {
            if (i > start && num.charAt(start) == '0') return; // 每一个数不能带前导零
            long cur = Long.valueOf(num.substring(start, i + 1));
            int len = sb.length();
            if (start == 0) {
                sb.append(cur);
                dfs(num, i + 1, cur, cur, target, sb, ans);
                sb.delete(len, sb.length());
            } else {
                dfs(num, i + 1, sum + cur, cur, target, sb.append("+").append(cur), ans);
                sb.delete(len, sb.length());
                dfs(num, i + 1, sum - cur, -cur, target, sb.append("-").append(cur), ans);
                sb.delete(len, sb.length());
                dfs(num, i + 1, sum - multi + multi * cur, multi * cur, target, sb.append("*").append(cur), ans);
                sb.delete(len, sb.length());
            }
        }
    }
}



