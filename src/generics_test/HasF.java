package generics_test;

public class HasF {

	public HasF() {
		// TODO Auto-generated constructor stub
	}
	
	public void f() {
		
	}
	
	//��������ʵ�ַ�ʽ��ȡ�õ�Ч����һ����
	
	//����ʵ��
	class Test1<T extends HasF> {
		
		private T obj;
		
		public Test1(T x) {

			this.obj = x;
		}
		
		public void manipulate(){
		    obj.f();
		  }
			
		}

	//��̬ʵ��
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
