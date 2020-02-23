class Solution {
    // Sol 1: DFS + Topological sort
    // (1) build digraph: distinct char in words -> chars lower than it
    // For two strings, only the first pair of different chars tell order: first > second
    // First thing first, consider isoloated vertex, whose relative order is unknown from words;
    // then identify order relation and update g
    // (2) find Topological sort using dfs or bfs as LC Courses Schedule
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> g = new HashMap<>(); // char -> largers ones
        buildGraph(g, words);
        
        // find topological order
        Set<Character> visited = new HashSet<>();
        Set<Character> onStack = new HashSet<>();
        StringBuilder sb = new StringBuilder();
       
        // interate vertices
        for (char c : g.keySet()) {
            if (!visited.contains(c)) {
                if (dfs(g, c, onStack, visited, sb)) return "";
            }
        }
        
       return sb.reverse().toString(); // reverse post-order
    }
    
    private boolean dfs(Map<Character, Set<Character>> g, char c, Set<Character> onStack, Set<Character> visited, StringBuilder sb) {
        onStack.add(c);
        visited.add(c);
        for (char next : g.get(c)) {
            if (!visited.contains(next)) {
                if (dfs(g, next, onStack, visited, sb)) return true;
            } else if (onStack.contains(next)) return true;
        }
        sb.append(c);
        onStack.remove(c);
        return false;
    }
    
    private void buildGraph(Map<Character, Set<Character>> g, String[] words) {
        // load vertices
        for (String w : words) {
            for (char c : w.toCharArray()) {
                g.putIfAbsent(c, new HashSet<>());
            }
        }
        
        // load edges
        for (int i = 1; i < words.length; i++) {
            String first = words[i - 1];
            String second = words[i];
            // first is smaller than second
            for (int j = 0; j < first.length() && j < second.length(); j++) {
                // first distinct pair of letters tell ordering
                if (first.charAt(j) == second.charAt(j)) continue;
                char from = first.charAt(j);
                char to = second.charAt(j);
                // from -> to (from < to)
                g.get(from).add(to); // this edge may have been added; g handle duplicates
                break; // later chars's ordering is uncertain
            }
        }
        
    }

    // Sol 2: BFS + Topological Sort
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";

        Map<Character, Set<Character>> g = new HashMap<>();
        Map<Character, Integer> inDegree = new HashMap<>();
        build(g, inDegree, words);
        return bfs(g, inDegree);
    }

    private String bfs(Map<Character, Set<Character>> g, Map<Character, Integer> inDegree) {
        Queue<Character> q = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        int totalChars = g.size();
        for (char c : inDegree.keySet()) {
            if (inDegree.get(c) == 0) {
                sb.append(c);
                q.offer(c);
            }
        }

        while (!q.isEmpty()) {
            char top = q.poll();
            //if (g.get(top) == null || g.get(top).size() == 0) continue; // good practice!
            for (char next : g.get(top)) {
                inDegree.put(next, inDegree.get(next) - 1);
                if (inDegree.get(next) == 0) {
                    sb.append(next);
                    q.offer(next);
                }
            }
        }

        return sb.length() == totalChars ? sb.toString() : "";
    }

    private void build(Map<Character, Set<Character>> g, Map<Character, Integer> inDegree, String[] words) {
        // initialize HashSet for all distinct keys 
        // because order of some chars may not known from words, i.e. isolated vertices
        // and those chars need to be in g
        for (String w : words) {
            for (char c : w.toCharArray()) {
                g.putIfAbsent(c, new HashSet<>());
                inDegree.put(c, 0); 
            }
        }

        for (int i = 1; i < words.length; i++) {
            String first = words[i - 1];
            String second = words[i];
            for (int j = 0; j < first.length() && j < second.length(); j++) {
                if (first.charAt(j) != second.charAt(j)) {
                    char from = first.charAt(j);
                    char to = second.charAt(j);
                    if (!g.get(from).contains(to)) { // inDegree can't doubly updated (from, to may shown up before)
                        g.get(from).add(to);
                        inDegree.put(to, inDegree.get(to) + 1);
                    }
                    break;
                }
            }
        }

    }

}