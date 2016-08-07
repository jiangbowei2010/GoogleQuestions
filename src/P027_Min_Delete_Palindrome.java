
public class P027_Min_Delete_Palindrome {
	public String getLongestPalindrom(String s) {
		int n = s.length();
		if (n <= 1) return s;
		String[][] dp = new String[n][n];
		for (int len = 1; len <= n; len++) {
			for (int i = 0; i <= n - len; i++) {
				if (len == 1) dp[i][i + len - 1] = "" + s.charAt(i);
				else if (s.charAt(i) != s.charAt(i + len - 1)) {
					if (dp[i][i + len - 2].length() >= dp[i + 1][i + len - 1].length())
						dp[i][i + len - 1] = dp[i][i + len - 2];
					else dp[i][i + len - 1] = dp[i + 1][i + len - 1];
				} else if (len == 2) dp[i][i + len - 1] = s.substring(i, i + len);
				else dp[i][i + len - 1] = "" + s.charAt(i) + dp[i + 1][i + len - 2] + s.charAt(i);
			}
		}
		return dp[0][n - 1];
	}
	
	public static void main(String[] args) {
		P027_Min_Delete_Palindrome solution = new P027_Min_Delete_Palindrome();
		System.out.println(solution.getLongestPalindrom("abbcda"));
		System.out.println(solution.getLongestPalindrom("abcd"));
	}
}
