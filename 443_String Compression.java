class Solution {
    public int compress(char[] chars) {
        int n = chars.length;
        int p = 0;
        for (int i = 1; i <= n; i++) {
        	int count = 1;
        	while (i < n && chars[i] == chars[i - 1]) {
        		i++;
        		count++;
        	}
        	chars[p++] = chars[i - 1];
        	if (count == 1) continue;
        	for (char c : String.valueOf(count).toCharArray())
                chars[p++] = c;
        }
        return p;
    }

    public int compress(char[] chars) {
        int len = chars.length;
        int p = 0;
        for (int i = 1; i < len; i++) {
            int count = 1;
            for (; i < len; i++) {
                if (chars[i] != chars[i - 1]) break;
                count++;
            }
        
            chars[p++] = chars[i - 1];
            if (count == 1) continue;
            for (char c : Integer.toString(count).toCharArray())
                chars[p++] = c;
        }
        return p;
    }
}