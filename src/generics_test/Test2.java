package generics_test;

public class Test2 {
	
	public static void main(String[] args) {
		
		 /**��ָ�����͵�ʱ��*/  
        Integer i = Test2.add(1, 2);   //��������������Integer������TΪInteger����  
        Number f = Test2.add(1.2, 1);  //����������һ����Integer��һ����Double������ȡͬһ�������С����ΪNumber  
        Object o = Test2.add(1, "asd");  //����������һ����Integer��һ����String������ȡͬһ�������С����ΪObject  

        System.out.println(i.getClass().getName());   //����� java.lang.Integer
        System.out.println(f.getClass().getName());   //����� java.lang.Double
        System.out.println(o.getClass().getName());   //����� java.lang.String

        /**ָ�����͵�ʱ��*/  
        int a = Test2.<Integer>add(1, 2);  //ָ����Integer������ֻ��ΪInteger���ͻ���������  
//        int b = Test2.<Integer>add(1, 2.2);  //�������ָ����Integer������ΪDouble
        Number c = Test2.<Number>add(1, 2.2);  //ָ��ΪNumber�����Կ���ΪInteger��Double  
		
	}
	
	public static <T> T add(T... args) {
		return args[1];
	}

}
