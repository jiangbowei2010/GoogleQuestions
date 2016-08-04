import java.util.*;

public class P017_Same_Tree {
	public List<String> getSameTree(TreeNode root) {
		Map<String, List<TreeNode>> map = new HashMap<>(); // pre-order String to TreeNode
		dfs(root, map);
		List<String> res = new ArrayList<>();
		for (String s : map.keySet()) {
			if (map.get(s).size() >= 2) res.add(s);
		}
		return res;
	}
	
	private String dfs(TreeNode x, Map<String, List<TreeNode>> map) {
		if (x == null) return "#";
		String left = dfs(x.left, map);
		String right = dfs(x.right, map);
		String res = String.valueOf(x.val) + "," + left + "," + right;
		if (!map.containsKey(res)) map.put(res,  new ArrayList<>());
		map.get(res).add(x);
		return res;
	}
	
	public static void main(String[] args) {
		P017_Same_Tree solution = new P017_Same_Tree();
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		root.right.left = new TreeNode(2);
		root.right.right = new TreeNode(4);
		root.right.left.left = new TreeNode(4);
		System.out.println(solution.getSameTree(root));
	}
}
