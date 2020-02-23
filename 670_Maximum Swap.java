class Solution {
    public int maximumSwap(int num) {
        int[] lastIdx = new int[10];
        char[] chs = String.valueOf(num).toCharArray();
        for (int i = 0; i < chs.length; i++) {
            lastIdx[chs[i] - '0'] = i;
        }
        
        for (int i = 0; i < chs.length; i++) {
            int digitAhead = chs[i] - '0';
            for (int d = 9; d >= 0; d--) {
                if (d > digitAhead && lastIdx[d] > i) {
                    exch(chs, i, lastIdx[d]);
                    return Integer.valueOf(String.valueOf(chs));
                }
            }
        }
        return num;
    }

    从最高位开始, 找从9开始第一个比它大的数 (若有相同, 选最后一位), 交换即可. 
    e.g. 2996 -> 9296d

    public int maximumSwap(int num) {
        char[] numChars = String.valueOf(num).toCharArray();
        int[] lastIndex = new int[10]; // 各个数字在numChars最后一次出现的index
        for (int i = 0; i < numChars.length; i++) {
        	lastIndex[numChars[i] - '0'] = i;
        }

        // 找最左一个数
        for (int i = 0; i < numChars.length; i++) {
        	// 找最大且最右一个数
        	for (int d = 9; d > i[i] - '0'; d--) {
        		if (lastIndex[d] > i) {
        			swap(numChars, i, lastIndex[d]);
        			return Integer.valueOf(String.valueOf(numChars));
        		}
        	}
        }
        return num;
    }

    private void swap(char[] numChars, int i, int j) {
    	char temp = numChars[i];
    	numChars[i] = numChars[j];
    	numChars[j] = temp;
    }

    https://www.youtube.com/watch?v=_EUlHJaOo1I
}