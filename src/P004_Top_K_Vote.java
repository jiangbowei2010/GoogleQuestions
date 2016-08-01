import java.util.*;

public class P004_Top_K_Vote {
	List<String> list = new ArrayList<>();
	Map<String, Integer> personToCount = new HashMap<>();
	Map<String, Integer> personToIndex = new HashMap<>();
	Map<Integer, Integer> countToRank = new HashMap<>();
	
	public void vote(String person) {
		if (personToCount.containsKey(person)) {
			int count = personToCount.get(person);
			personToCount.put(person, count + 1);
			int j = personToIndex.get(person);
			int i = countToRank.get(count);
			swap(list, i, j);
			personToIndex.put(list.get(i), i);
			personToIndex.put(list.get(j), j);
			if (!countToRank.containsKey(count + 1)) countToRank.put(count + 1, i);
			if (i + 1 < list.size() && personToCount.get(list.get(i + 1)) == count)
				countToRank.put(count, i + 1);
			else countToRank.remove(count);
		} else {
			list.add(person);
			personToCount.put(person, 1);
			personToIndex.put(person, list.size() - 1);
			if (!countToRank.containsKey(1)) countToRank.put(1, list.size() - 1);
		}
	}
	
	public List<String> getTopKVote(int k) {
		List<String> res = new ArrayList<>();
		for (int i = 0; i < k && i < list.size(); i++)
			res.add(list.get(i));
		return res;
	}
	
	private void swap(List<String> list, int i, int j) {
		String temp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, temp);
	}
	
	public static void main(String[] args) {
		P004_Top_K_Vote solution = new P004_Top_K_Vote();
		for (int i = 0; i < 5; i++) solution.vote("e");
		for (int i = 0; i < 10; i++) solution.vote("a");
		for (int i = 0; i < 8; i++) solution.vote("b");
		for (int i = 0; i < 8; i++) solution.vote("c");
		for (int i = 0; i < 8; i++) solution.vote("d");
		System.out.println(solution.getTopKVote(5)); // a b c d e
		solution.vote("c");
		System.out.println(solution.getTopKVote(5)); // a c b d e
		solution.vote("c");
		solution.vote("c");
		System.out.println(solution.getTopKVote(5)); // c a b d e
		
	}
}
