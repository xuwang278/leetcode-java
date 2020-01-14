class Solution {
    // T: O(nloglogn) -> ~O(n)
    // S: O(n)
    public int  countPrimes(int n) {
        if (n <= 2) return 0;
        int count = 0;
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for (int i = 2; i < n; i++) {
            if (!isPrime[i]) continue;
            count++;
            for (int j = i * i; j < n; j += i) isPrime[j] = false;
        }
        return count;
    }
}