package read_write_lock_test;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test1 {
	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public static void main(String[] args)  {
        final Test1 test = new Test1();
        
        new Thread("B"){
            public void run() {
                test.write(Thread.currentThread());
            };
        }.start();

        new Thread("A"){
            public void run() {
                test.read(Thread.currentThread());
            };
        }.start();
    }

    public void read(Thread thread) {
    	rwl.readLock().lock();
    	try {
    		long start = System.currentTimeMillis();
            System.out.println("线程"+ thread.getName()+"开始读操作...");
            while(System.currentTimeMillis() - start <= 1) {
                System.out.println("线程"+ thread.getName()+"正在进行读操作...");
            }
            System.out.println("线程"+ thread.getName()+"读操作完毕...");
		} finally {
			rwl.readLock().unlock();
		}        
    }  

    public void write(Thread thread) {
    	rwl.writeLock().lock();
    	try {
    		long start = System.currentTimeMillis();
            System.out.println("线程"+ thread.getName()+"开始读操作...");
            while(System.currentTimeMillis() - start <= 1) {
                System.out.println("线程"+ thread.getName()+"正在进行写操作...");
            }
            System.out.println("线程"+ thread.getName()+"读操作完毕...");
		} finally {
			rwl.writeLock().unlock();
		}        
    }
}
