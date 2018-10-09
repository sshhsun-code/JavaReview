package synchronized_test;

public class Run {

	public Run() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {

        //临界资源
        Service service = new Service();

        //创建并启动线程A
        Thread1 a = new Thread1(service);
        a.setName("Thread1");
        a.start();

        //创建并启动线程B
        Thread2 b = new Thread2(service);
        b.setName("Thread2");
        b.start();

    }
}

//资源类
class Service {

	Object object = new Object();
  public void print(String stringParam) {
      try {
          synchronized (stringParam) {
        	  for (int i = 0; i < 10; i++) {
                  System.out.println(Thread.currentThread().getName());
                  Thread.sleep(1000);
			}
          }
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }
}

//线程A
class Thread1 extends Thread {
  private Service service;

  public Thread1(Service service) {
      super();
      this.service = service;
  }

  @Override
  public void run() {
      service.print("AA");
  }
}

//线程B
class Thread2 extends Thread {
  private Service service;

  public Thread2(Service service) {
      super();
      this.service = service;
  }

  @Override
  public void run() {
      service.print("AA");
  }
}