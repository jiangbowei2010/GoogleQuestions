
public class P037_X_Straight {
	private final int m = 13;
	
	public boolean canBeDivided(int[] nums) {
		int[] count = new int[m + 1];
		for (int num : nums) count[num]++;
		int[] head = new int[m + 1];
		head[1] = count[1];
		for (int i = 2; i <= m; i++) {
			if (count[i] >= count[i - 1]) head[i] = count[i] - count[i - 1];
			else if (head[i - 1] + head[i - 2] > count[i]) return false;
		}
		return head[m - 1] + head[m] == 0;
	}
	
	public static void main(String[] args) {
		P037_X_Straight sol = new P037_X_Straight();
		int[] nums = { 1, 2, 3, 2, 3, 4};
		System.out.println(sol.canBeDivided(nums)); // true;
		nums = new int[]{1, 2, 3, 4, 3, 4};
		System.out.println(sol.canBeDivided(nums)); // false;
	}
}
