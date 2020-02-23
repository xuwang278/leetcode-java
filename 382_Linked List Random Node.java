class Solution {
	private List<Integer> nums;
	private java.util.Random rand;

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public Solution(ListNode head) {
        nums = new ArrayList<>();
        rand = new java.util.Random();

        ListNode cur = head;
        while (cur != null) {
        	nums.add(cur.val);
        	cur = cur.next;
        }
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}

