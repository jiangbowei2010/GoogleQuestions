import java.util.*;

public class P015_Flow_Water {
	public boolean[][] getLocations(int[][] grid) {
		if (grid.length == 0 || grid[0].length == 0) return new boolean[0][0];
		int m = grid.length, n = grid[0].length;
		boolean[][] visited1 = new boolean[m][n];
		boolean[][] visited2 = new boolean[m][n];
		Queue<Integer> q1 = new LinkedList<>();
		Queue<Integer> q2 = new LinkedList<>();
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 || j == 0) {
					q1.offer(i * n + j);
					visited1[i][j] = true;
				}
				if (i == m - 1 || j == n - 1) {
					q2.offer(i * n + j);
					visited2[i][j] = true;
				}
			}
		}
		
		bfs(grid, q1, visited1);
		bfs(grid, q2, visited2);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				visited1[i][j] &= visited2[i][j];
			}
		}
		return visited1;
	}
	
	private void bfs(int[][] grid, Queue<Integer> q, boolean[][] visited) {
		int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
		int m = grid.length, n = grid[0].length;
		while (!q.isEmpty()) {
			int num = q.poll();
			for (int[] dir : dirs) {
				int i = num / n + dir[0], j = num % n + dir[1];
				if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || grid[i][j] < grid[num / n][num % n])
					continue;
				q.offer(i * n + j);
				visited[i][j] = true;
			}
		}
	}
	
	public static void main(String[] args) {
		int[][] grid = {
				{1, 2, 2, 3, 5},
				{3, 2, 3, 4, 4},
				{2, 4, 5, 3, 1},
				{6, 7, 1, 4, 5},
				{5, 1, 1, 2, 4}};
		P015_Flow_Water solution = new P015_Flow_Water();
		boolean[][] res = solution.getLocations(grid);
		int m = res.length, n = res[0].length;
		for (int i = 0; i < m; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < n; j++) sb.append(res[i][j]).append(' ');
			System.out.println(sb.toString());
		}
	}
}
