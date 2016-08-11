/*
 * numbers are 0-9 and continuous
 */
public class P042_Missing_Two_Numbers {
	public int[] findTwoNumbers(String s) {
		if (s.length() == 0) return new int[0];
		int len = s.length() + 2;
		int lo = s.charAt(0) - '0', hi = s.charAt(s.length() - 1) - '0';
		int repeat = len / (hi - lo + 1);
		int i = lo, j = hi;
		while (i <= j) {
			int mid = i + (j - i) / 2;
			int index = (mid - lo + 1) * repeat - 1;
			if (index < s.length() && s.charAt(index) - '0' == mid) i = mid + 1;
			else j = mid - 1;
		}
		int num1 = i;
		i = lo;
		j = hi;
		while (i <= j) {
			int mid = i + (j - i) / 2;
			int index = (mid - lo + 1) * repeat - 2;
			if (index < s.length() && s.charAt(index) - '0' == mid) i = mid + 1;
			else j = mid - 1;
		}
		int num2 = i;
		return new int[]{num1, num2};
	}
	
	public static void main(String[] args) {
		P042_Missing_Two_Numbers sol = new P042_Missing_Two_Numbers();
		int[] res = sol.findTwoNumbers("111122223334445555");
		System.out.println("" + res[0] + " " + res[1]); // 3, 4
		res = sol.findTwoNumbers("1111");
		System.out.println("" + res[0] + " " + res[1]); // 1, 1
		res = sol.findTwoNumbers("1112");
		System.out.println("" + res[0] + " " + res[1]); // 2, 2
		
	}
}
