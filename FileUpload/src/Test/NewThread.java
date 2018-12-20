import ssm.bean.Result;

public class NewThread  extends Thread{
    private Result result;
    @Override
    public void run() {
        System.out.println("操作指令"+result.getDesc());
        get();
    }
    public NewThread (Result result) {
        this.result = result;
    }
    public synchronized String get () {
        System.out.println("-----------------------");
       String a = "12闺女的双方各";
       System.out.println(a+result.getDesc());
       return a+result.getDesc();
    }
}
