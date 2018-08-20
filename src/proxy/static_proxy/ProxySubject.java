package proxy.static_proxy;

public class ProxySubject implements Subject{

	 //���������һ��ί����Ķ�������  
	 private Subject delegate;  
	   
	 public ProxySubject(Subject delegate) {  
	  this.delegate = delegate;  
	 }  
	  
	 /** 
	  * ��������ɸ�ί����ִ�У���¼����ִ��ǰ���ʱ�䣬ʱ��Ϊ����Ĵ���ʱ�� 
	  *  
	  * @param taskName 
	  */  
	 @Override  
	 public void dealTask(String taskName) {  
	  long stime = System.currentTimeMillis();   
	  //��������ɸ�ί���ദ��  
	  delegate.dealTask(taskName);  
	  long ftime = System.currentTimeMillis();   
	  System.out.println("ִ�������ʱ"+(ftime - stime)+"����");  
	    
	 }

}
