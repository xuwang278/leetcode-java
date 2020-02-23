class RandomizedSet {
    private List<Integer> list; // store values
    private Map<Integer, Integer> map; // value -> its index in list
    private int n; // # of values
    private Random rand;
    
    /** Initialize your data structure here. */
    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
        n = 0;
        rand = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false; // already in list, return falsel; O(1)
        map.put(val, n); 
        list.add(val); // add to tail of list; O(1)
        n++;
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int idx = map.get(val); // index of the val to be removed in list
        
        // if val is not the last item in list
        if (idx != n - 1) {
            int lastVal = list.get(n - 1);
            list.set(idx, lastVal); // replace with last item
            map.put(lastVal, idx);
           // map.put(val, n - 1);
        } 
        
        // remove last one (at index n-1) from list 
        // while keep other remaining same place; O(1)
        list.remove(n - 1); 
        map.remove(val); // O(1)
        n--;
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int idx = rand.nextInt(n);
        return list.get(idx);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */