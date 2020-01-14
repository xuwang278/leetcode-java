public class Solution { 
    // DFS
    // T: O(V + E)
    // S: O(V + E)
    public Node cloneGraph(Node node) {
        Map<Node, Node> map = new HashMap<>(); // orig -> copy
        return dfs(node, map);
    }
    
    private Node dfs(Node node, Map<Node, Node> map) {
        if (map.containsKey(node)) return map.get(node); // if already copied, just return its copy
        Node newNode = new Node(node.val, new ArrayList<>());

        // map负责记录node对应的copy, 不记录	neighbors关系	
        map.put(node, newNode); // 这句话必须放在for loop之前, 因为for loop里有dfs(), 如果遇到之前节点, 直接通过map返回copy值

        for (Node next : node.neighbors) {
            Node copy = dfs(next, map);
            newNode.neighbors.add(copy);
        }
        return newNode;
    }

    // BFS
    // T: O(V + E)
    // S: O(V + E)
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        Queue<Node> q = new LinkedList<>();
        Map<Node, Node> map = new HashMap<>();
        map.put(node, new Node(node.val, new ArrayList<>())); // make a copy and offer to q
        q.offer(node);
        while (!q.isEmpty()) {
            Node top = q.poll();
            for (Node next : top.neighbors) {
                if (!map.containsKey(next)) { // unvisited: make a copy and offer to q
                    map.put(next, new Node(next.val, new ArrayList<>()));
                    q.offer(next);
                }
                map.get(top).m.add(map.get(next)); // put the copy to neighbors list
            }
        }
        return map.get(node); // return the copy
    }

}

