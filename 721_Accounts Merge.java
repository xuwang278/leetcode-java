class Solution {
    // Solution 1': Graph + DFS, see 721.pptx
    //     {John, A, B, C, D}, {John, B, E, F}, {Xu, X, Y, Z}
//     Graph: vertex: email; edge: two emails that shows up under the same user name
//     e.g.
//          F
//          |
// //     A-B-E      
//        |\
//        C D
        
//     then run dfs to find connected compontnent; sort; insert name at head; put to ans list
    
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> ans = new ArrayList<>();
        if (accounts == null || accounts.size() == 0) return ans;
        
        // graph: email - related emails (1 to many)
        Map<String, Set<String>> g = new HashMap<>();
        buildGraph(g, accounts);
        
        // map: email - name relation (1 to 1)
        Map<String, String> map = new HashMap<>();
        buildMap(map, accounts);
        
        Set<String> visited = new HashSet<>();
        for (String email : g.keySet()) { // iterate all vertices
            if (!visited.contains(email)) { // starting from a unvisited one
                List<String> list = new ArrayList<>(); // put all reachable vertices to list
                dfs(g, email, visited, list);
                Collections.sort(list);
                list.add(0, map.get(email));
                ans.add(list);
            }
        }
        
        return ans;
    }
    
    private void dfs(Map<String, Set<String>> g, String email, Set<String> visited, List<String> list) {
        visited.add(email);
        list.add(email);
        // expand
        for (String next : g.get(email)) {
            if (!visited.contains(next))
                dfs(g, next, visited, list);
        }
    }
    
    private void buildGraph(Map<String, Set<String>> g, List<List<String>> accounts) {
        for (List<String> account : accounts) {
            String baseEmail = account.get(1);
            g.putIfAbsent(baseEmail, new HashSet<>()); // create vertex
            for (int i = 2; i < account.size(); i++) { // for neighboring emails, if any
                String next = account.get(i);
                g.putIfAbsent(next, new HashSet<>());
                g.get(baseEmail).add(next); // build edges
                g.get(next).add(baseEmail);
            }
        }
    }
    
    private void buildMap(Map<String, String> map, List<List<String>> accounts) {
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                map.put(account.get(i), name);
            }
        }
    }
    

    // Solution 2: UF
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<Integer>> map = buildMap(accounts); 

        // 弄清楚user id 之间的联系：union同一个email下的所有id
        UF uf = new UF(accounts.size());
        for (List<Integer> users : map.values()) {
            int baseId = users.get(0);
            for (int i = 1; i < users.size(); i++)
                uf.union(baseId, users.get(i));
        }

        // 把每个user的emails放在其root id所对应的set中 (去重)
        Map<Integer, Set<String>> mergedAccounts = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            int root = uf.find(i);
            mergedAccounts.putIfAbsent(root, new HashSet<>());
            for (int j = 1; j < accounts.get(i).size(); j++)
                mergedAccounts.get(root).add(accounts.get(i).get(j));
        }

        // turn map to ans
        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<Integer, Set<String>> entry : mergedAccounts.entrySet()) {
            int root = entry.getKey();
            String name = accounts.get(root).get(0);
            List<String> list = new ArrayList<>();
            list.add(name);

            Set<String> emails = entry.getValue();
            List<String> listOfEmails = new ArrayList<>(emails);
            Collections.sort(listOfEmails);

            list.addAll(listOfEmails);
            ans.add(list);
        }

        return ans;
    }

    // email -> user id
    private Map<String, List<Integer>> buildMap(List<List<String>> accounts) {
        Map<String, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> a = accounts.get(i);
            for (int j = 1; j < a.size(); j++) {
                String email = a.get(j);
                map.putIfAbsent(email, new ArrayList<>());
                map.get(email).add(i);
            }
        }
        return map;
    }

    private class UF {
        private int[] parents;
        private int[] rank;
        private int count;

        public UF(int n) {
            count = n;
            parents = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                rank[i] = 0;
            }
        }

        // O(1)* amorized
        // pass compression
        public int find(int s) {
            if (s != parents[s])
                parents[s] = find(parents[s]);
            return parents[s];
        }

        // O(1)* 
        // union by rank
        public void union(int p, int q) {
            int rootP = find(p); // pass compression
            int rootQ = find(q);
            if (rootP == rootQ) return;
            if (rank[rootP] < rank[rootQ]) parents[rootP] = rootQ;
            else if (rank[rootP] > rank[rootQ]) parents[rootQ] = rootP;
            else {
                parents[rootQ] = rootP;
                rank[rootP]++;
            }
            count--;
        }

        // O(1)
        public int count() {
            return count;
        }

        // O(1)
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }
    }

	// Solution 1': Graph + DFS (Different shape of graph)
    // Map<String, Set<String>> g: 
        // emails that belongs to the same user are connected: 
        // e.g. accounts = { {Jogn, A, B, C, D}, {John, B, E, F}, {John, X, Y} }
        // graph: A-B-C-D   X-Y  (two cc)
        //          |
        //          E-F

    // Map<String, String> map:
        // email -> name (no duplicate keys)
        // e.g. A - John
        //      B - John
        //      C - John
        //      D - John
        //      E - John
        //      F - John
        //      X - John
        //      Y - John
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Set<String>> g = new HashMap<>(); // email -> the latest one connected
        Map<String, String> map = new HashMap<>(); // email -> name
        
        // build graph and map
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                map.put(email, name);
                g.putIfAbsent(email, new HashSet<>());
                if (i == 1) continue;
                String preEmail = account.get(i - 1); // edge preEmail - email
                g.get(email).add(preEmail);
                g.get(preEmail).add(email);
            }
        }
        
        List<List<String>> ans = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for (String email : map.keySet()) { // unique vertices
            if (!visited.contains(email)) {
                List<String> list = new ArrayList<>();
                dfs(g, email, list, visited); // find all emails in cc and put to list
                Collections.sort(list); // sort emails
                list.add(0, map.get(email)); // put name at head
                ans.add(list);
            }
        }
        
        return ans;
    }
    
    private void dfs(Map<String, Set<String>> g, String cur, List<String> list, Set<String> visited) {
        visited.add(cur);
        list.add(cur);
        for (String next : g.get(cur)) {
            if (visited.contains(next)) continue;
            dfs(g, next, list, visited);
        }
    }
    
    

}