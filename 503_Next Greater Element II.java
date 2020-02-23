class Solution {
    // Solution 1: idea as LC 496 Next Greater Element I
    // T: O(n^2)
    // S: O(n)
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            int j = (i + 1) % len;
            for (; j != i; j = (j + 1) % len) {
                if (nums[j] > nums[i]) {
                    ans[i] = nums[j];
                    break;
                }
            }
            if (j == i) ans[i] = -1;
        }
        return ans;
    }

    // Solution 2: stack
    // T: O(n)
    // S: O(n)
    public int[] nextGreaterElements(int[] nums) {
        int size = nums.length;
        int[] ans = new int[size];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size * 2 - 1; i++) {
            int num = nums[i % size];
            while (!stack.isEmpty() && nums[stack.peek()] < num)
                ans[stack.pop()] = num;
            if (i < size) stack.push(i);
        }
        return ans;
    }

}