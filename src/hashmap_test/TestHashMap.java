package hashmap_test;

import java.util.HashMap;
import java.util.Map.Entry;

public class TestHashMap {
	
	public static void main(String[] args) {
		testHashMap();
	}
	
	private static void testHashMap() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("语文", 1);
		map.put("数学", 2);
		map.put("英语", 3);
		map.put("历史", 4);
		map.put("政治", 5);
		map.put("地理", 6);
		map.put("生物", 7);
		map.put("化学", 8);
		map.size();
		for(Entry<String, Integer> entry : map.entrySet()) {
		    System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

}
