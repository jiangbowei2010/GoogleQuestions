
public class P022_RGB_Change_Color {
	
	private final int[] colors = {0x00, 0x33, 0x66, 0x99, 0xcc, 0xff};
	
	public int getColor(int num) {
		int R = (num >> 16) & 0xff;
		int G = (num >> 8) & 0xff;
		int B = num & 0xff;
		return (close(R) << 16) | (close(G) << 8) | close(B);
	}
	
	private int close(int color) {
		if (color == 0xff) return 0xff;
		int index = color / 0x33;
		return color - colors[index] <= colors[index + 1] - color? colors[index] : colors[index + 1];
	}
	
	public static void main(String[] args) {
		P022_RGB_Change_Color solution = new P022_RGB_Change_Color();
		System.out.println(Integer.toHexString(solution.getColor(0x2f3d13)));
	}
}
