class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int l = i >= 0 ? a.charAt(i--) - '0' : 0; // char -> int
            int r = j >= 0 ? b.charAt(j--) - '0' : 0;
            int sum = (l + r + carry) % 2;
            carry = (l + r + carry) / 2;
            sb.append(sum);
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString(); // reverse first then toString
    }
}