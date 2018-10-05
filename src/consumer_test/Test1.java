package consumer_test;

public class Test1 {
	private static Integer count = 0;
    private final Integer FULL = 5;
    private static String lock = "lock";
    
    public static void main(String[] args) {
    	 Test1 t = new Test1();
    	 new Thread(t.new Producer()).start();
         new Thread(t.new Consumer()).start();
         new Thread(t.new Producer()).start();
         new Thread(t.new Consumer()).start();
	}

    class Producer implements Runnable
    {
        @Override
        public void run()
        {
            for (int i = 0; i < 5; i++)
            {
            	try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                synchronized (lock)
                {
                    while (count >= FULL)
                    {
                            try {
								lock.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                    }
                    count+=1;
                    System.out.println(Thread.currentThread().getName() + "生产了 1个 剩余:: " + count + "个");
                    lock.notifyAll();
                }
            }
        }
    }
    class Consumer implements Runnable
    {
        @Override
        public void run()
        {
            for (int i = 0; i < 5; i++)
            {
            	try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                synchronized (lock)
                {
                    while (count == 0)
                    {
                            try {
								lock.wait();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName()+ "消费了 1个 剩余:: " + count + "个");
                    lock.notifyAll();
                }
            }
        }
    }
}
