package volatile_test;

public class ThreadRun {

	public ThreadRun() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		
		MyThread[] mythreadArray = new MyThread[100];
        for (int i = 0; i < 100; i++) {
            mythreadArray[i] = new MyThread();
        }

        for (int i = 0; i < 100; i++) {
            mythreadArray[i].start();
        }

	}

}

class MyThread extends Thread {
    // volatile 共享静态变量，类成员
    public static int count;

    private synchronized static void addCount() {
        for (int i = 0; i < 10; i++) {
            count++;
            System.out.println("count=" + count);
        }
    }

    @Override
    public void run() {
        addCount();
    }
}