class Solution {
    public String toLowerCase(String str) {
        char[] chs = str.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            chs[i] = Character.toLowerCase(chs[i]);
        }
        return new String(chs);
    }

    public String toLowerCase(String str) {
        char[] a = str.toCharArray();
        for (int i = 0; i < a.length; i++)
            if ('A' <= a[i] && a[i] <= 'Z')
                a[i] = (char) (a[i] - 'A' + 'a');
        return new String(a);
    }

    public String toLowerCase(String str) { 
    	return str.toLowerCase(); 
    }
    
}