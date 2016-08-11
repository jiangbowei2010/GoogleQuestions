import java.util.Arrays;

public class P041_Maxium_Guest_In_Room {
	public int maxNumOfGuest(int[] arr, int[] exit) {
		int res = 0, index = 0;
		Arrays.sort(arr);
		Arrays.sort(exit);
		for (int i = 0; i < arr.length; i++) {
			while (index < exit.length && exit[index] < arr[i]) index++;
			res = Math.max(res, i - index + 1);
		}
		return res;
	}
	
	public static void main(String[] args) {
		P041_Maxium_Guest_In_Room sol = new P041_Maxium_Guest_In_Room();
		int[] arr = {1, 2, 9, 5, 5};
		int[] exit = {4, 5, 12, 9, 12};
		System.out.println(sol.maxNumOfGuest(arr, exit));
	}
}
