import java.util.*;

public class P009_ConvexHall {
	public List<int[]> getConvexHall(List<int[]> points) {
		List<int[]> res = new ArrayList<>();
		if (points.size() < 3) return res;
		int index = 0;
		for (int i = 1; i < points.size(); i++) {
			if (points.get(i)[0] > points.get(index)[0]) continue;
			if (points.get(i)[0] < points.get(index)[0] || points.get(i)[1] < points.get(index)[1])
				index = i;
		}
		myArrow[] arrows = new myArrow[points.size()];
		int k = 0;
		for (int i = 0; i < points.size(); i++) {
			if (i == index) continue;
			arrows[k++] = new myArrow(points.get(i), points.get(i)[0] - points.get(index)[0], points.get(i)[1] - points.get(index)[1]);
		}
		Arrays.sort(arrows, 0, arrows.length - 1);
		arrows[arrows.length - 1] = new myArrow(points.get(index), 0, 0);
		Deque<int[]> stack = new LinkedList<>();
		stack.push(points.get(index));
		int[] prePoint = arrows[0].point;
		for (int i = 1; i < arrows.length; i++) {
			while (!stack.isEmpty() && getAngle(arrows[i].point, prePoint, stack.peek()) <= 0) 
				prePoint = stack.pop();
			stack.push(prePoint);
			prePoint = arrows[i].point;
		}
		if (stack.size() < 3) return res;
		while (!stack.isEmpty()) res.add(stack.pop());
		return res;
	}
	
	private int getAngle(int[] point1, int[] point2, int[] point3) {
		return (point2[0] - point3[0]) * (point1[1] - point2[1]) - (point1[0] - point2[0]) * (point2[1] - point3[1]);
	}
	
	public static void main(String[] args) {
		int[][] arr = {{0, 0}, {1, 1}, {-1, 1}, {1, -1}, {-1, -1}, {-1, 0}, {2, 2}};
		List<int[]> points = new ArrayList<>();
		for (int[] point : arr) points.add(point);
		P009_ConvexHall solution = new P009_ConvexHall();
		List<int[]> res = solution.getConvexHall(points);
		// (-1,1) (2,2) (1,-1) (-1,-1) 
		for (int[] point : res) 
			System.out.print("(" + point[0] + "," + point[1] + ") ");
	}
}

class myArrow implements Comparable<myArrow> {
	int[] point;
	int x, y;
	public myArrow(int[] point, int x, int y) {
		this.point = point;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int compareTo(myArrow that) {
		return this.y * that.x - that.y * this.x;
	}
}
