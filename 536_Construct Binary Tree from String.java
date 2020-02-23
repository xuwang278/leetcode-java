/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

(1) 遇到数字, 构建数字, 创建节点, 并入上级左/右子节点, 压入栈中
(2) 遇到'(', root降到local最底层root
(3) 遇到')', pop

class Solution {
    public TreeNode str2tree(String s) {
        if (s == null || s.length() == 0) return null;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = null; // refer to every local root
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (c == '-' || (Character.isDigit(c))) {
                int num = c == '-' ? 0 : c - '0';
                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + s.charAt(i + 1) - '0';
                    i++;
                }
                if (c == '-') num = -num; // i points the last digit of num
                
                // create node
                TreeNode node = new TreeNode(num);
                if (root != null) {
                    if (root.left == null)
                        root.left = node;
                    else root.right = node;
                }
                // push to stack
                stack.push(node);
            } 
            
            else if (c == '(') { // root points to local tree
                root = stack.peek();
            } 
            
            else stack.pop();

            i++;
        }

        return stack.pop();
    }
}