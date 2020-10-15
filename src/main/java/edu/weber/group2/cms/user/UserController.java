package edu.weber.group2.cms.user;

import edu.weber.group2.cms.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.ZonedDateTime;

@Controller
@RequestMapping("user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;


    private JdbcTemplate jdbcTemplate;
    public JdbcTemplate getJdbcTemplate()
    {
        return jdbcTemplate;
    }

    @RequestMapping(value="login", method= RequestMethod.GET)
    public String getLoginPage(Model model)
    {
        model.addAttribute("user", new User());
        return "user/login";
    }

    @RequestMapping(value="login", method=RequestMethod.POST)
    public String postLoginPage(Model model)
    {
        return "user/register";
    }


    @RequestMapping(value="register", method=RequestMethod.GET)
    public String getRegisterPage(Model model)
    {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @RequestMapping(value="register", method=RequestMethod.POST)
    public String postRegisterPage(@ModelAttribute("user") User user, BindingResult result)
    {
        boolean isValid = true;
        ZonedDateTime dateTime = ZonedDateTime.now();
        if(user.getFirstName().equalsIgnoreCase(""))
        {
            System.out.print("First Name");
            isValid = false;
        }
        if(user.getLastName().equalsIgnoreCase(""))
        {
            System.out.print("First Name");
            isValid = false;
        }
        if(user.getUserName().equalsIgnoreCase(""))
        {
            System.out.print("First Name");
            isValid = false;
        }
        if(!user.getPassword().equalsIgnoreCase(""))
        {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        else {
            System.out.print("First Name");
            isValid = false;
        }
        if(!isValid)
        {
            return "user/register";
        }


        user.setEnabled(true);
        user.setLocked(false);
        user.setExpiredOn(dateTime);
        user.setCreatedOn(dateTime);
        user.setCredentialExpiredOn(dateTime);
        user.setModifiedOn(dateTime);

        userService.addUser(user);


        return "redirect:/";
    }

}