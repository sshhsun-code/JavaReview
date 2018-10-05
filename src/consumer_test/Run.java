package consumer_test;

import java.util.ArrayList;
import java.util.List;

public class Run {

	public static void main(String[] args) {
		 MyStack myStack = new MyStack();

	     P_Thread pThread1 = new P_Thread(myStack, "P1");
	     P_Thread pThread2 = new P_Thread(myStack, "P2");
	     
	     pThread1.start();
	     pThread2.start();
	     
	     C_Thread cThread1 = new C_Thread(myStack, "C1");
	     C_Thread cThread2 = new C_Thread(myStack, "C2");
	     cThread1.start();
	     cThread2.start();
	}
}

//��Դ��
class MyStack {
  // �������
  private List list = new ArrayList();

  // ����
  @SuppressWarnings("unchecked")
  public synchronized void push() {
      try {
          while (list.size() >= 1) {    // ���������
              System.out.println("�����������߳� "
                      + Thread.currentThread().getName() + " ��wait״̬...");
              this.wait();
          }
          list.add("anyString=" + Math.random());
          System.out.println("�߳� " + Thread.currentThread().getName()
                  + " �����ˣ���������...");
          this.notifyAll();                   // ��ֹ�����߽�֪ͨ������
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }

  // ����
  public synchronized String pop() {
      String returnValue = "";
      try {
          while (list.size() == 0) {              // ���������
              System.out.println("�����ѿգ��߳� "
                      + Thread.currentThread().getName() + " ��wait״̬...");
              this.wait();
          }
          returnValue = "" + list.get(0);
          list.remove(0);
          System.out.println("�߳� " + Thread.currentThread().getName()
                  + " �����ˣ������ѿ�...");
          this.notifyAll();                   // ��ֹ�����߽�֪ͨ������
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
      return returnValue;
  }
}

//������
class P_Thread extends Thread {

  private MyStack myStack;

  public P_Thread(MyStack myStack,String name) {
      super(name);
      this.myStack = myStack;
  }

  public void pushService() {
      myStack.push();
  }

  @Override
  public void run() {
      while (true) {     
          myStack.push();
      }
  }
}

//������
class C_Thread extends Thread {

  private MyStack myStack;

  public C_Thread(MyStack myStack,String name) {
      super(name);
      this.myStack = myStack;
  }

  @Override
  public void run() {
      while (true) {
          myStack.pop();
      }
  }
}
