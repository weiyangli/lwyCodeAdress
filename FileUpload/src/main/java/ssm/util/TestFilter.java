package ssm.util;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * filter 是基于 servlet 类似于 spring 的拦截器，都用了aop的编程思想，横向扩展增强了程序。
 * */
public class TestFilter implements javax.servlet.Filter {

    private String name;
    private String desc;

    /*
     * FilterConfig 用于接收web.xml中配置的参数
     * init 在filter 中一般用于初始化 web.xml 中的参数
     * */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        name = filterConfig.getInitParameter("param1");
        desc = filterConfig.getInitParameter("param2");
        System.out.println(name + desc + "------->进入filter成功!!");
    }

    /*
     * doFilter 处理业务
     * */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        System.out.println("进入 doFilter 开始处理业务");
        Cookie cookie = new Cookie("filter", "TestFilterSuccess");
        ((HttpServletResponse) servletResponse).addCookie(cookie);
        System.out.println("--------------cookie 设置成功");
        // 一定要记得掉用 doFilter ,不然后后面的请求处理都会丢失 request，response
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
