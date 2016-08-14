import java.util.Arrays;

public class P049_Max_Multiple {
	public int maxValue(int[] nums, int k) {
		if (k <= 0 || k > nums.length) return Integer.MIN_VALUE;
		Arrays.sort(nums);
		if (nums[nums.length - 1] < 0 && k % 2 == 1) return get(nums, k);
		int res = 1, i = 0, j = nums.length - 1;
		for (int t = 0; t < k; t++) {
			if (Math.abs(nums[i]) > Math.abs(nums[j])) res *= nums[i++];
			else res *= nums[j--];
		}
		if (res >= 0) return res;
		int aux1 = res / nums[i - 1] * nums[j];
		int aux2 = j == nums.length - 1? Integer.MIN_VALUE : res / nums[j + 1] * nums[i];
		return Math.max(res, Math.max(aux1, aux2));
	}
	
	private int get(int[] nums, int k) {
		int res = 1, index = nums.length - 1;
		for (int i = 0; i < k; i++) res *= nums[index--];
		return res;
	}
	
	public static void main(String[] args) {
		P049_Max_Multiple sol = new P049_Max_Multiple();
		int[] nums = {-8, -7, -6, -5, -2, -1};
		System.out.println(sol.maxValue(nums, 2)); // 56
		System.out.println(sol.maxValue(nums, 3)); //-10
		nums = new int[]{-8, -7, 0, 0, 1, 2, 5, 7};
		System.out.println(sol.maxValue(nums, 2)); 
	}
}
