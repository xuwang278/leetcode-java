class Solution extends SolBase {
	public int rand10() {
	    int result = 40;
	    while (result >= 40) {
	    	result = 7 * (rand7() - 1) + (rand7() - 1);
	    }
	    return result % 10 + 1;
	}
}