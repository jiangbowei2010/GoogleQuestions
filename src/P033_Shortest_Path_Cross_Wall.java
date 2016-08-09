import java.util.*;

public class P033_Shortest_Path_Cross_Wall {
	public int numberOfSteps(int[][] board, int x1, int y1, int x2, int y2, int maxWall) {
		if (board.length == 0 || board[0].length == 0) return 0;
		int m = board.length, n = board[0].length;
		int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
		Map<Integer, Map<Integer, Set<Integer>>> map = new HashMap<>();
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[]{x1, y1, 0});
		int step = 0;
		while (!q.isEmpty()) {
			Queue<int[]> nextQ = new LinkedList<>();
			while (!q.isEmpty()) {
				int[] position = q.poll();
				for (int[] dir : dirs) {
					int x = position[0] + dir[0], y = position[1] + dir[1];
					if (x < 0 || x >= m || y < 0 || y >= n || board[x][y] == 2) continue;
					int wall = position[2] + board[x][y];
					if (wall > maxWall || visit(map, x, y, wall)) continue;
					if (x == x2 && y == y2) return step + 1;
					add(map, x, y, wall);
					nextQ.offer(new int[]{x, y, wall});
				}
			}
			q = nextQ;
			step++;
		}
		return -1;
	}
	
	private boolean visit(Map<Integer, Map<Integer, Set<Integer>>> map, int x, int y, int wall) {
		if (map.containsKey(x) && map.get(x).containsKey(y) && map.get(x).get(y).contains(wall))
			return true;
		return false;
	}
	
	private void add(Map<Integer, Map<Integer, Set<Integer>>> map, int x, int y, int wall) {
		if (!map.containsKey(x)) map.put(x,  new HashMap<>());
		if (!map.get(x).containsKey(y)) map.get(x).put(y,  new HashSet<>());
		map.get(x).get(y).add(wall);
	}
	
	public static void main(String[] args) {
		int[][] board = {
				{0, 1, 1, 2},
				{0, 1, 0, 0},
				{0, 1, 1, 0},
				{0, 0, 0, 0}};
		P033_Shortest_Path_Cross_Wall solution = new P033_Shortest_Path_Cross_Wall();
		System.out.println(solution.numberOfSteps(board, 0, 0, 1, 2, 0)); //9
		System.out.println(solution.numberOfSteps(board, 0, 0, 1, 2, 1)); //3
	}
}
