package read_write_lock_test;

public class Test {
	
	public static void main(String[] args)  {
        final Test test = new Test();

        new Thread("A"){
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();

        new Thread("B"){
            public void run() {
                test.get(Thread.currentThread());
            };
        }.start();

    }  

    public synchronized void get(Thread thread) {
        long start = System.currentTimeMillis();
        System.out.println("�߳�"+ thread.getName()+"��ʼ������...");
        while(System.currentTimeMillis() - start <= 1) {
            System.out.println("�߳�"+ thread.getName()+"���ڽ��ж�����...");
        }
        System.out.println("�߳�"+ thread.getName()+"���������...");
    }

}
