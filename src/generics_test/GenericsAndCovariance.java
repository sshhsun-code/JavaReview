package generics_test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenericsAndCovariance {
	
	public static void main(String[] args) {
		test1();
	}
	
	private static void test1() {

		List<? extends Number> list1 = getIntegers();
		for (Number number : list1) {
			String clazz = number.getClass().getName();
			System.out.println("number " + number + "///clazz " + clazz);
		}
		
//		list1.add(new Integer(12));
	}
	
	private static void test2() {

		List<? super Number> list1 = new ArrayList<>();
		
		list1.add(new Integer(12));
	}
	
	private static void writeTo(List<? extends Number> list, Number number) {
		list.add(number);
	}
	
	private static List<Integer> getIntegers() {
		List<Integer> integers = new ArrayList<>();
		integers.add(2);
		integers.add(3);
		integers.add(4);
		integers.add(5);
		return integers;
	}
	
	/**
	 * 存储引用的实际类型并不知道，因而无法使用这个变量调用任何与类型参数有关的方法
	 * 特别地，在 Java 集合框架中，对于参数值是未知类型的容器类，只能读取其中元素，
	 * 不能向其中添加元素。因为其类型未知，所以编译器无法识别添加元素的类型和容器的
	 * 类型是否兼容，唯一的例外是 NULL(对 Null 而言，无所谓类型)。
	 * 
	 * @param list
	 */
	private static void test3(List<String> list) {

		List<?> list1 = new ArrayList<>();
//		list1.add(new String("test"));
//		list1.add(list);
	}

}
