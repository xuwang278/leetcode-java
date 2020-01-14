class Solution {
    // 从(0,0)开始,沿着右下左上四个方向依次拐弯
    // 如果下一步在界内, 走这步; 如果越界, 则拐到下一个方向并按此方向走一步(该步一定在界内)
    // 当遍历完R*C个位置后, 结束.

    // 此题: 一直走, 直到越界再拐弯
    // 885. Spiral Matrix III: 转右或转左时, 步子加1
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return list;

        int R = matrix.length, C = matrix[0].length;
        boolean[][] visited = new boolean[R][C];
        int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // r d l u
        int r = 0, c = 0;
        int d = 0;
        while (list.size() < R * C) {
            list.add(matrix[r][c]);
            visited[r][c] = true;
            int dr = r + dirs[d][0];
            int dc = c + dirs[d][1];
            if (dr < 0 || dr >= R || dc < 0 || dc >= C || visited[dr][dc]) {
                d = (d + 1) % 4;
                r += dirs[d][0]; // 新方向走一步
                c += dirs[d][1];
            } else {
                r = dr; // 原方向走一步
                c = dc;
            }
        }
        return list;

    }

    
	// T: O(n * m)
	// S: O(n * m)
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return res;

        // 初始化: 最左, 最右, 最上, 最下
        int left = 0, right = matrix[0].length - 1;
        int top = 0, bottom = matrix.length - 1;

        // 只要还能螺旋转圈
        while (top < bottom && left < right) {
            // 扫描: 从左到右, 从上到下, 从右到左, 从下到上
            // 注意: 右端点开口
        	for (int i = left; i < right; i++) res.add(matrix[top][i]);
        	for (int i = top; i < bottom; i++) res.add(matrix[i][right]);
        	for (int i = right; i > left; i--) res.add(matrix[bottom][i]);
        	for (int i = bottom; i > top; i--) res.add(matrix[i][left]);
        	left++;
        	right--;
        	top++;
        	bottom--;
        }

        // 剩下一个夹心
        // 注意: 右端点闭口
        if (left == right) {
        	for (int i = top; i <= bottom; i++) res.add(matrix[i][left]);
        } else if (top == bottom) {
        	for (int i = left; i <= right; i++) res.add(matrix[top][i]);
        }

    	return res;
    }

}