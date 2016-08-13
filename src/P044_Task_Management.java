/*
 * (0, 1, 2, 3, 4) -> 7
 *  5 -> 8 -> 10
 *  6 -> 9 -> 11
 *  (5, 6, 0, 1, 2) -> (3, 4, 8, 9) -> (7, 10, 11) need 3 steps -- optimized
 *  (1, 2, 3, 4, 5) -> (5, 6, 7) -> (8, 9) -> (10, 11) need 4 steps
 */
import java.util.*;

public class P044_Task_Management {
	public List<List<Integer>> optimizeTask(int n, int[][] prerequests) {
		// build the graph
		List<List<Integer>> adj = new ArrayList<>();
		for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
		int[] indeg = new int[n];
		for (int[] pre : prerequests) {
			adj.get(pre[0]).add(pre[1]);
			indeg[pre[1]]++;
		}
		
		//mark the node to end longest distance with dfs
		Map<Integer, Integer> map = new HashMap<>();
		boolean[] visited = new boolean[n];
		for (int v = 0; v < n; v++) {
			if (!visited[v]) dfs(adj, v, visited, map);
		}
		
		//greedy topo sort, run long path first
		Queue<int[]> heap = new PriorityQueue<>(10, new Comparator<int[]>(){ //[0] node, [1] distance
			@Override
			public int compare(int[] a, int[] b) {
				if (a[1] == b[1]) return a[0] - b[0];
				return b[1] - a[1];
			}
		});
		for (int i = 0; i < n; i++) {
			if (indeg[i] == 0) heap.offer(new int[]{i, map.get(i)});
		}
		List<List<Integer>> res = new ArrayList<>();
		while (!heap.isEmpty()) {
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < 5 && !heap.isEmpty(); i++) {
				list.add(heap.poll()[0]);
			}
			for (int v : list) {
				for (int w : adj.get(v)) {
					if (--indeg[w] == 0) heap.offer(new int[]{w, map.get(w)});
				}
			}
			res.add(list);
		}
		return res;
	}
	
	private void dfs(List<List<Integer>> adj, int v, boolean[] visited, Map<Integer, Integer> map) {
		visited[v] = true;
		int max = 0;
		for (int w : adj.get(v)) {
			if (!visited[w]) dfs(adj, w, visited, map);
			max = Math.max(max, map.get(w));
		}
		map.put(v, 1 + max);
	}
	
	public static void main(String[] args) {
		P044_Task_Management sol = new P044_Task_Management();
		int[][] prerequests = {{0, 7}, {1, 7}, {2, 7}, {3, 7}, {4, 7}, {5, 8}, {8, 10}, {6, 9}, {9, 11}};
		System.out.println(sol.optimizeTask(12, prerequests));
	}
}
