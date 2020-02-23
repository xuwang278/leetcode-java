class Solution {
	// The idea is to keep string builder and appending until the length A is greater or equal to B.
    public int repeatedStringMatch(String A, String B) {
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        while (sb.length() < B.length()) {
        	sb.append(A);
        	cnt++;
        }

        if (sb.toString().contains(B)) return cnt;
        if (sb.append(A).toString().contains(B)) return ++cnt;
        return -1;
    }
}