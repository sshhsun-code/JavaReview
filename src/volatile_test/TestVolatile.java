package volatile_test;

public class TestVolatile {

	public TestVolatile() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ThreadDemo td = new ThreadDemo();
        new Thread(td, "ThreadDemo").start();

        while (true) {
            // ��������������������һ�䣬���򶼻�����������
//             System.out.println("!!");                              //...���1
//             synchronized (TestVolatile.class) {}                     //...���2
//            test2();                                    //...���3

            // ��ֻ��������һ����룬���򶼻���ѭ����
             test1();                                  //...���4

            if (td.flag) {
                System.out.println("�߳� " + Thread.currentThread().getName()
                        + " ��������whileѭ����... ");
                break;
            }
        }
      }
	
	public static void test1() {
		 int i = 0;

	}

    public synchronized static void test2() {}

}

class ThreadDemo implements Runnable {

    public boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        flag = true;
        System.out.println("�߳� " + Thread.currentThread().getName() + " ִ����ϣ� "
                + "��  flag= " + flag + " ...");
    }
}