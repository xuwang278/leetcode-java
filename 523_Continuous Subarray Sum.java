class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return false;
        
        int n = nums.length;
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        
        // sums[j] - sums[i] = sum of subarray[i, j)
        for (int j = 0; j <= n; j++) {
            for (int i = 0; i < j; i++) {
                int sum = sums[j] - sums[i];
                if (j - i >= 2) {
                    if (k == 0) {
                        if (sum == 0) return true;
                    } else if (sum % k == 0) return true;
                }
                    
            }
        }
        return false;
    }

    
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++)
            sums[i] = sums[i - 1] + nums[i - 1];
        
        // sums[j] - sums[i] = sum of [i, j)
        for (int i = 0; i < n; i++) {
            for (int j = i + 2; j <= n; j++) { // length of [i, j) is at least 2
                int sum = sums[j] - sums[i];
                if (k != 0) {
                    if (sum % k == 0) return true;
                } else {
                    if (sum == 0) return true;
                }
            }
        }  
        
        return false;
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>(); 
        // sum % k to current index -> current index
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (k != 0) sum = sum % k;
            if (map.containsKey(sum)) { // 余数相减，抵消
                if (i - map.get(sum) > 1) return true;
            } else map.put(sum, i);
        }
        return false;
    }

    public boolean checkSubarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0)
            return false;
        
        Map<Integer, Integer> map = new HashMap<>(); // prefixSum % k -> index
        int prefixSum = 0;
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (k != 0) prefixSum = prefixSum % k;
            if (map.containsKey(prefixSum)) {
                if (i - map.get(prefixSum) > 1) return true;
            } else map.put(prefixSum, i);
        }
        return false;
    }

}

i = 0， sum = 23, 23 % 6 = 5,        5 -> 0
i = 1,  sum = 5 + 2 % 6 = 1,         1 -> 1
i = 2,  sum = 1 + 4 % 6 = 5,  

prefixSums[2] - prefixSums[0] = sum pf subarray[1...2] = 6 % 6 == 0