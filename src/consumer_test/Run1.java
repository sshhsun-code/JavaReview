package consumer_test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Run1 {
	
	public static void main(String[] args) {
		
		MyService service = new MyService();
		MyThreadA threadA = new MyThreadA(service, "Thread-A");
		MyThreadB threadB = new MyThreadB(service, "Thread-B");
		threadA.start();
		threadB.start();
	}

}class MyThreadA extends Thread {

    private MyService myService;

    public MyThreadA(MyService myService, String name) {
        super(name);
        this.myService = myService;
    }

    @Override
    public void run() {
        while (true)
            myService.set();
    }
}

// �߳�B
class MyThreadB extends Thread {

    private MyService myService;

    public MyThreadB(MyService myService, String name) {
        super(name);
        this.myService = myService;
    }

    @Override
    public void run() {
        while (true)
            myService.get();
    }
}

//��Դ��
class MyService {

 private ReentrantLock lock = new ReentrantLock();
 private Condition conditionA = lock.newCondition();   // �����߳�
 private Condition conditionB = lock.newCondition();  // �����߳�
 private boolean hasValue = false;

 public void set() {
     try {
         lock.lock();
         while (hasValue == true) {
             System.out.println("[�����߳�] " + " �߳�"
                     + Thread.currentThread().getName() + " await...");
             conditionA.await();
         }
         System.out.println("[������] " + " �߳�" + Thread.currentThread().getName() + " ������");
         Thread.sleep(1000);
         hasValue = true;
         System.out.println("�߳�" + Thread.currentThread().getName() + " �������...");
         System.out.println("[�������������߳�] " + " �߳�"
                 + Thread.currentThread().getName() + "...");
         conditionB.signalAll();
     } catch (InterruptedException e) {
         e.printStackTrace();
     } finally {
         lock.unlock();
     }
 }

 public void get() {
     try {
         lock.lock();
         while (hasValue == false) {
             System.out.println("[�����߳�] " + " �߳�"
                     + Thread.currentThread().getName() + " await...");
             conditionB.await();
         }
         System.out.println("[������] " + " �߳�"
                 + Thread.currentThread().getName() + " ���ѡ�");
         Thread.sleep(1000);
         System.out.println("�߳�" + Thread.currentThread().getName() + " �������...");
         hasValue = false;
         System.out.println("[�������������߳�] " + " �߳�"
                 + Thread.currentThread().getName() + "...");
         conditionA.signalAll();
     } catch (InterruptedException e) {
         e.printStackTrace();
     } finally {
         lock.unlock();
     }
 }
}