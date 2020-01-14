class Solution {
    public boolean isPalindrome(String s) {
        int lo = 0, hi = s.length() - 1;
        while (lo < hi) {
            while (lo < hi && !Character.isLetterOrDigit(s.charAt(lo))) lo++; // if while conditon doesn't meet, lo doesn't increment
            while (lo < hi && !Character.isLetterOrDigit(s.charAt(hi))) hi--;
            char l = Character.toLowerCase(s.charAt(lo++));
            char r = Character.toLowerCase(s.charAt(hi--));
            if (l != r) return false;
        }
        return true;
    }

    public boolean isPalindrome(String s) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i))) // Character.isLetter() is wrong for case "0p"
                list.add(Character.toLowerCase(s.charAt(i)));
        }
        
        int lo = 0, hi = list.size() - 1;
        while (lo < hi) {
            System.out.println(list.get(lo++));
            System.out.println(list.get(hi--));
            if (list.get(lo++) != list.get(hi--)) return false;
        }
        return true;
    }

    // T: O(n)
    // S: O(1)
    // two pointers
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        int head = 0, tail = s.length() - 1;
        while(head <= tail) {
            char headChar = s.charAt(head);
            char tailChar = s.charAt(tail);
            if (!Character.isLetterOrDigit(headChar))
                head++;
            else if (!Character.isLetterOrDigit(tailChar))
                tail--;
            else if (Character.toLowerCase(headChar) != Character.toLowerCase(tailChar))
                return false;
            else {
                head++;
                tail--;
            }
        }
        return true;
    }

    // T: O(n)
    // S: O(1)
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;

        s = s.toLowerCase();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i)))
                stack.push(s.charAt(i));
        }

        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i))) {
                if (s.charAt(i) != stack.pop())
                    return false;
            }
            
        }
        return true;
    }
}










