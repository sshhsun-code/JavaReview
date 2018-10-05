package read_write_lock_test;

import java.util.concurrent.locks.ReentrantLock;

public class RunFair {
	
	private static ReentrantLock mLock = new ReentrantLock(false);

	public static void main(String[] args) {
        final Service service = new Service(mLock);     // 公平锁，设为 true
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("★线程" + Thread.currentThread().getName()
                        + "运行了");
                service.serviceMethod();
            }
        };

        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) 
            threadArray[i] = new Thread(runnable, i+ "");

        for (int i = 0; i < 10; i++) 
            threadArray[i].start(); 
    }
	

}

class Service {
    private ReentrantLock mLock;   
    public Service(ReentrantLock lock) {
        super();
        mLock = lock;
    }
    public void serviceMethod() {
        try {
        	mLock.lock();
            System.out.println("ThreadName=" + Thread.currentThread().getName()
                    + "获得锁定");
        } finally {
        	mLock.unlock();
        }
    }
}