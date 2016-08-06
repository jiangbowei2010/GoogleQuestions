import java.util.*;

public class P024_Sliding_Window_Average {
	private double sum = 0;
	private double windowSum = 0;
	Queue<Integer> q = new LinkedList<>();
	private int size = 0;
	private final int windowSize;
	
	public P024_Sliding_Window_Average(int windowSize) {
		this.windowSize = windowSize;
	}
	public void insert(int num) {
		sum += num;
		windowSum += num;
		size++;
		q.offer(num);
		if (q.size() > windowSize) windowSum -= q.poll();
	}
	
	public double getWindowAvg() {
		if (size == 0) return Double.MAX_VALUE;
		return windowSum / q.size();
	}
	
	public double getGlobalAvg() {
		if (size == 0) return Double.MAX_VALUE;
		return sum / size;
	}
	
	public static void main(String[] args) {
		P024_Sliding_Window_Average solution = new P024_Sliding_Window_Average(2);
		solution.insert(1);
		solution.insert(2);
		solution.insert(3);
		System.out.println(solution.getWindowAvg());
		System.out.println(solution.getGlobalAvg());
		solution.insert(100);
		System.out.println(solution.getWindowAvg());
		System.out.println(solution.getGlobalAvg());
	}
}
