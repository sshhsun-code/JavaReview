package proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import proxy.static_proxy.RealSubject;
import proxy.static_proxy.Subject;

public class DynProxyFactory {

	public DynProxyFactory() {
		// TODO Auto-generated constructor stub
	}
	 public static Subject getSubjectInstance() {
		  Subject delegate = new RealSubject();  
		  InvocationHandler handler = new SubjectInvocationHandler(delegate);
		  Subject proxy = null; 
		  
		  proxy = (Subject) Proxy.newProxyInstance(delegate.getClass().getClassLoader(),
				  delegate.getClass().getInterfaces(), 
				  handler);
		 return proxy;
	 }
	 
    public static ISing getSingInstance() {
	  ISing delegate = new SingImpl();  
	  InvocationHandler handler = new SubjectInvocationHandler(delegate);
	  ISing proxy = null; 
	  
	  proxy = (ISing) Proxy.newProxyInstance(delegate.getClass().getClassLoader(),
			  delegate.getClass().getInterfaces(), 
			  handler);
	 return proxy;
}


}
