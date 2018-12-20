import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
* */
public class LeanMap {

    public static void main(String[] args) {
       Map<String,String> a = new ConcurrentHashMap<>();
       a.put("fdsf","dsfsdf");
    }

}
