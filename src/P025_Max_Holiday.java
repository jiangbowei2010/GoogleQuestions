/*
 * input: holidays is n * 12 matrix, holidays[i][j] is city i at month j holidays
 * 		  airlines is n * n matrix, airlines[i][j] = true, means you can travel from city i to city j
 */

public class P025_Max_Holiday {
	public int getMaxDays(int n, int[][] holidays, boolean[][] airlines) {
		int[][] dp = new int[12][n];
		for (int j = 0; j < n; j++) 
			dp[0][j] = holidays[j][0];
		for (int i = 1; i < 12; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					if (airlines[k][j]) dp[i][j] = Math.max(dp[i][j], dp[i - 1][k]);
				}
				dp[i][j] += holidays[j][i];
			}
		}
		int res = 0;
		for (int j = 0; j < n; j++) res = Math.max(dp[11][j], res);
		return res;
	}
	
	public static void main(String[] args) {
		int[][] holidays = {
				{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 100, 0, 0, 0, 0, 0}};
		boolean[][] airlines = {
				{true, true, false},
				{true, true, true},
				{false, true, true}};
		P025_Max_Holiday solution = new P025_Max_Holiday();
		System.out.println(solution.getMaxDays(3, holidays, airlines)); //109
	}
}
