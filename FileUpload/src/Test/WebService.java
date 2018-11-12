import ssm.service.serviceInterface.WebServiceImp;

import javax.xml.ws.Endpoint;

public class WebService {
    public static void main(String[] args) {
            String address = "http://192.168.10.158:8989/WS_Server/Webservice";
            //使用Endpoint类提供的publish方法发布WebService，发布时要保证使用的端口号没有被其他应用程序占用
            Endpoint.publish(address , new WebServiceImp());
            System.out.println("发布webservice成功!");
    }
}
