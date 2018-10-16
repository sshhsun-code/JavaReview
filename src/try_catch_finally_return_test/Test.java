package try_catch_finally_return_test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		Test();
//		int value = testBasic1();
//		System.out.println("main testBasic1   = " + value);
		int value1 = testBasic2();
		System.out.println("main testBasic2   = " + value1);
//		List<Object> list = testWrap();
//		for (Object object : list) {
//
//			System.out.println("main list object = " + object);
//		}
	}
	
	private static void Test() {
		
	}
	
	public static  int testBasic(){
        int i = 1; 
        try{
            i++;
            System.out.println("try block, i = "+i);
        }catch(Exception e){
            i ++;
            System.out.println("catch block i = "+i);
        }finally{
            i = 10;
            System.out.println("finally block i = "+i);
        }
        return i;
  }
	
	public static  int testBasic1(){
        int i = 1; 
        try{
            i++;
            System.out.println("try block, i = "+i);
            int tmp = i/0;
            return i;
        }catch(Exception e){
            i ++;
            System.out.println("catch block i = "+i);
            return i;
        }finally{
            i = 10;
            System.out.println("finally block i = "+i);
//            return i;
        }
      }
	
	public static  int testBasic2(){
		int i = 1; 
    try{
        i++;
        Integer.parseInt(null);
        System.out.println("try block, i = "+i);
        return i;
    }catch(Exception e){
        String.valueOf(null);
        System.out.println("catch block i = "+i);
        return i;
    }finally{
        i = 10;
        int m = i / 0;
        System.out.println("finally block i = "+i);
    }
      }
	
	public static  List<Object> testWrap(){
        List<Object> list = new ArrayList<>();
        try{
            list.add("try");
            System.out.println("try block");
            throw new IOException();
//            return list;
        }catch(Exception e){
            list.add("catch");
            System.out.println("catch block");
            return list;
        }finally{
            list.add("finally");
            System.out.println("finally block ");
        }
}

}
