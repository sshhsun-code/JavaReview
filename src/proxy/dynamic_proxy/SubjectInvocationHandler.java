package proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SubjectInvocationHandler implements InvocationHandler {
	 //���������һ��ί����Ķ�������  
	 private Object delegate;  
	   
	 public SubjectInvocationHandler(Object delegate) {  
	  this.delegate = delegate;  
	 }
	   

	@Override
	public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
		 long stime = System.currentTimeMillis();   
		  //���÷�����ƽ�������ɸ�ί���ദ��Method��invoke����Object������Ϊ����ִ�н����  
		  //��Ϊʾ������û�з���ֵ��������������˷���ֵ����  
		 arg1.invoke(delegate, arg2);  
		  long ftime = System.currentTimeMillis();   
		  System.out.println("ִ�������ʱ"+(ftime - stime)+"����");  
		    
		  return null;  
	}

}
