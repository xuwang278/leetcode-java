class Solution {
    // Basic Calculator I II III 通解 
    // 递归的解法: 遇到数字做累加, 遇到左括号开始找完整的(), 递归求解里面结果
    // 遇到算符或者最后一位时: 局部解参与运算, 累计到全局解
    public int calculate(String s) {
        int n = s.length(), num = 0, curRes = 0, res = 0;
        char op = '+';
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } 

            // else if (c == '(') {
            //     int j = i, cnt = 0;
            //     while (i < n) {
            //         if (s.charAt(i) == '(') cnt++;
            //         if (s.charAt(i) == ')') cnt--;
            //         if (cnt == 0) break;
            //         i++;
            //     }
            //     num = calculate(s.substring(j + 1, i));
            // }

            if (c == '+' || c == '-' || c == '*' || c == '/' || i == n - 1) {
                switch(op) {
                    case '+': curRes += num; break;
                    case '-': curRes -= num; break;
                    case '*': curRes *= num; break;
                    case '/': curRes /= num; break;
                }

                if (c == '+' || c == '-' || i == n - 1) {
                    res += curRes;
                    curRes = 0;
                }
                op = c;
                num = 0;
            }
        }
        return res;
    }

    // Assumption: given expression is always valid
    // 非负数
    // 加减乘除
    // T: O(n)
    // S: O(n)
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;

        s = s.trim().replaceAll(" ", "");
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            // if digit
            if (Character.isDigit(s.charAt(i)))
                num = num * 10 + s.charAt(i) - '0';

            // if sign or last digit
            if (!Character.isDigit(s.charAt(i)) || i == s.length() - 1) {
                if (sign == '+') stack.push(num);
                if (sign == '-') stack.push(-num);
                if (sign == '*') stack.push(stack.pop() * num);
                if (sign == '/') stack.push(stack.pop() / num);
                sign = s.charAt(i);
                num = 0;
            }
        }

        int res = 0;
        for (int n : stack) res += n;
        return res;
    }


    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            // if digit, adding to num
            if (Character.isDigit(s.charAt(i))) 
                num = num * 10 + s.charAt(i) - '0';

            // if sign, process and update stack
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == s.length() - 1) {
                if (sign == '+') stack.push(num);
                if (sign == '-') stack.push(-num);
                if (sign == '*') stack.push(stack.pop() * num);
                if (sign == '/') stack.push(stack.pop() / num);
                sign = s.charAt(i);
                num = 0;
            }
        }

        int res = 0;
        for (int n : stack) res += n;
        return res;
    
    }


}