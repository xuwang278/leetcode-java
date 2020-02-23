class Solution {
    public String convertToBase7(int n) {
        if (n < 0) return "-" + convertToBase7(-n);
        if (n < 7) return Integer.toString(n);
        return convertToBase7(n / 7) + Integer.toString(n % 7);
    }

    public String convertTo7(int num) {
        if (num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        boolean negative = false;
        if (num < 0) negative = true;
        while (num != 0) {
            sb.append(Math.abs(num % 7));
            num = num / 7;
        }
        if (negative) sb.append("-");
        return sb.reverse().toString();
    }
    
}