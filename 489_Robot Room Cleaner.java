/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */
class Solution {
	// T:O(4^(n-m))
	// S: O(n-m)
    public void cleanRoom(Robot robot) {
        Set<String> visited = new HashSet<>();
        dfs(robot, visited, 0, 0, 0); // 起点的相对坐标为(0,0)
    }

    int[][] dirs = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 上右下左 (顺时针转)

    private void dfs(Robot robot, Set<String> visited, int x, int y, int arrow) {
    	String node = x + "-" + y; // 相对坐标
    	if (visited.contains(node)) return; // 清扫过了, 回退
    	visited.add(node); // 新位置没有访问过
    	robot.clean();

        // 向四个方向扩展
    	for (int k = 0; k < 4; k++) {
    		// 如果按目前朝向能走且已经走了这步 (一定需要回退回来)
    		if (robot.move()) {
    			int dx = x + dirs[arrow][0]; // 下一步坐标
    			int dy = y + dirs[arrow][1];
                // if (visited.contains(di + "-" + dj)) continue; // 不能这么做, 因为机器人不能瞬移
    			dfs(robot, visited, dx, dy, arrow); // 朝这个方向一直走下去

    			// 回溯到原来位置:
    			// 旋转 180 度掉头，前进一步，再转回到原来的方向
    			// move 函数只能探测前方是否能到达
    			robot.turnLeft();
    			robot.turnLeft();
    			robot.move();
    			robot.turnLeft();
    			robot.turnLeft();
    		}

    		// 该方向不能走或者走完了, 让机器人换下个方向
    		robot.turnRight(); // 顺时针转
    		arrow = (arrow + 1) % 4;
    	}
    }


}