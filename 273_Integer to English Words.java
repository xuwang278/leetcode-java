class Solution {
    private String[] TEENS = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", 
                              "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", 
                              "Seventeen", "Eighteen", "Nineteen"};
    private String[] TYS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private String[] SEP = {"", "Thousand", "Million", "Billion"};
    
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        int index = 0; // SEP[0] == "", 因为最后三位没有thousand, million, billion后缀
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            if (num % 1000 != 0) { // 是否存在最后三位
                StringBuilder temp = new StringBuilder();
                build(temp, num % 1000);
                sb.insert(0, temp.append(SEP[index]).append(" "));
            }
            index++; // 往前移动三位, 后缀依次变为thousand, million, billion
            num /= 1000;
        }
        
        return sb.toString().trim();
    }
    
    // 处理三位数
    private void build(StringBuilder sb, int num) {
        // 处理结束
        if (num == 0) return;
        
        // 1 - 19直接添加后返回
        if (num < 20) {
            sb.append(TEENS[num]).append(" ");
            return;
        }
        
        // 添加...ty, 然后再处理最后一位 
        // 85: Eighty + case 2
        else if (num < 100) {
            sb.append(TYS[num / 10]).append(" ");
            build(sb, num % 10);
        }
        
        // 添加...Hundred, 然后处理最后两位
        // 885: Eight Hundred + case 3
        else {
            sb.append(TEENS[num / 100]).append(" Hundred ");
            build(sb, num % 100);
        }
    }
}