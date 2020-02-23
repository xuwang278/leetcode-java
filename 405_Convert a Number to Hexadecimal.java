class Solution {
    char[] map = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    
    public String toHex(int num) {
        if(num == 0) return "0";
        String result = "";
        while(num != 0){
            result = map[(num & 15)] + result; 
            num = (num >>> 4);
        }
        return result;
    }
}

class Solution {
    // 比如50，转为十六进制数，我们先对50除以16，商3余2，那么转为十六进制数就是32。
    // 本题利用2进制转16进制方法
    public String toHex(int num) {
        if (num == 0) return "0";
        StringBuilder sb = new StringBuilder();
        String str = "0123456789abcdef";
        while(num != 0){
            sb.insert(0, str.charAt(num & 0b1111)); // oxf, 15
            num = (num >>> 4);
        }
        return sb.toString();
    }
}

// e.g. 
// 26 -> 0001 1010
// 15 -> 0000 1111
    
//  0001 1010
// &0000 1111
// =0000 1010 = 10 (a)
    
    
//  0000 0001
// &0000 1111
// =0000 0001 = 1 
    
// result = 1a