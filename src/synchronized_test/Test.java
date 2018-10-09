package synchronized_test;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		try {
            //临界资源
            PublicVar publicVarRef = new PublicVar();

            //创建并启动线程
            ThreadA thread = new ThreadA(publicVarRef);
            thread.start();

            Thread.sleep(200);// 打印结果受此值大小影响

            //在主线程中调用
            publicVarRef.getValue();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

}

//资源类
class PublicVar {

  public String username = "A";
  public String password = "AA";

  //同步实例方法
  public synchronized void setValue(String username, String password) {
      try {
          this.username = username;
          Thread.sleep(5000);
          this.password = password;

          System.out.println("method=setValue " +"\t" + "threadName="
                  + Thread.currentThread().getName() + "\t" + "username="
                  + username + ", password=" + password);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }

  //非同步实例方法
  public void getValue() {
      System.out.println("method=getValue " + "\t" +  "threadName="
              + Thread.currentThread().getName()+ "\t" + " username=" + username
              + ", password=" + password);
  }
}


//线程类
class ThreadA extends Thread {

  private PublicVar publicVar;

  public ThreadA(PublicVar publicVar) {
      super();
      this.publicVar = publicVar;
  }

  @Override
  public void run() {
      super.run();
      publicVar.setValue("B", "BB");
  }
}