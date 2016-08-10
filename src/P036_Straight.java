
public class P036_Straight {
	private final int m = 13;
	
	public boolean canBedivided(int[] nums) {
		if (nums.length % 5 != 0) return false;
		int[] count = new int[m + 1];
		for (int num : nums) count[num]++;
		for (int i = 1; i <= m - 4; i++) {
			if (count[i] == 0) continue;
			int c = count[i];
			for (int k = i; k < i + 5; k++) {
				if (count[k] < c) return false;
				count[k] -= c;
			}
		}
		for (int k = m - 3; k <= m; k++)
			if (count[k] != 0) return false;
		return true;
	}
	
	public static void main(String[] args) {
		P036_Straight sol = new P036_Straight();
		int[] nums = {2, 1, 3, 5, 4};
		System.out.println(sol.canBedivided(nums)); // true;
		nums = new int[]{2, 1, 3, 5, 4, 3, 6, 4, 5, 7};
		System.out.println(sol.canBedivided(nums)); // true;
		nums = new int[]{1, 2, 4, 5, 7};
		System.out.println(sol.canBedivided(nums)); // false;
	}
}
