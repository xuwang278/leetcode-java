class Solution {
    public int minStickers(String[] stickers, String target) {
        int m = stickers.length;
        int[][] mp = new int[m][26];
        // 统计每个sticker字符个数
        for (int i = 0; i < m; i++) {
            for (char c : stickers[i].toCharArray()) {
                mp[i][c - 'a']++;
            }
        }

        // dp[s] is the minimum stickers required for string s (-1 if impossible). Note s is sorted.
        // clearly, dp[""] = 0, and the problem asks for dp[target].
        
        // dp[s] = min(1+dp[reduced_s]) for all stickers, 
        // here reduced_s is a new string after certain sticker applied
        Map<String, Integer> dp = new HashMap<>();
        dp.put("", 0);
        return helper(dp, mp, target);
    }

    private int helper(Map<String, Integer> dp, int[][] mp, String target) {
        if (dp.containsKey(target)) return dp.get(target);
        int ans = Integer.MAX_VALUE, n = mp.length;
        
        // 统计当前target字符数量
        int[] tar = new int[26];
        for (char c : target.toCharArray()) {
            tar[c-'a']++;
        }

        // try every sticker
        for (int i = 0; i < n; i++) {
            // optimization
            if (mp[i][target.charAt(0) - 'a'] == 0) continue;

            StringBuilder sb = new StringBuilder();
            // apply a sticker on every character a-z
            for (int j = 0; j < 26; j++) {
                if (tar[j] > 0 ) 
                    // 
                    for (int k = 0; k < Math.max(0, tar[j] - mp[i][j]); k++) {
                        sb.append((char)('a' + j));
                    }
            }
            String s = sb.toString();
            int tmp = helper(dp, mp, s);
            if (tmp != -1) ans = Math.min(ans, 1 + tmp);
        }

        dp.put(target, ans == Integer.MAX_VALUE? -1 : ans);
        return dp.get(target);
    }

}

class Solution {
    public int minStickers(String[] stickers, String target) {
        int N = target.length();
        int[] dp = new int[1 << N];
        for (int i = 1; i < 1 << N; i++) dp[i] = -1;

        for (int state = 0; state < 1 << N; state++) {
            if (dp[state] == -1) continue;
            for (String sticker: stickers) {
                int now = state;
                for (char letter: sticker.toCharArray()) {
                    for (int i = 0; i < N; i++) {
                        if (((now >> i) & 1) == 1) continue;
                        if (target.charAt(i) == letter) {
                            now |= 1 << i;
                            break;
                        }
                    }
                }
                if (dp[now] == -1 || dp[now] > dp[state] + 1) {
                    dp[now] = dp[state] + 1;
                }
            }
        }
        return dp[(1 << N) - 1];
    }

}