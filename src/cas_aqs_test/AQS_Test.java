package cas_aqs_test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author CM
 * 
 * AQS是JDK下提供的一套用于实现基于FIFO等待队列的阻塞锁和相关的同步器的一个同步框架。
 * 这个抽象类被设计为作为一些可用原子int值来表示状态的同步器的基类。如果你有看过类似 
 * CountDownLatch 类的源码实现，会发现其内部有一个继承了
 *  AbstractQueuedSynchronizer 的内部类 Sync 。
 *  可见 CountDownLatch 是基于AQS框架来实现的一个同步器.类似的同步器在JUC下还有不少。(eg. Semaphore )
 * 
 * AQS管理一个关于状态信息的单一整数，该整数可以表现任何状态
 * 
 * 比如， Semaphore 用它来表现剩余的许可数，ReentrantLock 用它来表现拥有它的线程已经请求了多少次锁；FutureTask 用它来表现任务的状态(尚未开始、运行、完成和取消)
 * 
 * 如JDK的文档中所说，使用AQS来实现一个同步器需要覆盖实现如下几个方法，并且使用getState,setState,compareAndSetState这几个方法来设置获取状态 
 * 
 * 1. boolean tryAcquire(int arg) 
2. boolean tryRelease(int arg) 
3. int tryAcquireShared(int arg) 
4. boolean tryReleaseShared(int arg) 
5. boolean isHeldExclusively()

以上方法不需要全部实现，根据获取的锁的种类可以选择实现不同的方法，支持独占(排他)获取锁的同步器应该实现tryAcquire、 
tryRelease、isHeldExclusively而支持共享获取的同步器应该实现tryAcquireShared、tryReleaseShared、isHeldExclusively。
下面以 CountDownLatch 举例说明基于AQS实现同步器, CountDownLatch 用同步状态持有当前计数，countDown方法调用 release
从而导致计数器递减；当计数器为0时，解除所有线程的等待；await调用acquire，如果计数器为0，acquire 会立即返回，否则阻塞。通常用
于某任务需要等待其他任务都完成后才能继续执行的情景。

 *
 */
public class AQS_Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

/**
 * CountDownLatch源码
 * 
 * 下面以 CountDownLatch 举例说明基于AQS实现同步器, CountDownLatch 用同步状态持有当前计数，countDown方法调用 release
从而导致计数器递减；当计数器为0时，解除所有线程的等待；await调用acquire，如果计数器为0，acquire 会立即返回，否则阻塞。通常用
于某任务需要等待其他任务都完成后才能继续执行的情景。
 * 
 * 
 * @author CM
 *
 */
class CountDownLatch {
    /**
     * 基于AQS的内部Sync
     * 使用AQS的state来表示计数count.
     */
    private static final class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 4982264981922014374L;

        Sync(int count) {
            // 使用AQS的getState()方法设置状态
            setState(count);
        }

        int getCount() {
            // 使用AQS的getState()方法获取状态
            return getState();
        }

        // 覆盖在共享模式下尝试获取锁
        protected int tryAcquireShared(int acquires) {
            // 这里用状态state是否为0来表示是否成功，为0的时候可以获取到返回1，否则不可以返回-1
            return (getState() == 0) ? 1 : -1;
        }

        // 覆盖在共享模式下尝试释放锁
        protected boolean tryReleaseShared(int releases) {
            // 在for循环中Decrement count直至成功;
            // 当状态值即count为0的时候，返回false表示 signal when transition to zero
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

    // 使用给定计数值构造CountDownLatch
    public CountDownLatch(int count) {
        if (count < 0) throw new IllegalArgumentException("count < 0");
        this.sync = new Sync(count);
    }

    // 让当前线程阻塞直到计数count变为0，或者线程被中断
    public void await() throws InterruptedException {
        sync.acquireSharedInterruptibly(1);
    }

    // 阻塞当前线程，除非count变为0或者等待了timeout的时间。当count变为0时，返回true
    public boolean await(long timeout, TimeUnit unit)
        throws InterruptedException {
        return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
    }

    // count递减
    public void countDown() {
        sync.releaseShared(1);
    }

    // 获取当前count值
    public long getCount() {
        return sync.getCount();
    }

    public String toString() {
        return super.toString() + "[Count = " + sync.getCount() + "]";
    }
}
