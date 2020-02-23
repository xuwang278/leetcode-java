class Solution {
    // Solution 2: DFS, Combination
    // https://www.youtube.com/watch?v=qpgqhp_9d1s&t=47s
    // T: O(?)
    // S: O(n)
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum % k != 0) return false; // no way to split
        
        int target = sum / k; 
        
        // (1) find k combinations of n in nums that sum up to target
        // (2) boolean[] visited is utilized to avoid mutiple usage of item 
        // as searching needs to begin from 0 index for k times
        return dfs(nums, 0, 0, k, new boolean[nums.length], target);
    }
    
    private boolean dfs(int[] nums, int start, int sum, int k, boolean[] visited, int target) {
        if (k == 1) return true; // k == 0 also works
        if (sum > target) return false;
        if (sum == target) return dfs(nums, 0, 0, k - 1, visited, target);
        
        for (int i = start; i < nums.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            if (dfs(nums, i + 1, sum + nums[i], k, visited, target)) return true;
            visited[i] = false;
        }
        return false;
    }

    // Solution 1: explicitly create k buckets
    // T: O(?)
    // S: O(n)
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum % k != 0) return false;
        return dfs(0, new int[k], nums, sum / k); 
    }
    
    // if nums[idx] can be placed in a bucket while leading to final success,
    // which means all items can be placed in k buckets
    private boolean dfs(int idx, int[] buckets, int[] nums, int target) {
        if (idx == nums.length) return true; 
        
        int selected = nums[idx];
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] + selected > target) continue; // overflow, skip to next bucket
            buckets[i] += selected; // put into a bucket
            if (dfs(idx + 1, buckets, nums, target)) return true; // if this choice work in the end?
            buckets[i] -= selected; // if it doesn't work, unload and try other bucket
        }

        return false; // fail to place nums[idx] in any bucket given current choice path
    }

    // Solution 1 pro with speed up
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int n : nums) sum += n;
        if (sum % k != 0) return false;

        Arrays.sort(nums);
        int subSum = sum / k;
        int beginIndex = nums.length - 1;
        if (nums[beginIndex] > subSum) return false;
        // speed-up 1
        while (beginIndex >= 0 && nums[beginIndex] == subSum) {
            beginIndex--;
            k--;
        }

        return partition(new int[k], nums, beginIndex, subSum);
    }
    
    private boolean partition(int[] subsets, int[] nums, int index, int target) {
        if (index < 0) return true; 

        int selected = nums[index];
        for (int i = 0; i < subsets.length; i++) {
            if (subsets[i] + selected > target) continue;
            subsets[i] += selected;
            if (partition(subsets, nums, index - 1, target)) return true;
            subsets[i] -= selected; // backtrack
            if (subsets[i] == 0) break; // speed-up 2: in the first run of search, we will make only 1 recursive call, instead of k. 
        }

        return false;
    }

    

}