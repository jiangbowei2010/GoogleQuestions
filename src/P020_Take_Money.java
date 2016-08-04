public class P020_Take_Money {
	public long maxAmount(int[] nums) {
		int n = nums.length;
		if (n == 0) return 0;
		long[][] dp = new long[n][n];
		long[][] sum = new long[n][n];
		for (int len = 1; len <= n; len++) {
			for (int i = 0; i <= n - len; i++) {
				if (len == 1) {
					dp[i][i] = nums[i];
					sum[i][i] = nums[i];
				}
				else {
					sum[i][i + len - 1] = nums[i] + sum[i + 1][i + len - 1];
					dp[i][i + len - 1] = sum[i][i + len - 1] - Math.min(dp[i + 1][i + len - 1], dp[i][i + len - 2]);
				}
			}
		}
		return dp[0][n - 1];
	}
	
	public static void main(String[] args) {
		P020_Take_Money solution = new P020_Take_Money();
		int[] nums = {1};
		System.out.println(solution.maxAmount(nums)); //1
		nums = new int[]{1, 5};
		System.out.println(solution.maxAmount(nums)); //5
		nums = new int[]{1, 100, 2};
		System.out.println(solution.maxAmount(nums)); //3
	}
}
