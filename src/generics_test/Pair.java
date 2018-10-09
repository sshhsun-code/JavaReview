package generics_test;

import java.util.Date;

/**
 * @author CM
 *
 *由泛型类的定义可知，Pair<T> 是一个泛型类，因为在类名后面有类型参数；
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
 *类DateInterval 后面没有跟类型参数列表，因此该类就是一个 T 被替换为 Date 的实体类，其从Pair<Date>继承得到的方法列表，与泛型彻底无关。
 */
class DateInterval extends Pair<Date> {     // 时间间隔类
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
 *TInterval<T>  是一个泛型类，继承了Pair<T> 且在类名后面有类型参数；
 *
 * @param <T>
 */
class TInterval<T> extends Pair<T> {     // 泛型间隔类
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