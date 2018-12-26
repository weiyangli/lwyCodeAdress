package ssm.handle;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InterceptorDemo implements HandlerInterceptor {
    /*
    * 拦截器预处理方法
    * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookie = request.getCookies();
        if (cookie == null || cookie.length == 0) {
            System.out.println("该用户未登陆");
        } else {
            for(Cookie cookie1:cookie) {
                if(cookie1.getName().equals("token")) {
                    String token = cookie1.getValue();
                    System.out.println("用户已登录 token为---》"+token);
                }
            }
        }
        System.out.println("preHandle");
        return true;
    }
    /*
     * 预处理返回true后postHandle调用做一些返回处理(请求的方法访问结束后调用)
     * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    /*
    * 后处理方法待放回处理执行完后，方法所有业务处理完以后执行(请求的方法访问结束后调用)
    * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
