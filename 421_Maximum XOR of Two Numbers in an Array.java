public class Solution {
    // Solution 1:
    // https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/discuss/184434/JavaTrie99
    private class TrieNode {
        int val;
        TrieNode zero, one;
        boolean isEnd;
    }

    private class TrieTree {
        TrieNode root;

        public TrieTree() {
            root = new TrieNode();
        }

        public void insert(int num) {
            TrieNode cur = root;
            int j = 1 << 30;
            for (int i = 0; i < 31; i++) {
                int b = (j & num) == 0 ? 0 : 1;
                if (b == 0 && cur.zero == null) {
                    cur.zero = new TrieNode();
                }
                if (b == 1 && cur.one == null) {
                    cur.one = new TrieNode();
                }
                cur = b == 0 ? cur.zero : cur.one;
                j >>= 1;
            }
            cur.isEnd = true;
            cur.val = num;
        }

    }

    public int findMaximumXOR(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        TrieTree tree = new TrieTree();
        for (int n : nums) {
            tree.insert(n);
        }

        // 获取真正需要开始判断的root
        TrieNode cur = tree.root;
        while (cur.one == null || cur.zero == null) {
            cur = cur.zero != null ? cur.zero : cur.one;
        }
        return maxHelper(cur.one, cur.zero);
    }

    /**
     * 分治法，原则就是尽量使两个分支的高位不同
     * one是1分支，zero是0分支，可以看做图中的左区和右区
     * 1. 当1分支的下一位只有1时，找0分支的0，若没有，就找0分支的1
     * 2. 当1分支的下一位只有0时，找0分支的1，若没有，就找0分支的0
     * 3. 当1分支的下一位0，1均有时，看0分支：如果0分支只有1，则1分支走0，反之则走1
     * 4. 当0，1两个分支均有两个下一位时，尝试【1分支走1，0分支走0】和【1分支走0，0分支走1】两条路线并取最大值
     * */
    private int maxHelper(TrieNode one, TrieNode zero) {
        if (one.isEnd && zero.isEnd) {
            return one.val ^ zero.val;
        }
        if (one.zero == null) {
            return maxHelper(one.one, zero.zero == null ? zero.one : zero.zero);
        } else if (one.one == null) {
            return maxHelper(one.zero, zero.one == null ? zero.zero : zero.one);
        } else if (zero.zero == null) {
            return maxHelper(one.zero, zero.one);
        } else if (zero.one == null) {
            return maxHelper(one.one, zero.zero);
        } else {
            return Math.max(maxHelper(one.one, zero.zero), maxHelper(one.zero, zero.one));
        }
    }




    // Solution 2:
    public int findMaximumXOR(int[] nums) {
        int max = 0, mask = 0;
        for(int i = 31; i >= 0; i--){
            mask = mask | (1 << i);
            Set<Integer> set = new HashSet<>();
            for(int num : nums){
                set.add(num & mask);
            }
            int tmp = max | (1 << i);
            for(int prefix : set){
                if(set.contains(tmp ^ prefix)) {
                    max = tmp;
                    break;
                }
            }
        }
        return max;
    }
}