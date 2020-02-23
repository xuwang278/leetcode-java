class Solution {
    // 思路：inorder traversal逐一访问树中每个节点，回溯时构建linked list
    // (1)从最左下角node开始，逐一构建linked list，因为每个节点的left/right只使用一次
    // 因此，该算法是in-place
    // (2)运用 pre 来记录前一个node，用来构建doubly relation; pre为static, 这样所有递归函数都
    // 只使用这唯一一个copy
    // (3) dummy node是构建linkedlist的常用手法：好处是每个节点，包括head，前面都有一个node
    // (4)最后不要忽视首尾相连
    
    private Node pre;

    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        Node dummy = new Node(0, null, null);
        pre = dummy;
        dfs(root);

        pre.right = dummy.right; // tail -> head
        dummy.right.left = pre; // head -> tail

        return dummy.right;
    }

    private void dfs(Node root) {
        if (root == null) return;
        dfs(root.left);

        pre.right = root;
        root.left = pre;
        pre = root;
        dfs(root.right);
    }

    // in-order: l -> root -> r
    private Node head; // points to 1st node
    private Node tail; // points to last node
    
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        dfs(root);
        head.left = tail;
        tail.right = head;
        return head;
    }
    
    private void dfs(Node root) {
        if (root == null) return;
        dfs(root.left);
        if (head == null) {
            head = root; // 1st non-null node, set up head
        } else {
            tail.right = root; // otherwise, set up relation between tail and root
            root.left = tail;
        }
        tail = root;
        dfs(root.right);
    }
}

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/