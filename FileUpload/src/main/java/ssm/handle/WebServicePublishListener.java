package  ssm.handle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.ws.Endpoint;
import ssm.service.serviceInterface.WebServiceImp;

import java.net.InetAddress;

/**
 * @author gacl
 * 用于发布WebService的监听器
 */
//使用Servlet3.0提供的@WebListener注解将实现了ServletContextListener接口的WebServicePublishListener类标注为一个Listener
@WebListener
public class WebServicePublishListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        InetAddress addr = null;
        try {
             addr = InetAddress.getLocalHost();
        }catch (Exception e){
            System.out.println("获取本地Ip失败");
        }
        String ip=addr.getHostAddress(); //获取本机ip
        System.out.println("获取本地的Ip为---》"+ip);
        //WebService的发布地址
        String address = "http://192.168.10.158:8080/WS_Server/WebService";
        //发布WebService，WebServiceImpl类是WebServie接口的具体实现类
        Endpoint.publish(address , new WebServiceImp());
        System.out.println("使用WebServicePublishListener发布webservice成功!");
    }

    public static void main(String[] args) {
        String address = "http://192.168.10.158:8999/WS_Server/WebService";
        //发布WebService，WebServiceImpl类是WebServie接口的具体实现类
        Endpoint.publish(address , new WebServiceImp());
        System.out.println("使用WebServicePublishListener发布webservice成功!");
    }
}