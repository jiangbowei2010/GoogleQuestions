
public class P046_LCM {
	public int getLCM(int n) {
		if (n <= 0) return 0;
		int res = 1;
		for (int i = 1; i <= n; i++) {
			res = calLCM(res, i);
		}
		return res;
	}
	
	private int calLCM(int a, int b) {
		int gcd = calGCD(a, b);
		return a / gcd * b;
	}
	
	private int calGCD(int a, int b) {
		while (a % b != 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}
		return b;
	}
	
	public static void main(String[] args) {
		P046_LCM sol = new P046_LCM();
		System.out.println(sol.getLCM(5)); //60
	}
}
