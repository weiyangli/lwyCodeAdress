import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ShellSort {
    /**
     * @param args
     */
    public static void main(String[] args) throws  Exception{
        // TODO Auto-generated method stub
       /* ShellSort ss = new ShellSort();
        int num[] = {
                546, 87, 21, 3124, 65, 2, 9, 3, 213, 54, 98, 23, 6, 4, 7,
                8, 123, 872, 61, 5, 8954
        };
        ss.shellArray(num);
        for (int i = 0; i < num.length; i++) {
            System.out.println(num[i]);
        }*/
        int[] fib = new int[20];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++) fib[i] = fib[i - 1] + fib[i - 2];
        for (int i = 0; i < fib.length; i++) System.out.print(fib[i] + " ");
        System.out.println();
    }
    public void shellArray(int[] num) {
        int i = 1;
        int tem, in ;
        for (; i < num.length / 3;) {
            i = 3 * i + 1;
        }
        for (; i >= 1;) {
            for (int j = i; j < num.length; j++) {
                tem = num[j]; in = j;
                while ( in > i - 1 && num[ in -i] >= tem) {
                    num[ in ] = num[ in -i]; in = in -i;
                }
                num[ in ] = tem;
            }
            i = (i - 1) / 3;
        }
    }

    public void move(int n, char a, char b, char c) {
        if (n == 1)
            System.out.println("盘 " + n + " 由 " + a + " 移至 " + c);
        else {
            move(n - 1, a, c, b);
            System.out.println("盘 " + n + " 由 " + a + " 移至 " + c);
            move(n - 1, b, a, c);
        }
    }
}
