package generics_test;

import java.util.Date;

/**
 * @author CM
 *
 *�ɷ�����Ķ����֪��Pair<T> ��һ�������࣬��Ϊ���������������Ͳ�����
 *
 * @param <T>
 */
public class Pair<T> {
    private T first;
    private T second;

    public Pair(T first, T second){
        this.first = first;
        this.second = second;
    }
    public void setFirst(T first){
        this.first = first;
    }
    public T getFirst(){
        return first;
    }
    public void setSecond(T second){
        this.second = second;
    }
    public T getSecond(){
        return second;
    }
}

/**
 * @author CM
 *��DateInterval ����û�и����Ͳ����б���˸������һ�� T ���滻Ϊ Date ��ʵ���࣬���Pair<Date>�̳еõ��ķ����б��뷺�ͳ����޹ء�
 */
class DateInterval extends Pair<Date> {     // ʱ������
    public DateInterval(Date first, Date second){
        super(first, second);
    }

    @Override
    public void setSecond(Date second) {
        super.setSecond(second);
    }
    @Override
    public Date getSecond(){
        return super.getSecond();
    }
}


/**
 * @author CM
 *TInterval<T>  ��һ�������࣬�̳���Pair<T> �����������������Ͳ�����
 *
 * @param <T>
 */
class TInterval<T> extends Pair<T> {     // ���ͼ����
    public TInterval(T first, T second){
        super(first, second);
    }

    @Override
    public void setSecond(T second) {
        super.setSecond(second);
    }
    @Override
    public T getSecond(){
        return super.getSecond();
    }
}