import java.util.*;

public class P047_Water_In_Cup {
	public List<int[]> getWater(int m, int n, int k) {
		List<int[]> res = new ArrayList<>();
		if (k < 0 || k > m + n) return res;
		Queue<WaterCup> q = new LinkedList<>();
		Map<Integer, Set<Integer>> map = new HashMap<>();
		add(map, 0, 0);
		q.offer(new WaterCup(0, 0, null));
		while (!q.isEmpty()) {
			WaterCup v = q.poll();
			if (v.x + v.y == k) {
				while (v != null) {
					res.add(new int[]{v.x, v.y});
					v = v.pre;
				}
				reverse(res);
				break;
			}
			int[][] pairs = {{0, v.y}, {m, v.y}, {v.x, 0}, {v.x, n}, {Math.max(v.x + v.y - n, 0), Math.min(n, v.x + v.y)}, {Math.min(v.x + v.y, m), Math.max(0, v.x + v.y - m)}};
			for (int[] pair : pairs) {
				if (visit(map, pair[0], pair[1])) continue;
				add(map, pair[0], pair[1]);
				q.offer(new WaterCup(pair[0], pair[1], v));
			}
		}
		return res;
	}
	
	private void add(Map<Integer, Set<Integer>> map, int x, int y) {
		if (!map.containsKey(x)) map.put(x,  new HashSet<>());
		map.get(x).add(y);
	}
	
	private boolean visit(Map<Integer, Set<Integer>> map, int x, int y) {
		return map.containsKey(x) && map.get(x).contains(y);
	}
	
	private void reverse(List<int[]> list) {
		int i = 0, j = list.size() - 1;
		while (i < j) {
			int[] temp = list.get(i);
			list.set(i, list.get(j));
			list.set(j, temp);
			i++;
			j--;
		}
	}
	
	public static void main(String[] args) {
		P047_Water_In_Cup sol = new P047_Water_In_Cup();
		List<int[]> list = sol.getWater(7, 9, 13);
		for (int[] pair : list) System.out.println("" + pair[0] + " " + pair[1]);
		System.out.println("---");
		list = sol.getWater(6, 9, 11);
		for (int[] pair : list) System.out.println("" + pair[0] + " " + pair[1]);
	}
	
}

class WaterCup {
	int x, y;
	WaterCup pre;
	public WaterCup(int x, int y, WaterCup pre) {
		this.x = x;
		this.y = y;
		this.pre = pre;
	}
}
