import java.lang.reflect.Array;

public class Test {

    /*
     * 教学day3ToNaNa
     * */
    public static void main(String[] args) {
        // 知识点1:while do (语法: do{}代码块类的代码会被一直执行，直到下面的while返回为false为止)
        int i = 5;
        int num = 0;
        do {
            num++;
            System.out.println("我是小于5的数" + num);
        } while (num < i);

        // 知识点2:while 单独使用 (只要布尔表达式为 true，循环就会一直执行下去。)
        int j = 0;
        while (j < 5) {
            System.out.println("我是小于5的数" + j);
            j++;
        }

        /*
         * 总结 do..while 和while的区别：都是根据条件进行循环
         * 1.相同点： 两者都是条件循环，当满足while中条件时就一直循环语句，直到不满足条件为止
         * 2.不同点： while语句是先判断条件，如果第一次判断就返回false，while中的代码块一次都不会访问，
         * do...while 因为while条件在最后所有循环最少要执行一次。
         */

        /*
         * for 循环是用的比较多的语法函数,他和while、do..while是同类型的函数。
         * 写起来比较容易易理解，执行效率高
         * */
        for (int k = 0; k <= 5; k++) {
            // 这个for循环用中文翻译过来为: k的初始值为0并且k是小于等于5的，k++译为只要不超出指定条件，k值每
            // 执行一次加一
            System.out.println("我是当前循环的值--》" + k);
        }
    }

}
