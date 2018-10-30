package cas_aqs_test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author CM
 * 
 * AQS��JDK���ṩ��һ������ʵ�ֻ���FIFO�ȴ����е�����������ص�ͬ������һ��ͬ����ܡ�
 * ��������౻���Ϊ��ΪһЩ����ԭ��intֵ����ʾ״̬��ͬ�����Ļ��ࡣ������п������� 
 * CountDownLatch ���Դ��ʵ�֣��ᷢ�����ڲ���һ���̳���
 *  AbstractQueuedSynchronizer ���ڲ��� Sync ��
 *  �ɼ� CountDownLatch �ǻ���AQS�����ʵ�ֵ�һ��ͬ����.���Ƶ�ͬ������JUC�»��в��١�(eg. Semaphore )
 * 
 * AQS����һ������״̬��Ϣ�ĵ�һ���������������Ա����κ�״̬
 * 
 * ���磬 Semaphore ����������ʣ����������ReentrantLock ����������ӵ�������߳��Ѿ������˶��ٴ�����FutureTask ���������������״̬(��δ��ʼ�����С���ɺ�ȡ��)
 * 
 * ��JDK���ĵ�����˵��ʹ��AQS��ʵ��һ��ͬ������Ҫ����ʵ�����¼�������������ʹ��getState,setState,compareAndSetState�⼸�����������û�ȡ״̬ 
 * 
 * 1. boolean tryAcquire(int arg) 
2. boolean tryRelease(int arg) 
3. int tryAcquireShared(int arg) 
4. boolean tryReleaseShared(int arg) 
5. boolean isHeldExclusively()

���Ϸ�������Ҫȫ��ʵ�֣����ݻ�ȡ�������������ѡ��ʵ�ֲ�ͬ�ķ�����֧�ֶ�ռ(����)��ȡ����ͬ����Ӧ��ʵ��tryAcquire�� 
tryRelease��isHeldExclusively��֧�ֹ����ȡ��ͬ����Ӧ��ʵ��tryAcquireShared��tryReleaseShared��isHeldExclusively��
������ CountDownLatch ����˵������AQSʵ��ͬ����, CountDownLatch ��ͬ��״̬���е�ǰ������countDown�������� release
�Ӷ����¼������ݼ�����������Ϊ0ʱ����������̵߳ĵȴ���await����acquire�����������Ϊ0��acquire ���������أ�����������ͨ����
��ĳ������Ҫ�ȴ�����������ɺ���ܼ���ִ�е��龰��

 *
 */
public class AQS_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

/**
 * CountDownLatchԴ��
 * 
 * ������ CountDownLatch ����˵������AQSʵ��ͬ����, CountDownLatch ��ͬ��״̬���е�ǰ������countDown�������� release
�Ӷ����¼������ݼ�����������Ϊ0ʱ����������̵߳ĵȴ���await����acquire�����������Ϊ0��acquire ���������أ�����������ͨ����
��ĳ������Ҫ�ȴ�����������ɺ���ܼ���ִ�е��龰��
 * 
 * 
 * @author CM
 *
 */
class CountDownLatch {
    /**
     * ����AQS���ڲ�Sync
     * ʹ��AQS��state����ʾ����count.
     */
    private static final class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 4982264981922014374L;

        Sync(int count) {
            // ʹ��AQS��getState()��������״̬
            setState(count);
        }

        int getCount() {
            // ʹ��AQS��getState()������ȡ״̬
            return getState();
        }

        // �����ڹ���ģʽ�³��Ի�ȡ��
        protected int tryAcquireShared(int acquires) {
            // ������״̬state�Ƿ�Ϊ0����ʾ�Ƿ�ɹ���Ϊ0��ʱ����Ի�ȡ������1�����򲻿��Է���-1
            return (getState() == 0) ? 1 : -1;
        }

        // �����ڹ���ģʽ�³����ͷ���
        protected boolean tryReleaseShared(int releases) {
            // ��forѭ����Decrement countֱ���ɹ�;
            // ��״ֵ̬��countΪ0��ʱ�򣬷���false��ʾ signal when transition to zero
            for (;;) {
                int c = getState();
                if (c == 0)
                    return false;
                int nextc = c-1;
                if (compareAndSetState(c, nextc))
                    return nextc == 0;
            }
        }
    }

    private final Sync sync;

    // ʹ�ø�������ֵ����CountDownLatch
    public CountDownLatch(int count) {
        if (count < 0) throw new IllegalArgumentException("count < 0");
        this.sync = new Sync(count);
    }

    // �õ�ǰ�߳�����ֱ������count��Ϊ0�������̱߳��ж�
    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    // ������ǰ�̣߳�����count��Ϊ0���ߵȴ���timeout��ʱ�䡣��count��Ϊ0ʱ������true
    public boolean await(long timeout, TimeUnit unit)
        throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
    }

    // count�ݼ�
    public void countDown() {
        sync.releaseShared(1);
    }

    // ��ȡ��ǰcountֵ
    public long getCount() {
        return sync.getCount();
    }

    public String toString() {
        return super.toString() + "[Count = " + sync.getCount() + "]";
    }
}
