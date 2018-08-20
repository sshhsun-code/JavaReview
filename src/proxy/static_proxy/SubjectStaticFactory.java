package proxy.static_proxy;

public class SubjectStaticFactory {

	public SubjectStaticFactory() {
		// TODO Auto-generated constructor stub
	}

	 public static Subject getInstance() {   
		  return new ProxySubject(new RealSubject());  
	}
}
