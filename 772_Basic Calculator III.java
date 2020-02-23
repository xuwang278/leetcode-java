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
                // j points to (, i points to its matching )
        		num = calculate(s.substring(j + 1, i));
        	}

        	if (c == '+' || c == '-' || c == '*' || c == '/' || i == n - 1) {
                if (op == '+') curRes += num;
                if (op == '-') curRes -= num;
                if (op == '*') curRes *= num;
                if (op == '/') curRes /= num;

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
}