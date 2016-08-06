import java.util.*;

public class P021_Ball_Maze {
	public int minDistanceExitMaze(Maze maze, int x, int y) {
		char[] dirs = {'U', 'D', 'L', 'R'};
		
		Position start = new Position(x, y, 0);
		Queue<Position> heap = new PriorityQueue<>();
		heap.offer(start);
		Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
		map.put(x, new HashMap<>()); //store Distance
		map.get(x).put(y, 0);
		
		while (!heap.isEmpty()) { //dijkstra
			Position position = heap.poll();
			if (position.dist > map.get(position.x).get(position.y)) continue;
			if (maze.isExit(position.x, position.y)) return position.dist;
			for (char dir : dirs) {
				int step = maze.move(position.x, position.y, dir);
				if (step == 0) continue;
				int i = position.x, j = position.y;
				switch(dir) {
					case 'U':
						i -= step;
						break;
					case 'D':
						i += step;
						break;
					case 'L':
						j -= step;
						break;
					case 'R':
						j += step;
						break;
					default:
				}
				if (map.containsKey(i) && map.get(i).containsKey(j) && map.get(i).get(j) <= position.dist + step)
					continue;
				if (!map.containsKey(i)) map.put(i, new HashMap<>());
				map.get(i).put(j, position.dist + step);
				heap.offer(new Position(i, j, position.dist + step));
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		P021_Ball_Maze solution = new P021_Ball_Maze();
		int[][] grid = {
				{1, 1, 1, 1, 1},
				{-1, 1, 0, 0, 0},
				{0, 1, 0, 1, 0},
				{0, 0, 0, 1, 0},
				{0, 1, 0, 1, -1}};
		Maze maze = new Maze(grid);
		System.out.println(solution.minDistanceExitMaze(maze, 4, 0)); //3
		System.out.println(solution.minDistanceExitMaze(maze, 3, 0)); //2
		System.out.println(solution.minDistanceExitMaze(maze, 4, 2)); //8
	}
}

class Position implements Comparable<Position>{
	int x, y;
	int dist;
	
	public Position(int x, int y, int dist) {
		this.x = x;
		this.y = y;
		this.dist = dist;
	}
	
	@Override
	public int compareTo(Position that) {
		return this.dist - that.dist;
	}
}


// Below implement helper class to generate the maze movement
class Maze {
	int[][] grid; // 1 wall, 0 space, -1 exit
	public Maze(int[][] grid) {
		this.grid = grid;
	}
	
	public int move(int x, int y, char dir) {
		int step = 0;
		switch(dir) {
			case 'U':
				while (x - 1 >= 0 && grid[x - 1][y] != 1) {
					step++;
					x--;
				}
				break;
			case 'D':
				while (x + 1 < grid.length && grid[x + 1][y] != 1) {
					step++;
					x++;
				}
				break;
			case 'L':
				while (y - 1 >= 0 && grid[x][y - 1] != 1) {
					step++;
					y--;
				}
				break;
			case 'R':
				while (y + 1 < grid[0].length && grid[x][y + 1] != 1) {
					step++;
					y++;
				}
				break;
			default:
		}
		return step;
	}
	
	public boolean isExit(int x, int y) {
		return grid[x][y] == -1;
	}
}
