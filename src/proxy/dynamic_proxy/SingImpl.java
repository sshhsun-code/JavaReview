package proxy.dynamic_proxy;

public class SingImpl implements ISing{

	public SingImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sing(String song) {
		 System.out.println("Sing the song£º¡¡"+ song );  
	      try {  
		         Thread.sleep(1000);  
		      } catch (InterruptedException e) {  
		         e.printStackTrace();  
		      }  
	}

}
