class Solution {
    // Sol 1: using two stacks
    // https://www.cnblogs.com/grandyang/p/7617017.html
    public boolean checkValidString(String s) {
      Stack<Integer> left = new Stack<>();
      Stack<Integer> star = new Stack<>();

      // 由于是正向遍历,所以当发现一个多余)时, 一定是不合法
      // 先用(消耗所有), 没有(拿*顶数
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (c == '*') star.push(i);
        else if (c == '(') left.push(i);
        else {
          if (left.isEmpty() && star.isEmpty()) return false; // 存在一个)无法匹配
          if (!left.isEmpty()) left.pop(); // 先消耗最近的(, 与之匹配
          else star.pop(); // 注意else, 即当没有(时, 再把*当做(与之匹配
        }
      }

      // 消耗完所有)后, 只剩下( *
      while (!left.isEmpty() && !star.isEmpty()) {
        if (left.peek() > star.peek()) return false; // *后面有个(无法匹配
        left.pop(); // (*做匹配
        star.pop();
      }

      return left.isEmpty(); // 是否有未匹配(
      // star非空没关系, *当作空即可
    }

    // Sol 2: 
    // 从左到有扫描且把*当做(
    // 从右到左扫面且把*当做)

    正向遍历的时候，我们把星号都当成左括号，此时用经典的验证括号的方法，即遇左括号计数器加1，遇右括号则自减1，如果中间某个时刻计数器小于0了，直接返回false。
    如果最终计数器等于0了，我们直接返回true，因为此时我们把星号都当作了左括号，可以跟所有的右括号抵消。
    而此时就算计数器大于0了，我们暂时不能返回false，因为有可能多余的左括号是星号变的，星号也可以表示空，所以有可能不多，
    我们还需要反向遍历一下，这是我们将所有的星号当作右括号，遇右括号计数器加1，遇左括号则自减1，如果中间某个时刻计数器小于0了，直接返回false。遍历结束后直接返回true
    https://www.cnblogs.com/grandyang/p/7617017.html

    public boolean checkValidString(String s) {
      int left = 0, right = 0, n = s.length();
      for (int i = 0; i < n; i++) {
        char c = s.charAt(i);
        if (c == '(' || c == '*') left++;
        else left--;
        if (left < 0) return false;
      }

      if (left == 0) return true;

      for (int i = n - 1; i >= 0; i--) {
        char c = s.charAt(i);
        if (c == ')' || c == '*') right++;
        else right--;
        if (right < 0) return false;
      }
      return true;
    }

    // 看不懂啥意思! 跳过!
    public boolean checkValidString(String s) {
        // l - 需要被匹配的左括号个数
        // r - 需要被匹配的右括号个数
        int l = 0, r = 0;
        for (char c : s.toCharArray()) {
          l += c == '(' ? 1 : -1; // 
          r += c != ')' ? 1 : -1; 
          if (r < 0) break; // 右括号比左括号/*都多了, 肯定不合法了
          lo = Math.max(lo, 0);
        }
        return l == 0; // 没有需要被匹配的左括号 -> 合法
    }

}

