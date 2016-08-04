public class P019_Number_Square {
	public int getNumberOfSquare(boolean[][] grid) {
		if (grid.length == 0 || grid[0].length == 0) return 0;
		int m = grid.length, n = grid[0].length;
		int res = 0;
		int[][] dp = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (grid[i][j]) dp[i][j] = 0;
				else if (i == 0 || j == 0) dp[i][j] = 1;
				else dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
				res += dp[i][j];
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		P019_Number_Square solution = new P019_Number_Square();
		boolean[][] grid = {
				{false, true, false},
				{false, false, false},
				{true, false, false}};
		System.out.println(solution.getNumberOfSquare(grid));
	}
}
