class Solution {
    
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x < 10) return true;
        int rev = 0, ori = x;
        while (x != 0) {
            int lastDigit = x % 10;
            rev = rev * 10 + lastDigit;
            x /= 10;
        }
        
        return rev == ori;
    }


    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        if (x / 10 == 0) return true;

        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();

        while (x != 0) {
        	stack.push(x % 10);
        	queue.add(x % 10);
        	x /= 10;
        }

        while (!stack.isEmpty() && !queue.isEmpty()) {
        	if (stack.pop() != queue.poll())
        		return false;
        }

        return true;
    }

    
    // convert to string
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
    	String num = Integer.toString(x);
        for (int i = 0, j = num.length() - 1; i < num.length() && j >= 0; i++, j--) {
            if (num.charAt(i) != num.charAt(j))
                return false;
        }
        return true;
    }
}