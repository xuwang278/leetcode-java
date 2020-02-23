class Solution {
    public int superPow(int a, int[] b) {
        if (b == null || b.length == 0) return 0;
        if (a == 1) return 1;

        int power = 0;
        for (int i = b.length - 1; i >= 0; i--) {
        	power *= 10;
        	power += b[i];
        }
    }
}