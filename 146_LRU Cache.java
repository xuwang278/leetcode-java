class LRUCache {
    // 创建一个双向链表, 从头部添加;
    // map: key -> node, O(1)时间找到对应node;
    // 查找时, 删除再重新插入头部;
    // 插入时, 如果已存在, 更新val; 
    //       如果不存在: (1)<cap, 直接放在头部; (2)== cap, 删除最后一个, 再放入头部
	private static class Node {
		private int key;
		private int val;
		private Node pre;
		private Node next;

		public Node(int key, int val) {
			this.key = key;
			this.val = val;
		}
	}
	
	private Map<Integer, Node> map;
	private int capacity;
	private int count;
	private Node head; // head.next: most recently used node
	private Node tail; // tail.pre: least recently used node

	// delete node from list
	private void deleteNode(Node node) {
		node.pre.next = node.next;
		node.next.pre = node.pre;
        // node.next = null;
        // node.pre = null;
	}

	// add node to head of list
	private void addToHead(Node node) {
		node.next = head.next;
		node.next.pre = node;
		node.pre = head;
		head.next = node;
	}

    public LRUCache(int capacity) {
    	map = new HashMap<>();
        this.capacity = capacity;
        count = 0;
        head = new Node(0, 0); // dummy
        tail = new Node(0, 0); // dummy
        head.next = tail; // head.pre = null
        tail.pre = head; // tail.next = null, [head...nodes...tail]
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key); // O(1)
        int val = node.val;
        deleteNode(node); // O(1)
        addToHead(node); // O(1)
        return val;
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
        	Node node = map.get(key);
        	node.val = value; // replace val
        	deleteNode(node); // delete from current position
        	addToHead(node); // move to head - most recently used one
        } else {
        	Node node = new Node(key, value); // create node
        	map.put(key, node);
        	if (count < capacity) {
        		count++;
        		addToHead(node); 
        	} else { 
        		map.remove(tail.pre.key); 
        		deleteNode(tail.pre); // least recently used node
        		addToHead(node); 
        	}
        }
    }
}