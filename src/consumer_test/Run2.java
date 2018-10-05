package consumer_test;

import java.util.concurrent.Semaphore;

/**
 * ʹ���ź���ʵ��������/������ģ��
 * @author sshhsun
 *
 */
public class Run2 {

	int count = 0;
    final Semaphore put = new Semaphore(5);//��ʼ���Ƹ���,ͬ������
    final Semaphore get = new Semaphore(0);//ͬ������
    final Semaphore mutex = new Semaphore(1);//��������
    
    public static void main(String[] args) {
    	Run2 t = new Run2();
    	new Thread(t.new Producer()).start();
        new Thread(t.new Consumer()).start();
        new Thread(t.new Consumer()).start();
        new Thread(t.new Producer()).start();
		
	}
    
    class Producer implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					put.acquire();
					mutex.acquire();
					count++;
                    System.out.println(Thread.currentThread().getName() + "produce:: " + count);
					
				} catch (Exception e) {
					// TODO: handle exception
				}finally {
					mutex.release();
					get.release();
				}
			}
			
		}
    	
    }

    
    class Consumer implements Runnable {

		@Override
		public void run() {
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				try {
					get.acquire();
					mutex.acquire();
					count--;
                    System.out.println(Thread.currentThread().getName() + "consume:: " + count);
					
				} catch (Exception e) {
					// TODO: handle exception
				}finally {
					mutex.release();
					put.release();
				}
			}
			
		}
    	
    }
 
}
