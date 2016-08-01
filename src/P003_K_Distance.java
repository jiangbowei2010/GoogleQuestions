import java.util.*;

public class P003_K_Distance {
	public String getKDistanceString(String s, int k) {
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (map.containsKey(c)) map.put(c,  map.get(c) + 1);
			else map.put(c,  1);
		}
		Queue<Node> heap = new PriorityQueue<>();
		for (char c : map.keySet()) 
			heap.offer(new Node(c, map.get(c)));
		StringBuilder sb = new StringBuilder();
		Queue<Node> q = new LinkedList<>();
		boolean lessThanK = false;
		while (!heap.isEmpty()) {
			if (heap.size() < k) {
				if (lessThanK) return "";
				else lessThanK = true;
			}
			for (int i = 0; i < k && !heap.isEmpty(); i++) {
				Node x = heap.poll();
				sb.append(x.c);
				if (--x.count > 0) q.offer(x);
			}
			while (!q.isEmpty()) heap.offer(q.poll());
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		P003_K_Distance solution = new P003_K_Distance();
		System.out.println(solution.getKDistanceString("bbaaa", 2)); //ababa
		System.out.println(solution.getKDistanceString("bbaaa", 3)); //""
		System.out.println(solution.getKDistanceString("abcdefaa", 3)); //"abcadeaf"
	}
	
}

class Node implements Comparable<Node>{
	public char c;
	public int count;
	public Node(char c, int count) {
		this.c = c;
		this.count = count;
	}
	
	@Override
	public int compareTo(Node that) {
		if (this.count == that.count) return this.c - that.c;
		return that.count - this.count;
	}
	
}
