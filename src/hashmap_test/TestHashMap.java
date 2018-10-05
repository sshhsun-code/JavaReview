package hashmap_test;

import java.util.HashMap;
import java.util.Map.Entry;

public class TestHashMap {
	
	public static void main(String[] args) {
		testHashMap();
	}
	
	private static void testHashMap() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("����", 1);
		map.put("��ѧ", 2);
		map.put("Ӣ��", 3);
		map.put("��ʷ", 4);
		map.put("����", 5);
		map.put("����", 6);
		map.put("����", 7);
		map.put("��ѧ", 8);
		map.size();
		for(Entry<String, Integer> entry : map.entrySet()) {
		    System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

}
