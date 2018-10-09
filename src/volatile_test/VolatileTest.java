package volatile_test;

import java.util.ArrayList;
import java.util.List;

public class VolatileTest {

	public VolatileTest() {
		// TODO Auto-generated constructor stub
	}


	volatile static MyList service = new MyList();

	volatile static boolean isStop = false;
	public static void main(String[] args) {	
		ThreadB b = new ThreadB(service);
		b.setName("B");
		b.start();
		
		ThreadA a = new ThreadA(service);
		a.setName("A");
		a.start();
	}
	
	static class MyList {

	    // �ٽ���Դ
	    private  List list = new ArrayList();

	    // ���ٽ���Դ�ķ���
	    public void add() {
	        list.add("rico");
	    }

	    public int size() {
	        return list.size();
	    }
	}
	// �߳�B
	static class ThreadB extends Thread {

		private MyList  list;

		public ThreadB(MyList  list) {
			super();
			this.list = list;
		}

		@Override
		public void run() { // ���� B
			try {
				while (true) {
					if (isStop) {
						System.out.println("list�е�Ԫ�ظ���Ϊ2�ˣ��߳�bҪ�˳��ˣ�");
						throw new InterruptedException();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// �߳�A
	static class ThreadA extends Thread {

		private MyList  list;

		public ThreadA(MyList  list) {
			super();
			this.list = list;
		}

		@Override
		public void run() { // ���� A
			try {
				for (int i = 0; i < 3; i++) {
					list.add();
					System.out.println("�����" + (i + 1) + "��Ԫ��");
					if (i + 1  >= 2) {
						isStop = true;
					}
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
