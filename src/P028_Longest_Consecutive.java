import java.util.*;

public class P028_Longest_Consecutive {
	public int numOfGroups(DualListNode[] nodes) {
		int res = 0;
		Set<DualListNode> set = new HashSet<>();
		for (DualListNode node : nodes) set.add(node);
		for (DualListNode node : nodes) {
			if (!set.contains(node)) continue;
			set.remove(node);
			DualListNode x = node;
			while (x.next != null && set.contains(x.next)) {
				x = x.next;
				set.remove(x);
			}
			x = node;
			while (x.pre != null && set.contains(x.pre)) {
				x = x.pre;
				set.remove(x);
			}
			res++;
		}
		return res;
	}
	
	public static void main(String[] args) {
		P028_Longest_Consecutive solution = new P028_Longest_Consecutive();
		DualListNode[] nodes = new DualListNode[5];
		for (int i = 0; i < 5; i++) nodes[i] = new DualListNode(i);
		for (int i = 0; i < 4; i++){
			nodes[i].next = nodes[i + 1];
			nodes[i + 1].pre = nodes[i];
		}
		System.out.println(solution.numOfGroups(new DualListNode[]{nodes[0], nodes[2], nodes[4]})); //3
		System.out.println(solution.numOfGroups(new DualListNode[]{nodes[1], nodes[2], nodes[3]})); //1
		System.out.println(solution.numOfGroups(new DualListNode[]{nodes[0], nodes[3], nodes[4]})); //2
	}
}

class DualListNode {
	int val;
	DualListNode pre, next;
	public DualListNode (int val) {
		this.val = val;
	}
}