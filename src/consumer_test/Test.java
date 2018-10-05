package consumer_test;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		MyList service = new MyList();

        ThreadA a = new ThreadA(service,"A");
        ThreadB b = new ThreadB(service,"B");

        a.start();
        b.start();

	}

}
//��Դ��
class MyList {

  //�ٽ���Դ
  private volatile List<String> list = new ArrayList<String>();

  public void add() {
      list.add("abc");
  }

  public int size() {
      return list.size();
  }
}

//�߳�A
class ThreadA extends Thread {

  private MyList list;

  public ThreadA(MyList list,String name) {
      super(name);
      this.list = list;
  }

  @Override
  public void run() {
      try {
          for (int i = 0; i < 3; i++) {
              list.add();
              System.out.println("�����" + (i + 1) + "��Ԫ��");
              Thread.sleep(1000);
          }
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }
}

//�߳�B
class ThreadB extends Thread {

  private MyList list;

  public ThreadB(MyList list,String name) {
      super(name);
      this.list = list;
  }

  @Override
  public void run() {
      try {
          while (true) {          // while �����ѯ
              if (list.size() == 2) {
                  System.out.println("==2�ˣ��߳�bҪ�˳��ˣ�");
                  throw new InterruptedException();
              }
          }
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  }
}