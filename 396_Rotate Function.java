class Solution {
	// Solution 1: TLE 
	// O(n^3)
	public int maxRotateFunction(int[] A) {
        if (A == null || A.length == 0) return 0;
        
        int n = A.length;
        int max = Integer.MIN_VALUE;
        // O(n)
        for (int start = 0; start < n; start++) {
            int[] rotate = new int[n];
            // O(n)
            for (int j = 0, idx = start; j < n; j++, idx = (idx + 1) % n) {
                rotate[j] = A[idx];
            }
            max = Math.max(max, sum(rotate));
        }
        return max;
    }
    
    // O(n)
    private int sum(int[] a) {
        int sum = 0;
        for (int i = 0; i < a.length; i++)
            sum += i * a[i];    
        return sum;
    }

    // Solution 2: 
    // O(n)
    https://leetcode.com/problems/rotate-function/discuss/87842/Java-Solution-O(n)-with-non-mathametical-explaination
    public int maxRotateFunction(int[] A) {
        if(A.length == 0){
            return 0;
        }
        
        int sum =0, iteration = 0, len = A.length;
        
        for(int i=0; i<len; i++){
            sum += A[i];
            iteration += (A[i] * i);
        }
        
        int max = iteration;
        for(int j=1; j<len; j++){
            // for next iteration lets remove one entry value of each entry and the prev 0 * k
            iteration = iteration - sum + A[j-1]*len;
            max = Math.max(max, iteration);
        }
        
        return max;
    }
}