class Solution {
	// Solution 1:
	(1) Initial:
	    num:  0000 0101
	    mask: 1111 1111

	(2) Iteration:

	    Iteration I:
	    (num & mask) = 0000 0101, (num和mask中，有两位相同，倒数第一第三)
	    mask = 1111 1110 （左移一位）
	    
	    Iteration II:
	    (num & mask) = 0000 0100，(num和mask中，有一位相同，倒数第三)
	    mask = 1111 1100 （再进一位）

	    Iteration III:
	    (num & mask) = 0000 0100，(num和mask中，有一位相同， 倒数第三)
	    mask = 1111 1000 （再进一位）

	    Iteration IV:
	    （num & mask） = 0000 0000, (num和mask中，没有位相同)

	    At this moment, all leading zeros are turned to 1!

    (3) ~num ^ mask: leading zeros in num are cancled by leading 1 in mask, leaving other bits reversed

    public int findComplement(int num) {
        int mask = ~0; // 1111 ... 1111
        while ((num & mask) != 0) mask <<= 1; // put bit operation in parentheses
        return ~num ^ mask;
    }

    // Solution 2:
    public int findComplement(int num) {
        int mask = (Integer.highestOneBit(num) << 1) - 1;
        num = ~num;
        return num & mask;
    }

    num: 0000 0101
    Integer.highestOneBit(num): 0000 0100 (reserve the left most bit)
    <<1: 0000 1000
    - 1: 0000 0111 (mask)
    ~num:1111 1010
    &   :0000 0010

}