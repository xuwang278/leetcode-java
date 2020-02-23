class Solution {
	// Basic Calculator I II III 通解 
	// 递归的解法: 遇到数字做累加, 遇到左括号开始找完整的(), 递归求解里面结果
	// 遇到算符或者最后一位时: 局部解参与运算, 累计到全局解
    public int calculate(String s) {
        int n = s.length();
        int num = 0; // factor
        int curRes = 0; // result of a modular
        int res = 0; // overall result
        char op = '+'; 
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num = num * 10 + c - '0';
            } 
            
            if (c == '(') {
                int j = i, cnt = 0;
                while (i < n) {
                    if (s.charAt(i) == '(') cnt++;
                    if (s.charAt(i) == ')') cnt--;
                    if (cnt == 0) break;
                    i++;
                }
                num = calculate(s.substring(j + 1, i));
            }
            
            if (c == '+' || c == '-' || c == '*' || c == '/' || i == n - 1) {
                switch (op) {
                    case '+': curRes += num; break;
                    case '-': curRes -= num; break;
                    case '*': curRes *= num; break;
                    case '/': curRes /= num; break;
                }

                // we can safely update res because + - are lowest preority
                // we don't need to wait for any feedback
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
    
	// 非负数
	// 加减法
    public int calculate(String s) {
        int n = s.length();
        int sign = 1;
        int ans = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
        	if (Character.isDigit(s.charAt(i))) {
        		String str = "" + s.charAt(i);
        		while (i + 1 < n && Character.isDigit(s.charAt(i + 1))) {
        			str += s.charAt(i + 1);
        			i++;
        		}
        		ans += sign * Integer.valueOf(str);
        	} else if (s.charAt(i) == '+') {
        		sign = 1;
        	} else if (s.charAt(i) == '-') {
        		sign = -1;
        	} else if (s.charAt(i) == '(') {
        		stack.push(ans);
        		stack.push(sign);
        		ans = 0;
        		sign = 1;
        	} else if (s.charAt(i) == ')') {
        		ans = ans * stack.pop() + stack.pop();
        	}
        }
        return ans;
    }
}