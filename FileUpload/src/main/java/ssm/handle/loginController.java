package ssm.handle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ssm.bean.User;

@Controller
public class loginController {
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String index(User user) {
        System.out.println("劲来了----->"+user.getUsername());
        return "redirect:/page/zero/index.html";
    }
}
