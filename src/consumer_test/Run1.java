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

// 线程B
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

//资源类
class MyService {

 private ReentrantLock lock = new ReentrantLock();
 private Condition conditionA = lock.newCondition();   // 生产线程
 private Condition conditionB = lock.newCondition();  // 消费线程
 private boolean hasValue = false;

 public void set() {
     try {
         lock.lock();
         while (hasValue == true) {
             System.out.println("[生产线程] " + " 线程"
                     + Thread.currentThread().getName() + " await...");
             conditionA.await();
         }
         System.out.println("[生产中] " + " 线程" + Thread.currentThread().getName() + " 生产★");
         Thread.sleep(1000);
         hasValue = true;
         System.out.println("线程" + Thread.currentThread().getName() + " 生产完毕...");
         System.out.println("[唤醒所有消费线程] " + " 线程"
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
             System.out.println("[消费线程] " + " 线程"
                     + Thread.currentThread().getName() + " await...");
             conditionB.await();
         }
         System.out.println("[消费中] " + " 线程"
                 + Thread.currentThread().getName() + " 消费☆");
         Thread.sleep(1000);
         System.out.println("线程" + Thread.currentThread().getName() + " 消费完毕...");
         hasValue = false;
         System.out.println("[唤醒所有生产线程] " + " 线程"
                 + Thread.currentThread().getName() + "...");
         conditionA.signalAll();
     } catch (InterruptedException e) {
         e.printStackTrace();
     } finally {
         lock.unlock();
     }
 }
}