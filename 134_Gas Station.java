class Solution {
    // T: O(n^2)
    // S: O(1)
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // starting gas station
        for (int i = 0; i < gas.length; i++) {
            int tank = 0;
            int cur = i;
            do {
                tank += gas[cur] - cost[cur];
                if (tank < 0) break;
                else {
                    cur = (cur + 1) % gas.length;
                }
            } while(cur != i);

            if (tank >= 0) return i;
        
        }
        return -1;
    }

    // T: O(n)
    // S: O(1)
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0;
        int remain = 0;
        int debt = 0;

        for (int i = 0; i < gas.length; i++) {
            remain += gas[i] - cost[i];
            if (remain < 0) {
                debt += remain;
                start = i + 1;
                remain = 0;
            }
        }
        return remain + debt >= 0 ? start : -1;
    }
    

}