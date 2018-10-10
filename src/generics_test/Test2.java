package generics_test;

public class Test2 {
	
	public static void main(String[] args) {
		
		 /**不指定泛型的时候*/  
        Integer i = Test2.add(1, 2);   //这两个参数都是Integer，所以T为Integer类型  
        Number f = Test2.add(1.2, 1);  //这两个参数一个是Integer，一个是Double，所以取同一父类的最小级，为Number  
        Object o = Test2.add(1, "asd");  //这两个参数一个是Integer，一个是String，所以取同一父类的最小级，为Object  

        System.out.println(i.getClass().getName());   //输出： java.lang.Integer
        System.out.println(f.getClass().getName());   //输出： java.lang.Double
        System.out.println(o.getClass().getName());   //输出： java.lang.String

        /**指定泛型的时候*/  
        int a = Test2.<Integer>add(1, 2);  //指定了Integer，所以只能为Integer类型或者其子类  
//        int b = Test2.<Integer>add(1, 2.2);  //编译错误，指定了Integer，不能为Double
        Number c = Test2.<Number>add(1, 2.2);  //指定为Number，所以可以为Integer和Double  
		
	}
	
	public static <T> T add(T... args) {
		return args[1];
	}

}
