import java.util.*;

public class P029B_Finish_Task_One_People {
	public boolean canFinish(Map<Integer, List<Integer>> peopleSkill, Map<Integer, List<Integer>> taskSkill) {
		Map<Integer, List<Integer>> skillPeople = new HashMap<>();
		for (int people : peopleSkill.keySet()) {
			for (int skill : peopleSkill.get(people)) {
				if (!skillPeople.containsKey(skill)) skillPeople.put(skill, new ArrayList<>());
				skillPeople.get(skill).add(people);
			}
		}
		
		for (int task : taskSkill.keySet()) { 
			List<Integer> skills = taskSkill.get(task); // intersaction of K list
			if (skills == null || skills.size() == 0) continue;
			Set<Integer> set = new HashSet<>();
			List<Integer> list1 = skillPeople.get(skills.get(0));
			if (list1 != null)
				for (int people : list1) set.add(people);
			for (int i = 1; i < skills.size(); i++) {
				if (set.isEmpty()) return false;
				Set<Integer> nextSet = new HashSet<>();
				List<Integer> list = skillPeople.get(skills.get(i));
				if (list == null || list.size() == 0) return false;
				for (int people : list)
					if (set.contains(people)) 
						nextSet.add(people);
				set.clear();
				set = nextSet;
			}
			if (set.isEmpty()) return false;			
		}
		return true;
	}
	
	public static void main(String[] args) {
		P029B_Finish_Task_One_People solution = new P029B_Finish_Task_One_People();
		Map<Integer, List<Integer>> peopleSkill = new HashMap<>();
		peopleSkill.put(1, Arrays.asList(1, 2, 3));
		peopleSkill.put(2, Arrays.asList(3, 4, 5));
		Map<Integer, List<Integer>> taskSkill = new HashMap<>();
		taskSkill.put(1,  Arrays.asList(1, 2));
		taskSkill.put(2,  Arrays.asList(4, 5));		
		System.out.println(solution.canFinish(peopleSkill, taskSkill));
		taskSkill.put(3,  Arrays.asList(2, 4));	
		System.out.println(solution.canFinish(peopleSkill, taskSkill));
	}
}
