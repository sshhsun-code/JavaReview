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
	    map.put("apple", "Æ»¹û");
	    map.put("watermelon", "Î÷¹Ï");
	    map.put("banana", "Ïã½¶");
	    map.put("peach", "ÌÒ×Ó");

	    Iterator iter = map.entrySet().iterator();
	    while (iter.hasNext()) {
	        Map.Entry entry = (Map.Entry) iter.next();
	        System.out.println(entry.getKey() + "=" + entry.getValue());
	    }
	}
	

	
	private static void test2() {
		LinkedHashMap<Integer, String> map = new LinkedHashMap<Integer, String>(0, 0.75f, true);
	
	    map.put(1, "Æ»¹û");
	    map.put(2, "Î÷¹Ï");
	    map.put(3, "Ïã½¶");
	    map.put(4, "ÌÒ×Ó");
	    
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
	
	    map.put("apple", "Æ»¹û");
	    map.put("watermelon", "Î÷¹Ï");
	    map.put("banana", "Ïã½¶");
	    map.put("peach", "ÌÒ×Ó");
	    
	    map.get("banana");
	    map.get("watermelon");
	    
	    Iterator iter = map.entrySet().iterator();
	    while (iter.hasNext()) {
	        Map.Entry entry = (Map.Entry) iter.next();
	        System.out.println(entry.getKey() + "=" + entry.getValue());
	    }
	}
}
