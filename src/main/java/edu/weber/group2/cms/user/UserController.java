package edu.weber.group2.cms.user;

import edu.weber.group2.cms.user.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping("login")
    public String getLoginPage(Model model)
    {
            model.addAttribute("user", new User());
            return "user/loginPage";
    }

    @RequestMapping("register")
    public String getRegisterPage(Model model)
    {
        model.addAttribute("user", new User());
        return "registerPage";
    }
}
