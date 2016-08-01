import java.util.*;

public class P005_Points_Partition {
	
	private final double resolution = 1e-15;
	
	public Point[] partition(Point[] points) {
		int n = points.length;
		if (n < 2) return null;
		for (int i = 1; i < n; i++) {
			Point[] res = partition(points, i, n);
			if (res != null) return res;
		}
		return null;
	}
	
	private Point[] partition(Point[] points, int index, int n) {
		Arrow[] arrows = new Arrow[2 * n - 2];
		for (int i = 0, j = 0; i < n; i++) {
			if (i == index) continue;
			arrows[j++] = new Arrow(points[index], points[i]);
		}
		Arrays.sort(arrows, 0, n - 1);
		for (int i = n - 1; i < 2 * n - 2; i++) {
			arrows[i] = new Arrow(arrows[i - n + 1].a, arrows[i - n + 1].b);
			arrows[i].increaseTheta(2 * Math.PI);
		}
		int p0 = 0, p1 = 0, p2 = 0, p3 = 0;
		for (int i = 0; i < n - 1; i++) {
			while (p0 < 2 * n - 2 && arrows[p0].diff(arrows[i]) < resolution) p0++;
			while (p1 < 2 * n - 2 && arrows[p1].diff(arrows[i]) < Math.PI - resolution) p1++;
			while (p2 < 2 * n - 2 && arrows[p2].diff(arrows[i]) < Math.PI + resolution) p2++;
			while (p3 < 2 * n - 2 && arrows[p3].diff(arrows[i]) < 2 * Math.PI - resolution) p3++;
			if (p1 - p0 == p3 - p2) return new Point[]{points[index], arrows[i].b};
		}
		return null;
	}
	
	public static void main(String[] args) {
		P005_Points_Partition solution = new P005_Points_Partition();
		int[] x = {-1, 1, 0, -2, -1, 0, 1, 2};
		int[] y = {2, 2, 1, 0, 0, 0, 0, 0};
		Point[] points = new Point[x.length];
		for (int i = 0; i < x.length; i++) {
			points[i] = new Point(x[i], y[i]);
		}
		Point[] res = solution.partition(points);
		if (res != null) System.out.println(res[0]);
		if (res != null) System.out.println(res[1]);
	}

}

class Arrow implements Comparable<Arrow> {
	Point a, b;
	double theta;
	private final double resolution = 1e-15;
	public Arrow(Point a, Point b) {
		this.a = a;
		this.b = b;
		this.theta = Math.atan2(b.y - a.y, b.x - a.x);
	}
	
	public void increaseTheta(double angle) {
		theta += angle;
	}
	
	public double diff(Arrow that) {
		return this.theta - that.theta;
	}
	
	@Override
	public int compareTo(Arrow that) {
		if (this.theta - that.theta < -resolution) return -1;
		if (this.theta - that.theta > resolution) return 1;
		return 0;
	}
}

class Point {
	int x, y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
