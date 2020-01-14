class Solution {
    // https://www.youtube.com/watch?v=ZmnqCZp9bBs

    // (1) add to stack if current value is equal or bigger than top of stack
    // (2) otherwise keep removing from stack until a number which is smaller or equal than current is found
    // (3) calculate area every time you remove, i.e.
    //     if (stack is empty) area = input[top] * i;
    //     else area = input[top] * (i - stackTop - 1)

    // T: O(2n)
    // S: O(n)
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int area = 0;
        int i = 0;
        while (i < heights.length) {
            if (stack.isEmpty() || heights[stack.peek()] <= heights[i]) {
                stack.push(i++);
            } else {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    area = heights[top] * i;
                } else {
                    area = heights[top] * (i - stack.peek() - 1);
                }
                maxArea = Math.max(maxArea, area);
            }
        }

        while (!stack.isEmpty()) {
            int top = stack.pop();
            if (stack.isEmpty()) {
                area = heights[top] * i;
            } else {
                area = heights[top] * (i - stack.peek() - 1);
            }
            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }

}