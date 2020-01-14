/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf);
 */
public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {
        char[] temp = new char[4];
        int total = 0;
        boolean end = false;
        while (total < n && !end) {
            int cnt = read4(temp);
            if (cnt < 4) end = true; 
            // 有时候读满4个, 但是total不能超过n
            for (int i = 0; total < n && i < cnt; i++) { // 从temp转到buf
                buf[total++] = temp[i];
            }
        }
        return total;
    }

    
    public int read(char[] buf, int n) {
        char[] temp = new char[4]; // buffer for read4
        boolean end = false; // end of the file
        int total = 0; // chars read
        while (total < n && !end) { // 文件若大, 读取n个字符即可; 文件若小, 读完整个文件即可
            int cnt = read4(temp);
            if (cnt < 4) end = true; // 文件读完了
            cnt = Math.min(n - total, cnt); // 有时候读满4个, 但是不size不能超过n
            for (int i = 0; i < cnt; i++) // 从temp转到buf
                buf[total++] = temp[i];
        }
        return total;
    }

    
}