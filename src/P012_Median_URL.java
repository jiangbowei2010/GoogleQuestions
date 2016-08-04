import java.util.*;

public class P012_Median_URL {
	private Queue<String> minHeap;
	private Queue<String> maxHeap;
	
	public P012_Median_URL() {
		minHeap = new PriorityQueue<>(10, new Comparator<String>(){
			@Override
			public int compare(String a, String b) {
				return a.length() - b.length();
			}
		});
		maxHeap = new PriorityQueue<>(10, new Comparator<String>(){
			@Override
			public int compare(String a, String b) {
				return b.length() - a.length();
			}
		});
	}
	
	public void addURL(String URL) {
		if (minHeap.isEmpty() || URL.length() >= minHeap.peek().length())
			minHeap.offer(URL);
		else maxHeap.offer(URL);
		if (maxHeap.size() > minHeap.size()) minHeap.offer(maxHeap.poll());
		else if (minHeap.size() - maxHeap.size() > 1) maxHeap.offer(minHeap.poll());
	}
	
	public String getURL() {
		if (minHeap.size() == 0) return "";
		return minHeap.peek();
	}
	
	public static void main(String[] args) {
		P012_Median_URL solution = new P012_Median_URL();
		solution.addURL("abcde");
		solution.addURL("a");
		solution.addURL("ab");
		solution.addURL("abcd");
		solution.addURL("abc");
		System.out.println(solution.getURL());
	}
}
