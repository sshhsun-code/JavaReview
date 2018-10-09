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


		   // ���� LinkedList<String> �� LinkedList<Object> �ڱ����ڸ������ǲ�ͬ���ͣ��������������벻��ͨ����
//		   proverbs = (LinkedList<String>)numbers;  // �����ڣ��� Integer ����ʵ��ǿ��ת��Ϊ Stringʵ�� ���� String���� 


		   // ÿ���඼�� Object ������
		   Object obj = (Object)numbers;
		   System.out.println("obj class name " + obj.getClass().getName());  // Output: java.util.LinkedList

		   //  ����ת�Ͱ�ȫ���쳣
		   proverbs = (LinkedList<String>)obj;
		   System.out.println("obj in proverbs class name " + obj.getClass().getName()); // Output:java.util.LinkedList

	}

}
