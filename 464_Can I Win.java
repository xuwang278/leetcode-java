class Solution {
  private byte[] m_;
  public boolean canIWin(int M, int T) {
    int sum = M * (M + 1) / 2;
    if (sum < T) return false;
    if (T <= 0) return true;
    m_ = new byte[1 << M];
    return canIWin(M, T, 0);
  }
  
  private boolean canIWin(int M, int T, int state) {
    if (T <= 0) return false;
    if (m_[state] != 0) return m_[state] == 1;
    
    for (int i = 0; i < M; ++i) {
      if ((state & (1 << i)) > 0) continue;
      if (!canIWin(M, T - (i + 1), state | (1 << i))) {
        m_[state] = 1;
        return true;
      }
    }
    m_[state] = -1;
    return false;
  }    
}
