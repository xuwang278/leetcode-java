class Solution {
	//left[i]表示在区间[0, i]范围内长度为k且和最大的子数组的起始位置
	//right[i]表示在区间[i, n - 1]范围内长度为k且和最大的子数组的起始位置
	public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] sums = new int[n + 1];
        for (int i = 1; i <= n; i++) 
        	sums[i] = sums[i - 1] + nums[i - 1];
        int[] left = new int[n];
        Arrays.fill(left, 0); // 默认为区间左端点
        int[] right = new int[n];
        Arrays.fill(right, n - k); // 默认为区间右端点
        
        // sums[i] - sums[j] = sum of subarray [j, i) i-j items
        for (int i = k, total = sums[k] - sums[0]; i < n; i++) {
        	if (sums[i + 1] - sums[i + 1 - k] > total) {
        		left[i] = i + 1 - k;
        		total = sums[i + 1] - sums[i + 1 - k];
        	} else left[i] = left[i - 1];
        }

        for (int i = n - 1 - k, total = sums[n] - sums[n - k]; i >= 0; --i) {
            if (sums[i + k] - sums[i] >= total) {
                right[i] = i;
                total = sums[i + k] - sums[i];
            } else {
                right[i] = right[i + 1];
            }
        }

        int mx = Integer.MIN_VALUE;
        int[] res = null;
        for (int i = k; i <= n - 2 * k; ++i) {
            int l = left[i - 1], r = right[i + k];
            int total = (sums[i + k] - sums[i]) + (sums[l + k] - sums[l]) + (sums[r + k] - sums[r]);
            if (mx < total) {
                mx = total;
                res = new int[] {l, i, r};
            }
        }
        return res;
    }

    // Sol 1:
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] w = new int[n - k + 1]; // w[i]: sum of subarray of size k starting from i
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (i >= k) sum -= nums[i - k];
            if (i >= k - 1) w[i - k + 1] = sum;
        }

        int[] left = new int[w.length]; 
        // left[i]: 从i开始(包括i), 向左寻找最大subarray of size k的起始index
        int best = 0;
        for (int i = 0; i < w.length; i++) {
            if (w[i] > w[best]) best = i;
            left[i] = best;
        }

        int[] right = new int[w.length];
        best = w.length - 1;
        for (int i = w.length - 1; i >= 0; i--) {
            if (w[i] >= w[best]) best = i;
            right[i] = best;
        }

        int[] ans = new int[] {-1, -1, -1};
        for (int j = k; j < w.length - k; j++) {
            // 从小于等于j-k开始, 向左寻找最大subarray of size k的起始index - left[j - k]
            int i = left[j - k], m = right[j + k]; 
            if (ans[0] == -1 || w[i] + w[j] + w[m] > w[ans[0]] + w[ans[1]] + w[ans[2]]) {
                ans[0] = i;
                ans[1] = j;
                ans[2] = m;
            }
        }
        return ans;
     }


















}