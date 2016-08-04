import java.util.*;

public class P013_Stock_Price {
	Record max = null, min = null, latest = null;
	Integer currTime = null;
	Map<Integer, Record> map = new HashMap<>();
	TreeSet<Record> set = new TreeSet<>();
	
	public void onUpdate(int timeStamp, int price) {
		if (currTime != null && timeStamp < currTime) return;
		latest = new Record(timeStamp, price);
		if (max == null || latest.price > max.price) max = latest;
		if (min == null || latest.price < min.price) min = latest;
		map.put(timeStamp, latest);
		set.add(latest);
	}
	
	public void onCorrect(int timeStamp, int price) {
		if (!map.containsKey(timeStamp)) return;
		Record x = map.get(timeStamp);
		set.remove(x);
		x.price = price;
		set.add(x);
		max = set.last();
		min = set.first();
	}
	
	public int getMax() {
		return max == null? -1 : max.price;
	}
	
	public int getMin() {
		return min == null? -1 : min.price;
	}
	
	public int getLatest() {
		return latest == null? -1 : latest.price;
	}
	
	public static void main(String[] args) {
		P013_Stock_Price solution = new P013_Stock_Price();
		solution.onUpdate(0, 5);
		solution.onUpdate(1, 2);
		solution.onUpdate(2, 7);
		solution.onUpdate(3, 9);
		solution.onUpdate(4, 8);
		System.out.println("max: " + solution.getMax() + ", min: " + solution.getMin() + ", latest: " + solution.getLatest());
		solution.onCorrect(4, 12);
		System.out.println("max: " + solution.getMax() + ", min: " + solution.getMin() + ", latest: " + solution.getLatest());		
	}

}

class Record implements Comparable<Record> {
	int timeStamp, price;
	public Record (int timeStamp, int price) {
		this.timeStamp = timeStamp;
		this.price = price;
	}
	
	@Override
	public int compareTo(Record that) {
		if (this.price == that.price) return this.timeStamp - that.timeStamp;
		return this.price - that.price;
	}
}
