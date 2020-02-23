class Solution {
   //  https://www.youtube.com/watch?v=yc4hCFzNNQc
    // Solution 1: Brute Force
    // try every number compare with its next k numbers
    // T: O(n * k)
    // S: O(1)
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) return false;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j - i <= k && j < nums.length; j++) {
                if (Math.abs(Long.valueOf(nums[i]) - Long.valueOf(nums[j])) <= t) {
                    return true;
                }
            }
        }
        return false;
    }

    // Solution 2: TreeSet (BST - ordered set)
    // T: O(n * logk)
    // S: O(k)
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) return false;

        TreeSet<Integer> set = new TreeSet();
        for (int i = 0; i < nums.length; i++) {
            // tree always holds k #
            if (i > k) set.remove(nums[i - k - 1]);
            // smallest # > nums[i]
            Integer ceil = set.ceiling(nums[i]);
            if (ceil != null && Long.valueOf(ceil) - Long.valueOf(nums[i]) <= t) return true;

            // largest # < nums[i]
            Integer floor = set.floor(nums[i]);
            if (floor != null && Long.valueOf(nums[i]) - Long.valueOf(floor) <= t) return true;

            set.add(nums[i]);
        }
        return false;
    }

    // Solution 3: Bucket
    // Put every # in a corresponding bucket (bucket_index = (# - min) / (t + 1))
    // Return true if two # go in the same bucket or adjacent buckets with diff less than t
    // T: O(n * logk)
    // S: O(bucket_size), worst case O((max_number-min_number) / t) -> optmized: O(k)
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) return false;
        int min = Integer.MAX_VALUE;
        for (int n : nums) min = Math.min(min, n);

        Map<Long, Integer> bucket = new HashMap<>();
        long diff = Long.valueOf(t) + 1;
        for (int i = 0; i < nums.length; i++) {

            if (i > k) {
                long remove_index = (Long.valueOf(nums[i - k - 1]) - Long.valueOf(min)) / diff; 
                bucket.remove(remove_index);
            }

            long index = (Long.valueOf(nums[i]) - Long.valueOf(min)) / diff;

            Integer left_bucket = bucket.get(index - 1);
            if (left_bucket != null && Math.abs(Long.valueOf(nums[i]) - Long.valueOf(left_bucket) < diff)) {
                return true;
            } 

            Integer right_bucket = bucket.get(index + 1);
            if (right_bucket != null && Math.abs(Long.valueOf(nums[i]) - Long.valueOf(right_bucket) < diff)) {
                return true;
            }

            Integer current_bucket = bucket.get(index);
            if (current_bucket != null) return true;

            bucket.put(index, nums[i]);
        }
        return false;
    }
}