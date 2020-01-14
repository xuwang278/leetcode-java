class Solution {
    public boolean isHappy(int n) {
        Set<Integer> visited = new HashSet<>();
        while (n != 1) {
            if (visited.contains(n)) return false;
            visited.add(n);
            n = squareSum(n);
        }
        return true;
    }
    
    private int squareSum(int n) {
        int sum = 0;
        while (n != 0) {
            int LastDigit = n % 10;
            sum += LastDigit * LastDigit;
            n /= 10;
        }
        return sum;
    }
}
