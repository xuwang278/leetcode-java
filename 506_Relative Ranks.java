class Solution {
    public String[] findRelativeRanks(int[] nums) {
        // max heap by value
        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return b[0] - a[0];
            }
        });
        
        for (int i = 0; i < nums.length; i++) {
            pq.offer(new int[] {nums[i], i}); // int[] {value, index}
        }
        
        String[] ans = new String[nums.length];
        int rank = 0;
        while (!pq.isEmpty()) {
            rank++;
            int[] top = pq.poll();
            int index = top[1];
            if (rank == 1) ans[index] = "Gold Medal";
            else if (rank == 2) ans[index] = "Silver Medal";
            else if (rank == 3) ans[index] = "Bronze Medal";
            else ans[index] = String.valueOf(rank);
        }
        
        return ans;
    }

    public String[] findRelativeRanks(int[] nums) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++)
            list.add(new int[] {nums[i], i});

        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return b[0] - a[0];
            }
        });

        String[] ans = new String[nums.length];
        int rank = 0;
        for (int[] l : list) {
            rank++;
            int index = l[1];
            if (rank == 1) ans[index] = "Gold Medal";
            else if (rank == 2) ans[index] = "Silver Medal";
            else if (rank == 3) ans[index] = "Bronze Medal";
            else ans[index] = String.valueOf(rank);
        }

        return ans;
    }
}