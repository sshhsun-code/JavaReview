package proxy.static_proxy;

public class RealSubject implements Subject{

	public RealSubject() {
		// TODO Auto-generated constructor stub
	}

	@Override
	   public void dealTask(String taskName) {  
	      System.out.println("����ִ������"+taskName);  
	      try {  
	         Thread.sleep(500);  
	      } catch (InterruptedException e) {  
	         e.printStackTrace();  
	      }  
	   }

}
