class Solution {
    // version 1
    public String reverseVowels(String s) {
        char[] chs = s.toCharArray();
        int i = 0, j = chs.length - 1;
        while (i < j) {
            if (!isVowel(chs[i])) i++;
            else if (!isVowel(chs[j])) j--;
            else {
                exch(chs, i, j);
                i++;
                j--;
           }
        }
        return new String(chs);
    }

    // version 2
    public String reverseVowels(String s) {
        char[] chs = s.toCharArray();
        int i = 0, j = chs.length - 1;
        while (i < j) {
            while (i < j && !isVowel(chs[i])) i++;
            while (i < j && !isVowel(chs[j])) j--;
            exch(chs, i++, j--);
        }
        return new String(chs);
    }
    
    private boolean isVowel(char ch) {
        char a = Character.toLowerCase(ch);
        if (a == 'a' || a == 'e' || a == 'i' || a == 'o' || a == 'u')
            return true;
        return false;
    }
    
    private void exch(char[] chs, int i, int j) {
        char swap = chs[i];
        chs[i] = chs[j];
        chs[j] = swap;
    }

}
