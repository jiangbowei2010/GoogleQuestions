
public class P034B_Swap_BST {
	public boolean isSwapBST(TreeNode x) {
		int[] res = dfs(x);
		return res[0] == 1;
	}
	
	private int[] dfs(TreeNode x) { //res[0] = 1 true, res[1]= lo, res[2] = hi
		int[] res = new int[3];
		if (x == null) {
			res[0] = 1;
			return res;
		}
		int[] left = dfs(x.left);
		int[] right = dfs(x.right);
		if (left[0] == 0 || right[0] == 0) return res;
		//no need swap
		if ((x.left == null || left[2] < x.val) && (x.right == null || right[1] > x.val)) {
			res[0] = 1;
			res[1] = x.left == null? x.val : left[1];
			res[2] = x.right == null? x.val : right[2];
			return res;
		}
		//swap
		if ((x.left == null || left[1] > x.val) && (x.right == null || right[2] < x.val)) {
			res[0] = 1;
			res[1] = x.right == null? x.val : right[1];
			res[2] = x.left == null? x.val : left[2];
			return res;
		}
		return res;
	}
	
	public static void main(String[] args) {
		P034B_Swap_BST sol = new P034B_Swap_BST();
		TreeNode x = new TreeNode(5);
		x.left = new TreeNode (8);
		System.out.println(sol.isSwapBST(x)); //true
		x.left.right = new TreeNode(9);
		System.out.println(sol.isSwapBST(x)); //true
		x.left.left = new TreeNode(4);
		System.out.println(sol.isSwapBST(x)); //false
	}
}
