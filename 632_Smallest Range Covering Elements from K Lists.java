class Solution {
    // T: O(nmlogm) m is the number of lists, n is the average length of a list
    // S: O(m)
    // https://www.youtube.com/watch?v=csJXQZFYklE
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node a, Node b) {
                return a.val - b.val;
            }
        });

        int max = Integer.MIN_VALUE; // 从三个list中分别取出的3个元素的最大值
        // 每个list首元素入队
        for (int i = 0; i < nums.size(); i++) {
            Node e = new Node(i, 0, nums.get(i).get(0));
            pq.offer(e);
            max = Math.max(max, nums.get(i).get(0));
        }

        int range = Integer.MAX_VALUE; // max - min
        int start = -1, end = -1;
        boolean found = false; // 当其中一list中的元素都查看完了, break while loop
        while (!pq.isEmpty() && !found) {
            Node top = pq.poll(); // 取出下一个最小的元素
            int min = top.val;
            if (max - min < range) {
                range = max - min;
                start = min;
                end = max;
            }
            if (top.idx + 1 >= nums.get(top.row).size()) found  = true;
            else {
                top.idx++;
                top.val = nums.get(top.row).get(top.idx);
                pq.offer(top);
                if (top.val > max) max = top.val;
            }
        }

        return new int[] {start, end};
    }

    private static class Node {
        private int row;
        private int idx;
        private int val;

        public Node(int row, int idx, int val) {
            this.row = row;
            this.idx = idx;
            this.val = val;
        }
    }

}