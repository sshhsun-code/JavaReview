package proxy.static_proxy;

public class Client1 {

	public Client1() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		Subject proxy = SubjectStaticFactory.getInstance();
		proxy.dealTask("DBQueryTask");
	}

}
