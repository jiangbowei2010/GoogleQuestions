import java.util.*;

public class P010_Weighted_Random {
	
	public int getRandom(int[] nums) {
		int n = nums.length;
		int[] sum = new int[n + 1];
		for (int i = 0; i < n; i++) 
			sum[i + 1] = sum[i] + nums[i];
		Random random = new Random();
		int val = random.nextInt(sum[n]);
		int lo = 0, hi = n;
		while (lo <= hi) { // looking for first sum[index] > val
			int mid = lo + (hi - lo) / 2;
			if (sum[mid] > val) hi = mid - 1;
			else lo = mid + 1;
		}
		return lo - 1;
	}
	
	public static void main(String[] args) {
		int[] nums = {1, 4, 2, 6};
		P010_Weighted_Random solution = new P010_Weighted_Random();
		int[] count = new int[4];
		for (int i = 0; i < 130; i++) {
			count[solution.getRandom(nums)]++;
		}
		for (int val : count) System.out.println(val);
	}
}
