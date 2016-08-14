import java.util.*;

public class P048_Android_Unlock {
	int[][] skip = new int[10][10];
	
	public List<List<Integer>> getUnlockPattern(int m, int n) {
		skip[1][3] = skip[3][1] = 2;
		skip[1][7] = skip[7][1] = 4;
		skip[3][9] = skip[9][3] = 6;
		skip[7][9] = skip[9][7] = 8;
		skip[1][9] = skip[9][1] = skip[3][7] = skip[7][3] = 5;
		skip[2][8] = skip[8][2] = skip[4][6] = skip[6][4] = 5;
		List<Integer> path = new ArrayList<>();
		List<List<Integer>> res = new ArrayList<>();
		boolean[] visited = new boolean[10];
		for (int i = 1; i <= 9; i++) dfs(i, 0, m, n, visited, path, res);
		return res;
	}
	
	private void dfs(int num, int d, int m, int n, boolean[] visited, List<Integer> path, List<List<Integer>> res) {
		visited[num] = true;
		path.add(num);
		if (d + 1 >= m && d + 1 <= n) res.add(new ArrayList<>(path));
		if (d < n) {
			for (int val = 1; val <= 9; val++) {
				if (visited[val]) continue;
				if (skip[num][val] > 0 && !visited[skip[num][val]]) continue;
				dfs(val, d + 1, m, n, visited, path, res);
			}
		}
		path.remove(path.size() - 1);
		visited[num] = false;
	}
	
	public static void main(String[] args) {
		P048_Android_Unlock sol = new P048_Android_Unlock();
		System.out.println(sol.getUnlockPattern(1, 2));
	}
}
