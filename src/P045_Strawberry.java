/*
 *  2 bots move total m + n position from top left to bottom right
 *  A at (i, j), B at (x, y), so i + j = x + y after same steps
 *  if (i == x, then y == j) pick same strawberry, now we can use DP
 */
public class P045_Strawberry {
	public int maxPick(int[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) return 0;
		int m = matrix.length, n = matrix[0].length;
		int[][][] dp = new int[m][n][m];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int lo = Math.max(0,  i + j - n + 1), hi = Math.min(m - 1, i + j);
				for (int x = lo; x <= hi; x++) {
					int y = i + j - x;
					int pick = matrix[i][j];
					if (i != x) pick += matrix[x][y];
					int max = 0;
					if (i > 0 && x > 0) max = Math.max(max, dp[i - 1][j][x - 1]);
					if (i > 0 && y > 0) max = Math.max(max, dp[i - 1][j][x]);
					if (j > 0 && x > 0) max = Math.max(max,  dp[i][j - 1][x - 1]);
					if (j > 0 && y > 0) max = Math.max(max, dp[i][j - 1][x]);
					dp[i][j][x] = max + pick;
				}
			}
		}
		return dp[m - 1][n - 1][m - 1];
	}
	
	public static void main(String[] args) {
		P045_Strawberry sol = new P045_Strawberry();
		int[][] matrix = {
				{0 , 0, 0, 0, 0},
				{0, 0, 100, 1, 1},
				{100, 0, 1, 0, 0},
				{0, 0, 1, 1, 1}};
		System.out.println(sol.maxPick(matrix)); //206
	}
}
