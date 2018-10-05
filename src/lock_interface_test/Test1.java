package lock_interface_test;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test1 {

	private ArrayList<Integer> arrayList = new ArrayList<Integer>();
	private Lock lock = new ReentrantLock();
	
    public static void main(String[] args) {
        final Test1 test = new Test1();

        new Thread("A") {
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();

        new Thread("B") {
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();
    }
	
    public void insert(Thread thread) {
        if (lock.tryLock()) {     // ʹ�� tryLock()
            try {
                System.out.println("�߳�" + thread.getName() + "�õ�����...");
                Thread.sleep(5000);
                for (int i = 0; i < 5; i++) {
                    arrayList.add(i);
                }
            } catch (Exception e) {

            } finally {
                System.out.println("�߳�" + thread.getName() + "�ͷ�����...");
                lock.unlock();
            }
        } else {
            System.out.println("�߳�" + thread.getName() + "��ȡ��ʧ��...");
        }
    }
}
