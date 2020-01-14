class Solution {
    1. 0 + -  
    2. 越界: (-Integetr.MIN_VALUE) = Integer.MAX_VALUE + 1
    3. 整数
    4，小数，循环
    public String fractionToDecimal(int numerator, int denominator) {
        long num = numerator, den = denominator, rem = num % den;
        if (rem == 0) return Long.toString(num / den);

        
        StringBuilder sb = new StringBuilder();
        if (num * den < 0) sb.append('-');
        num = Math.abs(num);
        den = Math.abs(den);

        sb.append(num / den).append('.');
        rem = num % den;
        Map<Long, Integer> map = new HashMap<>();
        map.put(rem, sb.length());
        while (rem != 0) {
            num = rem * 10;
            sb.append(num / den);
            rem = num % den;
            if (map.containsKey(rem)) {
                return sb.insert((int)map.get(rem), '(').append(')').toString();
            }
            map.put(rem, sb.length());
        }
        return sb.toString();
    }
}