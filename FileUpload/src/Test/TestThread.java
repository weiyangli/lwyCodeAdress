import ssm.bean.Result;
import ssm.util.Utils;

public class TestThread {
    public static void main(String[] args) {
        /*for(int i=0;i<=10;i++){
            NewThread newThread = new NewThread(new Result(89757,"我是000"+i));
            newThread.start();
        }*/
        String AAA =  Utils.passwordByBCrypt("123456");
        System.out.println(AAA);
        boolean flag =  Utils.isPasswordValidByBCrypt("123456",AAA);
        System.out.println(flag);
    }

}
