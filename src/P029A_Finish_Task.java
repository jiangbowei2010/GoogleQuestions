import java.util.*;

public class P029A_Finish_Task {
	public boolean canFinish(Map<Integer, List<Integer>> peopleSkill, Map<Integer, List<Integer>> taskSkill) {
		Set<Integer> skillSet = new HashSet<>();
		for (int people : peopleSkill.keySet()) 
			for (int skill : peopleSkill.get(people)) 
				skillSet.add(skill);
		for (int task : taskSkill.keySet()) 
			for (int skill : taskSkill.get(task))
				if (!skillSet.contains(skill)) return false;
		return true;
	}
	
	public static void main(String[] args) {
		P029A_Finish_Task solution = new P029A_Finish_Task();
		Map<Integer, List<Integer>> peopleSkill = new HashMap<>();
		peopleSkill.put(1, Arrays.asList(1, 2, 3));
		peopleSkill.put(2, Arrays.asList(3, 4, 5));
		Map<Integer, List<Integer>> taskSkill = new HashMap<>();
		taskSkill.put(1,  Arrays.asList(1, 2));
		taskSkill.put(2,  Arrays.asList(2, 5));
		System.out.println(solution.canFinish(peopleSkill, taskSkill)); //true
		taskSkill.put(3,  Arrays.asList(4, 5, 6));
		System.out.println(solution.canFinish(peopleSkill, taskSkill)); //false
	}
}
