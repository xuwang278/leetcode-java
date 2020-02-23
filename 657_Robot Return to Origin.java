class Solution {
    // Wrong: 没有覆盖全部可能性？
    public boolean judgeCircle(String moves) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < moves.length(); i++) {
            char c = moves.charAt(i);
            if (c == 'U') {
                if (set.contains('D')) set.remove('D');
                else set.add('U');
            } else if (c == 'D') {
                if (set.contains('U')) set.remove('U');
                else set.add('D');
            } else if (c == 'L') {
                if (set.contains('R')) set.remove('R');
                else set.add('L');
            } else if (c == 'R') {
                if (set.contains('L')) set.remove('L');
                else set.add('R');
            } 
        }
        return set.isEmpty();
    }

    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;
        for (char ch : moves.toCharArray()) {
            if (ch == 'U') y++;
            else if (ch == 'D') y--;
            else if (ch == 'R') x++;
            else if (ch == 'L') x--;
        }
        return x == 0 && y == 0;
    }
    
    
}