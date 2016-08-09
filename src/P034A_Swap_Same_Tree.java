
public class P034A_Swap_Same_Tree {
	public boolean isSwapSame(TreeNode x, TreeNode y) {
		if (x == null && y == null) return true;
		if (x == null || y == null) return false;
		if (x.val != y.val) return false;
		if (isSwapSame(x.left, y.left) && isSwapSame(x.right, y.right)) return true;
		if (isSwapSame(x.left, y.right) && isSwapSame(x.right, y.left)) return true;
		return false;
	}
	
	public static void main(String[] args) {
		P034A_Swap_Same_Tree sol = new P034A_Swap_Same_Tree();
		TreeNode x = new TreeNode(0);
		TreeNode y = new TreeNode(0);
		x.left = new TreeNode(1);
		y.right = new TreeNode(1);
		x.left.right = new TreeNode(2);
		y.right.left = new TreeNode(2);
		System.out.println(sol.isSwapSame(x, y)); //true
		x.right = new TreeNode(1);
		System.out.println(sol.isSwapSame(x, y)); //false
	}
}
