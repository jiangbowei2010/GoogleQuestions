import java.util.*;
/*
 * position[0], num, position[1] count of greater numbers on left
 */
public class P026B_Reconstruct_Array {
	public List<Integer> arrange(List<int[]> positions) {
		Collections.sort(positions, new Comparator<int[]>() {
			@Override
			public int compare(int[] a, int[] b) {
				return b[0] - a[0];
			}
		});
		TreeSizeNode root = null;
		for (int[] position : positions) {
			root = add(root, position[0], position[1]);
		}
		List<Integer> res = new ArrayList<>();
		Deque<TreeSizeNode> stack = new LinkedList<>();
		TreeSizeNode x = root;
		while (x != null || !stack.isEmpty()) {
			if (x != null) {
				stack.push(x);
				x = x.left;
			} else {
				x = stack.pop();
				res.add(x.val);
				x = x.right;
			}
		}
		return res;
	}
	
	private TreeSizeNode add(TreeSizeNode x, int val, int rank) {
		if (x == null) {
			if (rank == 0) return new TreeSizeNode(val, 1);
			return null;
		} 
		int num = x.size - size(x.right);
		if (rank >= num) x.right = add(x.right, val, rank - num);
		else x.left = add(x.left, val, rank);
		x.size++;
		return x;
	}
	
	private int size(TreeSizeNode x) {
		if (x == null) return 0;
		return x.size;
	}
	
	public static void main(String[] args) {
		P026B_Reconstruct_Array solution = new P026B_Reconstruct_Array();
		List<int[]> positions = new ArrayList<>(); 
		// 4 2 1 3 5
		positions.add(new int[]{1, 2});
		positions.add(new int[]{2, 1});
		positions.add(new int[]{3, 1});
		positions.add(new int[]{4, 0});
		positions.add(new int[]{5, 0});
		System.out.println(solution.arrange(positions));
		
	}
}

class TreeSizeNode {
	int val;
	int size;
	TreeSizeNode left, right;
	public TreeSizeNode(int val, int size) {
		this.val = val;
		this.size = size;
	}
}
