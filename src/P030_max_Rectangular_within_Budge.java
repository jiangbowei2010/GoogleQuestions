/*
 * Assuming all price is positive, solve 1D is easy
 * expand to 2D, row[i] - row[j] is covert to 1D issue
 */
public class P030_max_Rectangular_within_Budge {
	public int getMaxArea(int[][] matrix, int budget) {
		if (matrix.length == 0 || matrix[0].length == 0) return 0;
		int m = matrix.length, n = matrix[0].length;
		int[][][] sum = new int[m][m][n + 1];
		int[][] index = new int[m][m];
		int res = 0;
		for (int k = 0; k < n; k++) {
			int sumj = 0;
			for (int j = 0; j < m; j++) {
				sumj += matrix[j][k];
				int sumi = 0;
				for (int i = 0; i <= j; i++) {
					if (i > 0) sumi += matrix[i - 1][k];
					int num = sumj - sumi;
					sum[i][j][k + 1] = sum[i][j][k] + num;
					while (sum[i][j][k + 1] - sum[i][j][index[i][j]] > budget) index[i][j]++;
					res = Math.max(res, (j - i + 1) * (k - index[i][j] + 1));
				}
			}
		}
		return res;
	}
	
	public static void main(String[] args) {
		P030_max_Rectangular_within_Budge solution = new P030_max_Rectangular_within_Budge();
		int[][] matrix = {
				{1, 1, 1},
				{2, 1, 10},
				{1, 1, 1}};
		System.out.println(solution.getMaxArea(matrix, 4)); //3
		System.out.println(solution.getMaxArea(matrix, 5)); //4
		System.out.println(solution.getMaxArea(matrix, 6)); //4
		System.out.println(solution.getMaxArea(matrix, 7)); //6
	}
}
