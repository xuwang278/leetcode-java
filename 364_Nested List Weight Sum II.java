class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
	   Queue<NestedInteger> q = new LinkedList<>();
        for (NestedInteger n : nestedList)
        	q.offer(n);

	   int prevSum = 0, total = 0;
	   while (!q.isEmpty()) {
	       int size = q.size();
	       for (int s = 0; s < size; s++) {
	           NestedInteger top = q.poll();
	           if (top.isInteger()) {
	               prevSum += top.getInteger();
	           } else {
	               for (NestedInteger n : top.getList())
        				q.offer(n); 
	           }
	       }
	       total += prevSum;
	   }
	   return total;
	}
}