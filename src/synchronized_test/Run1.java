package synchronized_test;

public class Run1 {

	public Run1() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws InterruptedException {

        //�ٽ���Դ
        MyService service = new MyService();

        //�߳�A
        Thread31 a = new Thread31(service);
        a.setName("A");

        //�߳�B
        Thread32 b = new Thread32(service);
        b.setName("B");

        a.start();
        Thread.sleep(50);// ����50����
        b.start();
    }

}

//��Դ��
class MyService {
  private String lock = "123";

  public void testMethod() {
      try {
          synchronized (lock) {
              System.out.println(Thread.currentThread().getName() + " begin "
                      + System.currentTimeMillis());
              lock = "456";
              Thread.sleep(2000);
              System.out.println(Thread.currentThread().getName() + "   end "
                      + System.currentTimeMillis());
          }
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }
}

//�߳�B
class Thread32 extends Thread {

  private MyService service;

  public Thread32(MyService service) {
      super();
      this.service = service;
  }

  @Override
  public void run() {
      service.testMethod();
  }
}

//�߳�A
class Thread31 extends Thread {

  private MyService service;

  public Thread31(MyService service) {
      super();
      this.service = service;
  }

  @Override
  public void run() {
      service.testMethod();
  }
}
