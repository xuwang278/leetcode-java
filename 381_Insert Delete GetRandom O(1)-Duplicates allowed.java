class RandomizedCollection {
    private List<Integer> list; // vals
    private Map<Integer, Set<Integer>> map; // val -> index (indices) in list
    private Random rand;
    private int n;

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<>();
        rand = new Random();
        n = 0;
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contain = map.containsKey(val); // allow duplicates
        if (!contain) map.put(val, new HashSet<>());
        map.get(val).add(n); // 插在相应set中, idx = n
        list.add(val);
        n++;
        return !contain;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        // 所有该数值的节点, 没有在list最后一位
        if (!map.get(val).contains(n - 1)) {
            int curIdx = map.get(val).iterator().next(); // val在list中的第一个index; list.get(curIdx) == val
            int lastVal = list.get(n - 1); // list尾部的数值

            // 交换list中最后一位数和要删除的数
            // 维护map list
            // O(1)
            map.get(lastVal).remove(n - 1); // 把lastVal所对应的众多index中, 移除n-1
            map.get(lastVal).add(curIdx); // 放入新的index - curIdx

            map.get(val).remove(curIdx); // 把val所对应的众多index中, 移除curIdx
            map.get(val).add(n - 1); // 放入新的index - n - 1

            list.set(curIdx, lastVal); // 使用lastVal来替换curIdx所对应的数值
        } 

        // 删除list中最后一个数 
        // 维护map list
        // O(1)
        map.get(val).remove(n - 1); // 删除 n - 1 这个index
        if (map.get(val).isEmpty()) map.remove(val);
        list.remove(n - 1); // 删除最后一个数
        n--;
        return true;
    }
    

    /** Get a random element from the collection. */
    public int getRandom() {
        int idx = rand.nextInt(n);
        return list.get(idx);
    }

}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */