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
	 * �洢���õ�ʵ�����Ͳ���֪��������޷�ʹ��������������κ������Ͳ����йصķ���
	 * �ر�أ��� Java ���Ͽ���У����ڲ���ֵ��δ֪���͵������ֻ࣬�ܶ�ȡ����Ԫ�أ�
	 * �������������Ԫ�ء���Ϊ������δ֪�����Ա������޷�ʶ�����Ԫ�ص����ͺ�������
	 * �����Ƿ���ݣ�Ψһ�������� NULL(�� Null ���ԣ�����ν����)��
	 * 
	 * @param list
	 */
	private static void test3(List<String> list) {

		List<?> list1 = new ArrayList<>();
//		list1.add(new String("test"));
//		list1.add(list);
	}

}
