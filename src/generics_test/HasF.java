package generics_test;

public class HasF {

	public HasF() {
		// TODO Auto-generated constructor stub
	}
	
	public void f() {
		
	}
	
	//以下两种实现方式所取得的效果是一样的
	
	//泛型实现
	class Test1<T extends HasF> {
		
		private T obj;
		
		public Test1(T x) {

			this.obj = x;
		}
		
		public void manipulate(){
		    obj.f();
		  }
			
		}

	//多态实现
	class Manipulator2{
		  private HasF obj;
		  public Manipulator2(HasF x){
		    this.obj = x;
		  }
		  public void manipulate(){
		    obj.f();
		  }
		  
	}

}
