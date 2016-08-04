
/*
 * unsorted arr, same as LC 169, 229, here only give the sorted solution
 */

import java.util.*;

public class P011_Popular_Number {
	public List<Integer> getPopularNum(int[] nums) {
		int n = nums.length;
		Set<Integer> set = new HashSet<>();
		for (int i = 1; i <= 3; i++) {
			int val = nums[i * n / 4];
			int lo = (i - 1) * n / 4, hi = i * n /4;
			while (lo <= hi) {
				int mid = lo + (hi - lo) / 2;
				if (nums[mid] == val) hi = mid - 1;
				else lo = mid + 1;
			}
			if (lo + n / 4 < n && nums[lo] == nums[lo + n / 4]) set.add(nums[lo]);
		}
		List<Integer> list = new ArrayList<>();
		for (int num : set) list.add(num);
		return list;
	}
	
	public static void main(String[] args) {
		P011_Popular_Number solution = new P011_Popular_Number();
		int[] nums = {1, 2, 3};
		System.out.println(solution.getPopularNum(nums)); // 1 ,2 3
		nums = new int[]{1, 1, 2, 3};
		System.out.println(solution.getPopularNum(nums)); // 1
	}
}
