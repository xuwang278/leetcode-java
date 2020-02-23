class Solution {
	// 1...9 10...99 100...999
	//  (9)   (180)   (2700)
    public int findNthDigit(int n) {
        int len = 1; 
        long count = 9;
        int start = 1;

        while (n > len * count) {
        	n -= len * count;
        	len++;
        	count *= 10;
        	start *= 10;
        }

        // the answer shows on one of the digit of target
        int target =  start + (n - 1) / len; // each num counts for len digits
        int reminder = (n - 1) % len; // tells which digit it is
        return Integer.toString(target).charAt(reminder) - '0';
    }

}