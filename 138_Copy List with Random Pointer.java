class Solution {
    // T: O(n)
    // S: O(n)
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node cur = head; // iterate original list
        Node dummy = new Node(-1);
        Node tail = dummy; // iterate copy list
        while (cur != null) {
            tail.next = new Node(cur.val);
            map.put(cur, tail.next); // 注意新的节点是tail.next 不是tail
            cur = cur.next;
            tail = tail.next;
        }
        
        // handle random pointers
        cur = head;
        while (cur != null) {
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    // Sol 2:
    // T: O(n)
    // S: O(n)
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        
        for (Node x = head; x != null; x = x.next) {
            if (!map.containsKey(x)) map.put(x, new Node(x.val, null, null));
            if (x.next != null) {
                if (!map.containsKey(x.next)) {
                    map.put(x.next, new Node(x.next.val, null, null));
                }
                map.get(x).next = map.get(x.next);
            }
            
            if (x.random != null) {
                if (!map.containsKey(x.random)) {
                    map.put(x.random, new Node(x.random.val, null, null));
                }
                map.get(x).random = map.get(x.random);
            }
        }
        
        return map.get(head);
    }
    
}