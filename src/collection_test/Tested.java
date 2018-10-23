package collection_test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Tested {
	
	public static void main(String[] args) {
		test2();
	}
	
	private static void test1() {
		Map<String, String> map = new HashMap<String, String>();
	    map.put("apple", "ƻ��");
	    map.put("watermelon", "����");
	    map.put("banana", "�㽶");
	    map.put("peach", "����");

	    Iterator iter = map.entrySet().iterator();
	    while (iter.hasNext()) {
	        Map.Entry entry = (Map.Entry) iter.next();
	        System.out.println(entry.getKey() + "=" + entry.getValue());
	    }
	}
	

	
	private static void test2() {
		LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>(0, 0.75f, true);
	
	    map.put(1, "ƻ��");
	    map.put(2, "����");
	    map.put(3, "�㽶");
	    map.put(4, "����");
	    
//	    map.get(2);
	    map.get(1);
	    
	    Iterator iter = map.entrySet().iterator();
	    while (iter.hasNext()) {
	        Map.Entry entry = (Map.Entry) iter.next();
	        System.out.println(entry.getKey() + "=" + entry.getValue());
	    }
	}
	
	private static void test3() {
		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
	
	    map.put("apple", "ƻ��");
	    map.put("watermelon", "����");
	    map.put("banana", "�㽶");
	    map.put("peach", "����");
	    
	    map.get("banana");
	    map.get("watermelon");
	    
	    Iterator iter = map.entrySet().iterator();
	    while (iter.hasNext()) {
	        Map.Entry entry = (Map.Entry) iter.next();
	        System.out.println(entry.getKey() + "=" + entry.getValue());
	    }
	}
}
