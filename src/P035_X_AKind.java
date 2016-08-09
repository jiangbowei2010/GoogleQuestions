
public class P035_X_AKind {
	
	private final int m = 13;
	
	public boolean canBeDivided(int[] nums) {
		int[] count = new int[m + 1];
		for (int num : nums) count[num]++;
		for (int i = 1; i <= m; i++)
			if (count[i] == 1) return false;
		return true;
	}
	
	public static void main(String[] args) {
		P035_X_AKind sol = new P035_X_AKind();
		int[] nums = {3, 5, 3, 5, 3};
		System.out.println(sol.canBeDivided(nums)); // true
		nums = new int[]{3, 3, 5, 3, 3};
		System.out.println(sol.canBeDivided(nums)); //false
	}
}
