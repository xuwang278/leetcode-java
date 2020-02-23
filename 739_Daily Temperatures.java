class Solution {
	这道题应该使用递减栈Descending Stack来做，栈里只有递减元素
	遍历数组，如果栈不空，且当前数字大于栈顶元素，那么如果直接入栈的话就不是递减栈了，所以我们取出栈顶元素
	那么由于当前数字大于栈顶元素的数字，而且一定是第一个大于栈顶元素的数
	那么我们直接求出下标差就是二者的距离了

    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[T.length];
        for (int i = 0; i < T.length; i++) {
        	while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
        		int idx = stack.pop();
        		ans[idx] = i - idx;
        	}
        	stack.push(i);
        }
        return ans;
    }

    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            // if (stack.isEmpty() || T[i] < T[stack.peek()]) {
            //     stack.push(i);
            // } else {
                while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                    int cooler = stack.pop();
                    ans[cooler] = i - cooler;
                }
                stack.push(i);
            // }
        }
        return ans;
    }

}