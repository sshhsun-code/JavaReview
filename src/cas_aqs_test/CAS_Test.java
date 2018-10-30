package cas_aqs_test;

/**
 * @author CM
 *
 *CAS(Compare And Swap)�����Ƚϲ��������ǽ�����̲߳��������ʹ�������������ĵ�һ�ֻ��ƣ�
 *CAS�����������������������ڴ�λ�ã�V����Ԥ��ԭֵ��A������ֵ(B)������ڴ�λ�õ�ֵ��Ԥ��ԭֵ��ƥ�䣬��
 *ô���������Զ�����λ��ֵ����Ϊ��ֵ�����򣬴����������κβ��������������������������CASָ��֮ǰ���ظ�λ
 *�õ�ֵ��CAS��Ч��˵���ˡ�����Ϊλ��VӦ�ð���ֵA�����������ֵ����B�ŵ����λ�ã����򣬲�Ҫ���ĸ�λ�ã�
 *ֻ���������λ�����ڵ�ֵ���ɡ�

��JAVA�У�sun.misc.Unsafe ���ṩ��Ӳ�������ԭ�Ӳ�����ʵ�����CAS�� java.util.concurrent ���µĴ����඼ʹ������� Unsafe.java ���CAS���������� Unsafe.java �ľ���ʵ������Ͳ������ˡ�
 *
 */
public class CAS_Test {

}

/**
 * @author CM
 * 
 * java.util.concurrent.atomic ���µ�������ʹ��CAS������ʵ�ֵ�(eg. AtomicInteger.java,AtomicBoolean,AtomicLong)�������� AtomicInteger.java �Ĳ���ʵ�������½�������Щԭ�����ʵ�֡�
 * 
 * һ����˵�ھ��������ر��ҵ�ʱ��ʹ�øð��µ�ԭ�Ӳ������ܱ�ʹ�� synchronized �ؼ��ֵķ�ʽ��Ч�Ķ�(�鿴getAndSet()����֪�����Դ����ʮ�ּ��ҵĻ������forѭ�����ܻ������ܾö����ܳɹ������������������������Ҫ���ǽ�����Դ��������)�� 
�ڽ϶�ĳ������Ƕ����ܻ�ʹ�õ���Щԭ�������
 *
 */
class AtomicInteger extends Number implements java.io.Serializable {
    private static final long serialVersionUID = 6214790243416807050L;

    // setup to use Unsafe.compareAndSwapInt for updates
    private static final Unsafe unsafe = Unsafe.getUnsafe();

    private volatile int value;// ��ʼint��С
    // ʡ���˲��ִ���...

    // ���������캯���������ó�ʼint��С
    public AtomicInteger(int initialValue) {
        value = initialValue;
    }
    // �����������캯��,��ʼint��СΪ0
    public AtomicInteger() {
    }

    // ��ȡ��ǰֵ
    public final int get() {
        return value;
    }

    // ����ֵΪ newValue
    public final void set(int newValue) {
        value = newValue;
    }

    //���ؾ�ֵ����������ֵΪ��newValue
    public final int getAndSet(int newValue) {
        /**
        * ����ʹ��forѭ������ͨ��CAS������������ֵ
        * CASʵ�ֺͼ���ʵ�ֵĹ�ϵ�е������ֹ����ͱ������Ĺ�ϵ
        * */
        for (;;) {
            int current = get();
            if (compareAndSet(current, newValue))
                return current;
        }
    }

    // ԭ�ӵ�������ֵΪupdate, expectΪ�����ĵ�ǰ��ֵ
    public final boolean compareAndSet(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }

    // ��ȡ��ǰֵcurrent����������ֵΪcurrent+1
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

    // �˴�ʡ�Բ��ִ��룬���µĴ������ʵ��ԭ�������Ƶ�
}



