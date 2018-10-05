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
        System.out.println("线程"+ thread.getName()+"开始读操作...");
        while(System.currentTimeMillis() - start <= 1) {
            System.out.println("线程"+ thread.getName()+"正在进行读操作...");
        }
        System.out.println("线程"+ thread.getName()+"读操作完毕...");
    }

}
