class Solution {
    // Time: O(n)
    // Space: O(1)
    public int myAtoi(String str) {
        if (str == null) return 0;
        str = str.trim();
        if (str.length() == 0) return 0;
        
        long num = 0; // the forming num may > MAX or < MIN
        int sign = 1, start = 0;
        
        if (str.charAt(0) == '+') {
            start++;
        }
        
        if (str.charAt(0) == '-') {
            sign = -1;
            start++;
        }
        
        for (int i = start; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                num = num * 10 + str.charAt(i) - '0';
                if (sign == 1 && num > Integer.MAX_VALUE) return Integer.MAX_VALUE;
                if (sign == -1 && num > Integer.MAX_VALUE) return Integer.MIN_VALUE;
            }
            else break; // 碰到char立刻返回
        }
        return (int) num * sign;
    }

}