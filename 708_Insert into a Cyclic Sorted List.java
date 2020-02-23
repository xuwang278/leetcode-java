/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val,Node _next) {
        val = _val;
        next = _next;
    }
};
*/
class Solution {
    public Node insert(Node head, int insertVal) {
        // case 1: if it's empty, create a node that points to itself
        if (head == null) {
            Node newNode = new Node();
            newNode.val = insertVal;
            newNode.next = newNode;
            return newNode;
        }
        
        // find max and min
        Node max = head;
        while (max.next != head && max.next.val >= max.val)
            max = max.next;
        Node min = max.next;
        
        // case 2: insertVal < min || insertVal > max,
        // insert newNode after max
        if (insertVal <= min.val || insertVal >= max.val) {
            Node newNode = new Node(insertVal, min);
            max.next = newNode;
        }
        
        // case 3: min < insertVAl < max
        // 插入到
        else {
            // find its floor
            Node floor = min;
            while (floor.next.val < insertVal)
                floor = floor.next;
            
            // now, floor.next > insertVal
            Node newNode = new Node(insertVal, floor.next);
            floor.next = newNode;
           
        }
        
        return head;
 
    }
}



