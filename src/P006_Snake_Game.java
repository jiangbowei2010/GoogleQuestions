import java.util.*;

public class P006_Snake_Game {
	public int getSnakeLen(int m, int n, List<Character> moves, List<int[]> food) {
		if (m <= 0 || n <= 0) return 0;
		Queue<Integer> q = new LinkedList<>();
		Set<Integer> set = new HashSet<>();
		int head = 0, foodIndex = 0;
		q.offer(0);
		set.add(0);
		for (char c : moves) {
			int i = head / n, j = head % n;
			switch(c) {
				case 'U': 
					i--;
					break;
				case 'D':
					i++;
					break;
				case 'L':
					j--;
					break;
				case 'R':
					j++;
					break;
				default:
			}
			if (i < 0 || i >= m || j < 0 || j >= n) return q.size();
			if (foodIndex >= food.size() || i != food.get(foodIndex)[0] || j != food.get(foodIndex)[1]) 
				set.remove(q.poll());
			else foodIndex++;
			head = i * n + j;
			if (set.contains(head)) return q.size();
			q.offer(head);
			set.add(head);
		}
		return q.size();
	}
	
	public static void main(String[] args) {
		P006_Snake_Game solution = new P006_Snake_Game();
		List<Character> moves = Arrays.asList('R', 'D', 'D', 'U' ,'R', 'D', 'L', 'U');
		List<int[]> food = Arrays.asList(new int[]{0, 1}, new int[]{1, 1}, new int[]{1, 0});
		System.out.println(solution.getSnakeLen(2, 2, moves, food));
		
	}
}
