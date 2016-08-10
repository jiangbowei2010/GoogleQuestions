import java.util.*;

public class P039_Rank_User {
	
	private TreeSizeNode root = null;
	private Map<Integer, Integer> map = new HashMap<>();
	
	private class TreeSizeNode {
		int score, size;
		Set<Integer> ids = new HashSet<>();
		TreeSizeNode left, right;
		public TreeSizeNode(int id, int score) {
			this.score = score;
			ids.add(id);
			this.size = 1;
		}
	}
	
	public void add(int id, int score) {
		if (map.containsKey(id)) {
			int oldScore = map.get(id);
			remove(root, id, oldScore);
		}
		root = add(root, id, score);
		map.put(id, score);
	}
	
	private TreeSizeNode add(TreeSizeNode x, int id, int score) {
		if (x == null) return new TreeSizeNode(id, score);
		if (x.score == score) {
			x.ids.add(id);
		} 
		else if (score > x.score) 
			x.right = add(x.right, id, score);
		else x.left = add(x.left, id, score);
		x.size++;
		return x;
	}
	
	private void remove(TreeSizeNode x, int id, int score) {
		if (x == null) return;
		if (x.score == score) x.ids.remove(id);
		else if (score > x.score) remove(x.right, id, score);
		else remove(x.left, id, score);
		x.size--;
	}
	
	public int findByRank(int k) {
		if (k >= size(root)) return Integer.MIN_VALUE;
		return find(root, k);
	}
	
	private int find(TreeSizeNode x, int k) {
		if (x == null) return Integer.MIN_VALUE;
		if (k < size(x.left)) return find(x.left, k);
		if (k >= x.size - size(x.right)) return find(x.right, k - x.size + size(x.right));
		return x.score;		
	}
	
	private int size(TreeSizeNode x) {
		if (x == null) return 0;
		return x.size;
	}
	
	public static void main(String[] args) {
		P039_Rank_User sol = new P039_Rank_User();
		sol.add(1, 10);
		sol.add(2, 15);
		System.out.println(sol.findByRank(0));
		sol.add(1, 20);
		System.out.println(sol.findByRank(0));
		sol.add(3, 5);
		System.out.println(sol.findByRank(0));
	}
}

