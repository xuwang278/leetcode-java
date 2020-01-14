public class Solution {
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        reverseArray(words);
        String ans = "";
        for (String w : words) ans += w + " ";
        return ans.trim();
    }
    
    private void reverseArray(String[] words) {
        int lo = 0, hi = words.length - 1;
        while (lo < hi) {
            String swap = words[lo];
            words[lo] = words[hi];
            words[hi] = swap;
            lo++;
            hi--;
        }
    }

    public String reverseWords(String s) {
    	// 正则表达式，\\s表示空格字符，+表示可以有一个或多个空格字符
    	String[] strs = s.trim().split("\\s+"); 
    	String res = "";
    	for (int i = strs.length - 1; i >= 0; i--) {
    		res += strs[i] + " ";
    	}
    	return res.trim();
    }

    Sol 2: https://www.cnblogs.com/grandyang/p/4606676.html
    思路: 先整个字符串整体翻转一次，然后再分别翻转每一个单词
    public String reverseWords(String s) {
        int storeIndex = 0, n = s.length();
        StringBuilder sb = new StringBuilder(s).reverse();
        for (int i = 0; i < n; ++i) {
            if (sb.charAt(i) != ' ') {
                if (storeIndex != 0) sb.setCharAt(storeIndex++, ' ');
                int j = i;
                while (j < n && sb.charAt(j) != ' ') 
                	sb.setCharAt(storeIndex++, sb.charAt(j++));
                String t = new StringBuilder(sb.substring(storeIndex - (j - i), storeIndex)).reverse().toString();
                sb.replace(storeIndex - (j - i), storeIndex, t);
                i = j;
            }
        }
        sb.setLength(storeIndex);
        return sb.toString();
    }
}