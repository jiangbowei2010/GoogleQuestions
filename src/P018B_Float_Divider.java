
public class P018B_Float_Divider {
	public double div(double a, double b, double resolution) {
		if (b == 0 || resolution <= 0) return Float.MAX_VALUE;
		if (a == 0) return 0;
		boolean pos = true;
		if (a < 0) {
			a = -a;
			pos = !pos;
		}
		if (b < 0) {
			b = -b;
			pos = !pos;
		}
		float res = 0, weight = 1;
		while (a / 10 >= b) {
			b *= 10;
			weight *= 10;
		}
		while (b > a) {
			b /= 10;
			weight /= 10;
		}
		while (a > resolution) {
			while (a >= b) {
				a -= b;
				res += weight;
			}
			b /= 10;
			weight /= 10;
		}
		if (!pos) res = -res;
		return res;
	}
	
	public static void main(String[] args) {
		P018B_Float_Divider solution = new P018B_Float_Divider();
		System.out.println(solution.div(1, 3, 0.0001));
		System.out.println(solution.div(0, 3, 0.01));
		System.out.println(solution.div(1, 0, 0.01));
		System.out.println(solution.div(2, 5, 0.00001));
		System.out.println(solution.div(1, -3, 0.001));
	}
}
