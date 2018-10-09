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

	    // 临界资源
	    private  List list = new ArrayList();

	    // 对临界资源的访问
	    public void add() {
	        list.add("rico");
	    }

	    public int size() {
	        return list.size();
	    }
	}
	// 线程B
	static class ThreadB extends Thread {

		private MyList  list;

		public ThreadB(MyList  list) {
			super();
			this.list = list;
		}

		@Override
		public void run() { // 任务 B
			try {
				while (true) {
					if (isStop) {
						System.out.println("list中的元素个数为2了，线程b要退出了！");
						throw new InterruptedException();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// 线程A
	static class ThreadA extends Thread {

		private MyList  list;

		public ThreadA(MyList  list) {
			super();
			this.list = list;
		}

		@Override
		public void run() { // 任务 A
			try {
				for (int i = 0; i < 3; i++) {
					list.add();
					System.out.println("添加了" + (i + 1) + "个元素");
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
