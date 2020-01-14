class Solution {
    public String multiply(String num1, String num2) {
        if (num1.length() == 0 || num2.length() == 0) return "0";
        int len1 = num1.length();
        int len2 = num2.length();
        int[] res = new int[len1 + len2];
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int lo = i + j + 1;
                int hi = i + j;
                int value = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + res[lo];
                res[lo] = value % 10;
                res[hi] += value / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int n : res) {
            // get rid of 0 ahead while keeping 0 in the middle
            // e.g. res = {0, 0, 1, 0, 0} -> sb = 100
            if (sb.length() == 0 && n == 0) continue;
            sb.append(n);
        }
            
        return sb.length() == 0 ? "0" : sb.toString();
    }
}