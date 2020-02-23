class Solution {
    public void reverseString(char[] s) {
        int lo = 0, hi = s.length - 1;
        while (lo < hi) {
            swap(s, lo, hi);
            lo++;
            hi--;
        }
    }

    private void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
}