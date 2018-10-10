package generics_test;

import java.util.ArrayList;

public class Test3 {

	public static void main(String[] args) {
		//类型安全性检查是针对引用的
		ArrayList list1 = new ArrayList<>();
		list1.add("rich");
		list1.add(new Integer(11));
		list1.add(false);
		
		for (int i = 0; i < list1.size(); i++) {
			String clazz = list1.get(i).getClass().getName();
			System.out.println("i clazz = " + clazz);
		}
	}

}
