class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int n1 = num1.length(), n2 = num2.length();
        int carry = 0;
        for (int i = n1 - 1, j = n2 - 1; i >= 0 || j >= 0; i--, j--) {
            int a = i >= 0 ? Character.getNumericValue(num1.charAt(i)) : 0;
            int b = j >= 0 ? Character.getNumericValue(num2.charAt(j)) : 0;
            int sum = (a + b + carry) % 10;
            carry = (a + b + carry) / 10;
            sb.insert(0, (char) (sum + '0'));
        }
        if (carry != 0) sb.insert(0, (char) (1 + '0'));
        return sb.toString();
    }

    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        for(int i = num1.length() - 1, j = num2.length() - 1; i >= 0 || j >= 0 || carry == 1; i--, j--){
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            sb.append((x + y + carry) % 10);
            carry = (x + y + carry) / 10;
        }
        return sb.reverse().toString();
    }
}