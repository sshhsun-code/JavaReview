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
            System.out.println("�߳�"+ thread.getName()+"��ʼ������...");
            while(System.currentTimeMillis() - start <= 1) {
                System.out.println("�߳�"+ thread.getName()+"���ڽ��ж�����...");
            }
            System.out.println("�߳�"+ thread.getName()+"���������...");
		} finally {
			rwl.readLock().unlock();
		}        
    }  

    public void write(Thread thread) {
    	rwl.writeLock().lock();
    	try {
    		long start = System.currentTimeMillis();
            System.out.println("�߳�"+ thread.getName()+"��ʼ������...");
            while(System.currentTimeMillis() - start <= 1) {
                System.out.println("�߳�"+ thread.getName()+"���ڽ���д����...");
            }
            System.out.println("�߳�"+ thread.getName()+"���������...");
		} finally {
			rwl.writeLock().unlock();
		}        
    }
}
