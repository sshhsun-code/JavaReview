package synchronized_test;

public class Run1 {

	public Run1() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws InterruptedException {

        //临界资源
        MyService service = new MyService();

        //线程A
        Thread31 a = new Thread31(service);
        a.setName("A");

        //线程B
        Thread32 b = new Thread32(service);
        b.setName("B");

        a.start();
        Thread.sleep(50);// 存在50毫秒
        b.start();
    }

}

//资源类
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

//线程B
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

//线程A
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
