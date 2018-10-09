package generics_test;

import java.util.LinkedList;

public class LinkedListTest {

	public LinkedListTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args)  {
		LinkedList<String> proverbs = new LinkedList<>();
		  LinkedList<Object> numbers = new LinkedList<>();

		   System.out.println("numbers class name: " + numbers.getClass().getName());  // Output: java.util.LinkedList
		   System.out.println("proverbs class name: " + proverbs.getClass().getName()); // Output: java.util.LinkedList
		   System.out.println("Compare Class objects: " + numbers.getClass().equals(proverbs.getClass()));     // Output:true


		   // 由于 LinkedList<String> 与 LinkedList<Object> 在编译期根本就是不同类型，所以下面代码编译不能通过：
//		   proverbs = (LinkedList<String>)numbers;  // 类似于：把 Integer 类型实例强制转型为 String实例 赋给 String引用 


		   // 每个类都是 Object 的子类
		   Object obj = (Object)numbers;
		   System.out.println("obj class name " + obj.getClass().getName());  // Output: java.util.LinkedList

		   //  会有转型安全的异常
		   proverbs = (LinkedList<String>)obj;
		   System.out.println("obj in proverbs class name " + obj.getClass().getName()); // Output:java.util.LinkedList

	}

}
