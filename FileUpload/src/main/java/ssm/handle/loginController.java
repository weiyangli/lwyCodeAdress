package ssm.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;
import ssm.bean.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

    /**
     * 权限不够时访问 Spring Security forward request 到此方法.
     */
    @GetMapping("/page/deny")
    @ResponseBody
    public String toDenyPage(HttpServletRequest request) {
        // Ajax 访问时权限不够抛异常，我们提供的异常处理器会转换为 JSON 格式返回.
        if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))) {
            throw new RuntimeException("权限不够");
        }

        // 普通访问返回错误信息或者相关页面
        return "权限不够!";
    }

    /**
     * 处理登录，登录错误，注销，对应的 URL 为:
     *     登录页面: /page/login
     *     登录错误: /page/login?error=1
     *     注销成功: /page/login?logout=1
     *
     * @param error   不为 null 表示登陆出错
     * @param logout  不为 null 表示注销成功
     * @param request HttpServletRequest 对象
     * @param model   保存数据到 view 中显示
     */
    @GetMapping(value= "/page/login")
    public String loginPage(@RequestParam(value="error",  required=false) String error,
                            @RequestParam(value="logout", required=false) String logout,
                            HttpServletRequest request,
                            ModelMap model) {
        // 判断当前登录的状态
        String status = "1";
        status = (error  != null) ? "账号或密码无效" : status; // 登录错误
        status = (logout != null) ? "" : status; // 注销成功
        return "/page/html/index.html";
    }
}
