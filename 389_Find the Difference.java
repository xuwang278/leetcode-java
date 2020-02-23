class Solution {
    public char findTheDifference(String s, String t) {
    	char[] s_chars = s.toCharArray();
    	char[] t_chars = t.toCharArray();
    	Arrays.sort(s_chars);
    	Arrays.sort(t_chars);
    	for (int i = 0; i < s_chars.length; i++)
    		if (s_chars[i] != t_chars[i])
    			return t_chars[i];
    	return t_chars[t_chars.length - 1];
    }

    public char findTheDifference(String s, String t) {
    	int[] cnt = new int[26];
    	for (char i : s.toCharArray()) cnt[i - 'a']++;

    	for (char j : t.toCharArray())
    		if (--cnt[j - 'a'] < 0) return j; 
    	return '/';
    }
}