class Solution {
	Converting a and b to binary pattern: 
	(1) when two digits are both 1: result = (a & b) << 1
	(2) when two digits are both 0: result = a ^ b
	(3) when one digit is 1, the other is 0: result = a ^ b

	accumulate result until b = 0 

	sum(1, 3) -> sum(2, 2) -> sum(0, 4) -> sum(4, 0)

	// iterative
    public int getSum(int a, int b) {
        while (b != 0) {
        	int carry = a & b; // 处理两位都是1的数位
        	a = a ^ b; // 处理其他数位，即两位不都是1，结果存于a
        	b = carry << 1; // 进位结果存于b
        }
        return a;
    }

    // recursive
    public int getSum(int a, int b) {
    	return b == 0 ? a : getSum(a ^ b, (a & b) << 1);
    }



    // Substract
    public int getSubstract(int a, int b) {
    	while (b != 0) {
    		int borrow = (~a) & b;
    		a = a ^ b;
    		b = borrow << 1;
    	}
    }

    public int getSubstract(int a, int b) {
    	return b == 0 ? a : getSum(a ^ b, (~a & b) << 1);
    }

    // negate
    public int negate(int x) {
		return ~x + 1;
	}

}