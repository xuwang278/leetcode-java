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

    private char[] temp = new char[4];
    private int i = 0;
    private int cnt = 0; 
        
    public int read(char[] buf, int n) {
        int total = 0;
        boolean end = false;
        while (total < n && !end) {
            if (i == 0) cnt = read4(temp);
            if (cnt < 4) end = true;
            for (; total < n && i < cnt; i++) {
                buf[total++] = temp[i];
            }
            if (i == cnt) i = 0;
        }
        return total;
    }


}