class Solution {
	// # 先对g, s两个数组进行排序
	// # 贪心算法
	// # 贪心思想1 优先满足需求因子较小的孩子。因为如果较小需求的孩子无法被满足，则之后的较大的需求更不可能能被满足了。
	// #贪心思想2 尽量用较小的糖果去优先满足孩子。
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0;
        for (int j = 0; i < g.length && j < s.length; j++)
            if (g[i] <= s[j]) i++;
        return i;
    }

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) i++;
            //if (i == g.length) break;
            j++;
        }
        return i;
    }
}