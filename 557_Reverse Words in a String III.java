class Solution {
    public String reverseWords(String s) {
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++)
            sb.append(reverseString(strs[i]) + " ");
        return sb.toString().trim();
        
    }
    
    private String reverseString(String s) {
        char[] chs = s.toCharArray();
        int lo = 0, hi = s.length() - 1;
        while (lo < hi) {
            char ch = chs[lo];
            chs[lo] = chs[hi];
            chs[hi] = ch;
            lo++;
            hi--;
        }
        return String.valueOf(chs);
    }


    public String reverseWords(String s) {
        String[] strs = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String str : strs)
            sb.append(new StringBuffer(str).reverse() + " ");
        return sb.toString().trim();
    }
}