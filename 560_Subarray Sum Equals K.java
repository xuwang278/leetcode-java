class Solution {
    // https://www.youtube.com/watch?v=aYfwus5T3Bs
    // Sol 1: naive sol
    // T: O(n^3) TLE
    // S: O(1)
    public int subarraySum(int[] nums, int k) {
        int cnt = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0; // sum of subarray [i, j]
                for (int s = i; s <= j; s++)
                    sum += nums[s];
                if (sum == k) cnt++;
            }
        }
        return cnt;
    }

    // Sol 2: prefixSum - 保存之前计算结果
    // T: O(n^2)
    // S: O(1)
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int prefixSum = 0; // sum up values in subarray[i, j]
            for (int j = i; j < nums.length; j++) {
                prefixSum += nums[j];
                if (prefixSum == k) count++;
            }
        }
        return count;
    }

    // Sol 3: prefixSum array
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        int cnt = 0;
        // sums[j] - sums[i] = sum of subarray [i, j)
        for (int j = 0; j <= n; j++) {
            for (int i = 0; i < j; i++) {
                if (sums[j] - sums[i] == k) cnt++;
            }
        }
        return cnt;
    }

    // Solution 3: count of prefixSum, see 560.pptx

    // prefixSum[i] = nums[0] + nums[1] + ... + nums[i]
    // prefixSum[i] = prefixSum[i - 1] + nums[i]
    // sum of subarray(i, j) = prefixSum[j] - prefixSum[i - 1]
    // find how many pairs of [i, j] where i < j and prefixSum[j] - prefixSum[i] = k

    // map:
    // key - prefixSum value
    // value - # of occurrence of the prefixSum value

    // Actually no need to populate prefixSum[], only need a local variable to store
    // currentPrefixSum; all the other prefixSum values are stored in map as key

    // T: O(n)
    // S: O(n)
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> map = new HashMap<>(); // prefixsum -> freq
        map.put(0, 1);
        int currentPrefixSum = 0;
        int count = 0;
        for (int n : nums) {
            currentPrefixSum += n;
            count += map.getOrDefault(currentPrefixSum - k, 0); // currentPrefixSum - otherPre = k
            map.put(currentPrefixSum, map.getOrDefault(currentPrefixSum, 0) + 1);
        }
        return count;
    }

}

