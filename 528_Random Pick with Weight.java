https://www.cnblogs.com/grandyang/p/9784690.html

class Solution {
    private int[] sums;
    private Random rand;
        
    public Solution(int[] w) {
        sums = new int[w.length + 1];
        for (int i = 1; i <= w.length; i++) {
            sums[i] = sums[i - 1] + w[i - 1];
        }
        rand = new Random();
    }
    
    public int pickIndex() {
        int n = sums.length;
        int s = rand.nextInt(sums[n - 1]);
        int lo = 0, hi = n - 1; // [lo, hi)
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (sums[mid] > s) hi = mid;
            else lo = mid;
        }
        if (sums[lo] > s) return lo - 1; // 转换到w上的index
        return hi - 1;
    }
}

e.g. 
w = [1,3]
sums = [0,1,4]

random: [0, 4) i.e. 0,1,2,3
if random = 0, upper_bound = 1 (25%)

if random = 1, upper_bound = 2 (75%)
if random = 2, upper_bound = 2 
if random = 3, upper_bound = 2 

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */