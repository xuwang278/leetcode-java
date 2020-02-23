public class Solution {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) continue;
            int slow = i, fast = getNext(nums, i), val = nums[i];
            while (val * nums[fast] > 0 && val * nums[getNext(nums, fast)] > 0) {
                if (slow == fast) {
                    if (slow == getNext(nums, slow)) break;
                    return true;
                }
                slow = getNext(nums, slow);
                fast = getNext(nums, getNext(nums, fast));
            }
            slow = i;
            while (val * nums[slow] > 0) {
                int next = getNext(nums, slow);
                nums[slow] = 0;
                slow = next;
            }
        }
        return false;
    }

    private int getNext(int[] nums, int i) {
        int n = nums.length;
        return (((nums[i] + i) % n) + n) % n;
    }
    
    
}