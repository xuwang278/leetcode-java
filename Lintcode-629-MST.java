/**
 * Definition for a Connection.
 * public class Connection {
 *   public String city1, city2;
 *   public int cost;
 *   public Connection(String city1, String city2, int cost) {
 *       this.city1 = city1;
 *       this.city2 = city2;
 *       this.cost = cost;
 *   }
 * }
 */
public class Solution {
    /**
     * @param connections given a list of connections include two cities and cost
     * @return a list of connections from results
     */
    private static class UF {
        private int[] parent;
        private int[] rank;
        private int count;
        
        public UF(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
            count = n;
        }
        
        public int find(int s) {
            if (s != parent[s])
                parent[s] = find(parent[s]);
            return parent[s];
        }
        
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            if (rank[rootP] < rank[rootQ]) parent[rootP] = rootQ;
            else if (rank[rootP] > rank[rootQ]) parent[rootQ] = rootP;
            else {
                parent[rootQ] = rootP;
                rank[rootP]++;
            }
            count--;
        }
        
        public int count() {
            return count;
        }
        
        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }
    }
    
    public List<Connection> lowestCost(List<Connection> connections) {
        Queue<Connection> pq = new PriorityQueue<>(new Comparator<Connection>() {
            @Override
            public int compare(Connection c1, Connection c2) {
                if (c1.cost != c2.cost) return c1.cost - c2.cost;
                if (c1.city1.equals(c2.city1)) return c1.city2.compareTo(c2.city2);
                return c1.city1.compareTo(c2.city1);
            }
        });
        
        int i = 0;
        Map<String, Integer> hash = new HashMap<>();
        for (Connection c : connections) {
            pq.offer(c);
            String from = c.city1;
            String to = c.city2;
            int cost = c.cost;
            if (!hash.containsKey(from)) hash.put(from, i++);
            if (!hash.containsKey(to)) hash.put(to, i++);
        }
        
        UF uf = new UF(hash.size());
        List<Connection> mst = new ArrayList<>();
        while (!pq.isEmpty() && mst.size() < hash.size() - 1) {
            Connection top = pq.poll();
            int from = hash.get(top.city1);
            int to = hash.get(top.city2);
            if (uf.connected(from, to)) continue;
            uf.union(from, to);
            mst.add(top);
        }
        
        return  mst.size() < hash.size() - 1 ? new ArrayList<>() : mst;
    }
    
}