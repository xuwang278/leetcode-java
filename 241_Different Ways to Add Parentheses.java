class Solution {
    // The dfs function with non-void return type is very powerful when problem is more complicated
    // than simply combination searching, etc. 
    // (1) divide input to 2 substrings until only digits in substring
    // (2) merge two lists per operator
    // (3) as same substing may process mutiple times, a map is used for more efficient searching
    public List<Integer> diffWaysToCompute(String input) {
        return dfs(input);
    }
    
    private List<Integer> dfs(String s) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) continue;
            char c = s.charAt(i);
            String l = s.substring(0, i);
            String r = s.substring(i + 1);
            List<Integer> lList = dfs(l);
            List<Integer> rList = dfs(r);
            for (int x : lList) {
                for (int y : rList) {
                    if (c == '+') list.add(x + y);
                    if (c == '-') list.add(x - y);
                    if (c == '*') list.add(x * y);
                }
            }
        }
        if (list.size() == 0) list.add(Integer.valueOf(s));
        return list;
    }

    // Recursion with Memorization
    // https://www.youtube.com/watch?v=gxYV8eZY0eQs
    public List<Integer> diffWaysToCompute(String input) {
        Map<String, List<Integer>> map = new HashMap<>(); // string -> all possible results
        return dfs(input, map);
    }
    
    private List<Integer> dfs(String s, Map<String, List<Integer>> map) {
        if (map.containsKey(s)) return map.get(s); // has been calculated
        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '+' && c != '-' && c != '*') continue;
            String l = s.substring(0, i); // divide
            String r = s.substring(i + 1);
            List<Integer> lList = dfs(l, map); // conquer
            List<Integer> rList = dfs(r, map);
            for (int x : lList) { // merge
                for (int y : rList) {
                    if (c == '+') list.add(x + y);
                    if (c == '-') list.add(x - y);
                    if (c == '*') list.add(x * y);
                }
            }
        }
        
        if (list.size() == 0) list.add(Integer.valueOf(s)); // if s doesn't contain oprands
        map.put(s, list);
        return list;
    }

}