package cas_aqs_test;

/**
 * @author CM
 *
 *CAS(Compare And Swap)，即比较并交换。是解决多线程并行情况下使用锁造成性能损耗的一种机制，
 *CAS操作包含三个操作数——内存位置（V）、预期原值（A）和新值(B)。如果内存位置的值与预期原值相匹配，那
 *么处理器会自动将该位置值更新为新值。否则，处理器不做任何操作。无论哪种情况，它都会在CAS指令之前返回该位
 *置的值。CAS有效地说明了“我认为位置V应该包含值A；如果包含该值，则将B放到这个位置；否则，不要更改该位置，
 *只告诉我这个位置现在的值即可。

在JAVA中，sun.misc.Unsafe 类提供了硬件级别的原子操作来实现这个CAS。 java.util.concurrent 包下的大量类都使用了这个 Unsafe.java 类的CAS操作。至于 Unsafe.java 的具体实现这里就不讨论了。
 *
 */
public class CAS_Test {

}

/**
 * @author CM
 * 
 * java.util.concurrent.atomic 包下的类大多是使用CAS操作来实现的(eg. AtomicInteger.java,AtomicBoolean,AtomicLong)。下面以 AtomicInteger.java 的部分实现来大致讲解下这些原子类的实现。
 * 
 * 一般来说在竞争不是特别激烈的时候，使用该包下的原子操作性能比使用 synchronized 关键字的方式高效的多(查看getAndSet()，可知如果资源竞争十分激烈的话，这个for循环可能换持续很久都不能成功跳出。不过这种情况可能需要考虑降低资源竞争才是)。 
在较多的场景我们都可能会使用到这些原子类操作
 *
 */
class AtomicInteger extends Number implements java.io.Serializable {
    private static final long serialVersionUID = 6214790243416807050L;

    // setup to use Unsafe.compareAndSwapInt for updates
    private static final Unsafe unsafe = Unsafe.getUnsafe();

    private volatile int value;// 初始int大小
    // 省略了部分代码...

    // 带参数构造函数，可设置初始int大小
    public AtomicInteger(int initialValue) {
        value = initialValue;
    }
    // 不带参数构造函数,初始int大小为0
    public AtomicInteger() {
    }

    // 获取当前值
    public final int get() {
        return value;
    }

    // 设置值为 newValue
    public final void set(int newValue) {
        value = newValue;
    }

    //返回旧值，并设置新值为　newValue
    public final int getAndSet(int newValue) {
        /**
        * 这里使用for循环不断通过CAS操作来设置新值
        * CAS实现和加锁实现的关系有点类似乐观锁和悲观锁的关系
        * */
        for (;;) {
            int current = get();
            if (compareAndSet(current, newValue))
                return current;
        }
    }

    // 原子的设置新值为update, expect为期望的当前的值
    public final boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }

    // 获取当前值current，并设置新值为current+1
    public final int getAndIncrement() {
        for (;;) {
            int current = get();
            int next = current + 1;
            if (compareAndSet(current, next))
                return current;
        }
    }
	@Override
	public double doubleValue() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public float floatValue() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int intValue() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public long longValue() {
		// TODO Auto-generated method stub
		return 0;
	}

    // 此处省略部分代码，余下的代码大致实现原理都是类似的
}



