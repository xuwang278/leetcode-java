class Solution {
    // T: O(n)
    // S: O(n)
    public int longestPalindrome(String s) {   
        if (s == null || s.length() == 0) return 0;
        Set<Character> set = new HashSet<>();
        int cnt = 0;
        char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (set.contains(chs[i])) {
                set.remove(chs[i]);
                cnt++;
            } else set.add(chs[i]);
        }
        if (!set.isEmpty()) return cnt * 2 + 1;
        return cnt * 2;
    }

    // T: O(n)
    // S: O(1)
    public int longestPalindrome(String s) {   
        int[] count = new int[128];
        for (char c : s.toCharArray()) count[c]++;

        int res = 0;
        for (int v : count) {
            ans += v / 2 * 2;
            if(ans % 2 == 0 && v % 2 == 1) ans++;
        }
        return ans;
    }

    // https://www.youtube.com/watch?v=4GU7QvWffHQ
    // T: O(n)
    public int longestPalindrome(String s) {        
        int[] freqs = new int[128];
        for (int i = 0; i < s.length(); ++i)
            ++freqs[s.charAt(i)];
        
        int ans = 0;
        int odd = 0;
        
        for (int freq: freqs) {
            ans += freq & (~1);
            odd |= freq & 1;
        }
        
        return ans + odd;
    }

}