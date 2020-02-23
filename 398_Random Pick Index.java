class Solution {
    private Map<Integer, List<Integer>> map; // val -> list of its indices
    private Random rand;

    public Solution(int[] nums) {
        map = new HashMap<>();
        rand = new Random();
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
    }
    
    public int pick(int target) {
        List<Integer> indexes = map.get(target);
        return indexes.get(rand.nextInt(indexes.size()));
    }
    
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */

// Solution 2: Reservoir Sampling
// https://www.cnblogs.com/grandyang/p/5875509.html
// https://www.youtube.com/watch?v=a9Dfp6UT2kI
class Solution {
    private int[] nums;
    private Random rand;
    
    public Solution(int[] nums) {
        this.nums = nums;
        rand = new Random();
    }
    
    public int pick(int target) {
        int cnt = 0; // # of target
        ans = -1; // index of target
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) continue;
            int next = rand.nextInt(++cnt);
            if (next == 0) ans = i; // 1/cnt的概率next为零, 把答案替换为i
        }
        return ans;
    }
}


// 1 2 3 3 3 
// target = 3
// i = 2: res = 2, 
// i = 3: 

