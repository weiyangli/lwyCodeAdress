package ssm.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ssm.bean.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登陆成功处理器，主要作用为创建 token 保存到 cookie，然后跳转到角色对应的页面
 */
public class AuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        // 用户名密码查询用户 (访问这个函数，说明是通过表单成功登录过来的，使用用户名密码一定能够查询到用户)
        String username = request.getParameter(SecurityConstant.LOGIN_USERNAME);
        String password = request.getParameter(SecurityConstant.LOGIN_PASSWORD);
        // 生成 Spring Security 可使用的用户对象，保存到 SecurityContext 供 Spring Security 使用
        User user  = new User();
        user.setNickname(username);
        user.setPassword(password);
        user.setRole("ROLE_USER");
        user = User.userForSpringSecurity(user);
        Authentication auth =  new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        // 通过用户名区分角色跳转不同的页面
        if (username.equals("admin")) {
            // 普通管理员
            response.sendRedirect("/page/prize/index.html");
        } else if (username.equals("adminSystem")) {
            // 系统管理员
            response.sendRedirect("/page/zero/index.html");
        }
    }
}
