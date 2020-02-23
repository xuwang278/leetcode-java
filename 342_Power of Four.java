class Solution {
    public boolean isPowerOfFour(int num) {
        long p = 1;
        while (p < num) p *= 4;
        return p == num;
    }

    public boolean isPowerOfFour(int num) {
        if (num == 0) return false; // if (n < 1) return false; 
        while (num % 4 == 0) num /= 4;
        return num == 1;
    }
}