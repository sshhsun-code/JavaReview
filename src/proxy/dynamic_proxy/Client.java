package proxy.dynamic_proxy;

import proxy.static_proxy.Subject;

public class Client {

	public Client() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		Subject proxy = DynProxyFactory.getSubjectInstance();
		proxy.dealTask("DBQueryTask From DynProxyFactory");  
		
		ISing singImpl = (ISing) DynProxyFactory.getSingInstance();
		singImpl.sing("my Heart will go on");
	}

}
