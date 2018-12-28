package ssm.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ssm.bean.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/*
 * Cookie 用于浏览器保存数据的，浏览器关闭后数据消失。会话层跟踪技术。session也是，
 * seesion可以跨浏览器，是服务端的会话层跟踪技术
 * */
@Controller
public class loginController {
    private static final Logger log = LoggerFactory.getLogger(loginController.class);
    private static final String appId = "LWY";
    private static final String appKey = "shuaige";
    private static final long expiredAt = System.currentTimeMillis() + 1000 * 30; // 设置失效时间为30秒

    /*
    * jwt 的token其实就是一个令牌。用户登陆时校验用户密码正确后，根据用户信息生成 plyload 载荷，
    * 类型和算法生成 header 头部承载， 然后最关键的就是签名根据 设定的 appId 和 appKey 生成签名值，为了安全可以
    * 对签名值进行定期跟换。还可以设置token的失效时间
    * */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String index(User user, HttpServletResponse response) {
        System.out.println("劲来了----->" + user.getUsername());
        // 根据用户信息生成token
        String token = Jwt.create(appId, appKey).expiredAt(expiredAt)
                .param("username", user.getUsername())
                .param("password", user.getPassword())
                .param("role", "rich")
                .token();
        log.info("用户生成token为----->" + token);
        Cookie cookie = new Cookie("token", token);
        response.addCookie(cookie);
        return "redirect:/page/prize/index.html";
    }
}
