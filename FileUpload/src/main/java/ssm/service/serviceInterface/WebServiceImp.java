package ssm.service.serviceInterface;

@javax.jws.WebService
public class WebServiceImp implements WebService{
    @Override
    public String sayHello(String name) {
        System.out.println("hello " + name);
        return "Say to Hello";
    }
}
