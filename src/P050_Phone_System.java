// need to finish the random number generation

public class P050_Phone_System {
	private TrieNode root = new TrieNode();
	
	private class TrieNode {
		private int size = 0;
		private TrieNode[] next = new TrieNode[10];
	}
	
	private boolean isValidNumber(String number) {
		if (number.length() != 10) return false;
		for (int i = 0; i < number.length(); i++) {
			char c = number.charAt(i);
			if (c < '0' || c > '9') return false;
		}
		return true;
	}
	
	public boolean isNumberRegistered(String number) {
		if (!isValidNumber(number)) return false;
		TrieNode x = root;
		for (int i = 0; i < number.length(); i++) {
			int index = number.charAt(i) - '0';
			if (x.next[index] == null) return false;
			x = x.next[index];
		}
		return x != null;
	}
	
	public void registerNumber(String number) {
		if (!isValidNumber(number)) return;
		if (isNumberRegistered(number)) return;
		TrieNode x = root;
		for (int i = 0; i < number.length(); i++) {
			x.size++;
			int index = number.charAt(i) - '0';
			if (x.next[index] == null) x.next[index] = new TrieNode();
			x = x.next[index];
		}
		x.size++;
	}
	
	public void unRegisterNumber(String number) {
		if (!isNumberRegistered(number)) return;
		TrieNode x = root;
		for (int i = 0; i < number.length(); i++) {
			x.size--;
			int index = number.charAt(i) - '0';
			if (x.next[index].size == 1) {
				x.next[index] = null;
				break;
			}
			x = x.next[index];
		}
	}
	
	public static void main(String[] args) {
		P050_Phone_System sol = new P050_Phone_System();
		System.out.println(sol.isNumberRegistered("9494131234")); //false
		sol.registerNumber("9494131234");
		sol.registerNumber("9494131235");
		sol.registerNumber("9494131245");
		System.out.println(sol.isNumberRegistered("9494131234")); //true
		System.out.println(sol.isNumberRegistered("9494131237")); //false
		sol.unRegisterNumber("9494131234");
		System.out.println(sol.isNumberRegistered("9494131234")); //false
		System.out.println(sol.isNumberRegistered("9494131235")); //true
		sol.unRegisterNumber("9494131235");
		System.out.println(sol.isNumberRegistered("9494131245")); //true
	}
	
}
