import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
* hashMap的底层学习
*   hashMap底层是数组+链表的结构组成的。首先一组数据会先根据hashcode的计算规则生成各自的hashcode，
*   所有数据的hashcode会形成一个数组，在将数据分类，各自存储到自己的hashcode桶里，可能一个hashcode
*   桶中会有大量的数据，每个hashcode作为头部形成一条链表，这就得看你的hashcode的生成算法了，
*   合理的话大家会分配的比较平均，不合理的话撑的撑死，
*   饿的饿死，导致数据多的哪一组code下的链表数据多，会影响这一组数据的查询效率的，本来链表查的就慢，
*   get方法就是先根据你的key值计算hashcode找到对应的位置，然后根据key值去比较取出对应的数据。
*   如果想要hashmap效率高就自己根据数据的规律，重写hashcode写出适合自己数据的一套，使得每一个hashcode下的
*   数据竟可能的分布均匀。
*   还有hashMap是线程不安全的，所以如果涉及到高并发，集群的系统慎用。不过JDK1.5中引入了ConcurrentHashMap解决了HashMap
*   线程不安全问题。
*   总结ConcurrentHashMap就是一个分段的hashtable ，根据自定的hashcode算法生成的对象来获取对应hashcode的分段块进行加锁，
*   不用整体加锁，提高了效率。
*   缺点：在高并发的场景下开销是不能容忍的。容易出现数据库死锁等情况。
* */
/*
* 数据库中的悲观锁和乐观锁总结:
* 悲观锁是在操作数据之前就查询出来的相关数据先锁住，不让其他的线程和事物操作，等待当前的线程执行完所有的业务，执行update的时候
* 解锁，别的线程才可以操作。
* 乐观锁则是在数据库表中设置一个字段记录数据操作的次数，例如version，当去执行数据修改时可以去校验当前取到的version，
* 和数据库中是否一致，一致才可以操作数据并且将version+1。如果，没有控制好线程可能出现同一条数据被多个线程，一起操作的可能，
* 所以乐观锁一般在简单并发不多的业务系统中使用。
* 缺点：乐观锁只能对一张表的数据进行加锁，如果是需要对多张表的数据操作加分布式锁，基于版本号的乐观锁是办不到的
* */
public class LeanMap<H> {

    public static void main(String[] args) throws Exception{
        /*
         * java 反射机制
         * */
        Object obj = Class.forName("ssm.handle.DemoController");
        Method[] m = ((Class) obj).getMethods();
        StringBuffer stringBuffer = new StringBuffer();
        for(Method method:m) {
            stringBuffer.append( method.getName()+",");
        }
        System.out.println(stringBuffer.toString());

  /*      Class c = Class.forName("ssm.bean.Result");
        Constructor<?>[] constructor = c.getConstructors();
        Object result = constructor[1].newInstance(111,"fdsaf");
        System.out.println(JSON.toJSONString(result));*/
        List<String> a  = new ArrayList<>(1999999911);
        System.out.println(a.size());
        Collections.synchronizedList(a);
    }

}
