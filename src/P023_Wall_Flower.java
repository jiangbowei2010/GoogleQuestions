
public class P023_Wall_Flower {
	public int getMaxFlowers(char[][] grid) {
		if (grid.length == 0 || grid[0].length == 0) return 0;
		int m = grid.length, n = grid[0].length;
		int[][] nums = new int[m][n];
		for (int i = 0; i < m; i++) {
			int count = 0;
			for (int j = 0; j < n; j++) {
				nums[i][j] += count;
				if (grid[i][j] == 'w') count = 0;
				else if (grid[i][j] == 'f') count++;
			}
			count = 0;
			for (int j = n - 1; j >= 0; j--) {
				nums[i][j] += count;
				if (grid[i][j] == 'w') count = 0;
				else if (grid[i][j] == 'f') count++;	
			}
		}
		
		for (int j = 0; j < n; j++) {
			int count = 0;
			for (int i = 0; i < m; i++) {
				nums[i][j] += count;
				if (grid[i][j] == 'w') count = 0;
				else if (grid[i][j] == 'f') count++;				
			}
			count = 0;
			for (int i = m - 1; i >= 0; i--) {
				nums[i][j] += count;
				if (grid[i][j] == 'w') count = 0;
				else if (grid[i][j] == 'f') count++;				
			}
		}
		
		int res = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j] == 'w') continue;
				if (grid[i][j] == 'f') nums[i][j]++;
				res = Math.max(res,  nums[i][j]);
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		char[][] grid = {
				{'f', 'x', 'x', 'w', 'f'},
				{'f', 'f', 'x', 'x', 'x'},
				{'x', 'x', 'f', 'w', 'f'},
				{'f', 'f', 'x', 'w', 'x'}};
		P023_Wall_Flower solution = new P023_Wall_Flower();
		System.out.println(solution.getMaxFlowers(grid));
	}
}
