package edu.weber.group2.cms.admin;

import edu.weber.group2.cms.blogPost.BlogService;
import edu.weber.group2.cms.user.UserService;
import edu.weber.group2.cms.user.model.AdminUser;
import edu.weber.group2.cms.user.model.Role;
import edu.weber.group2.cms.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value="admin")
public class AdminController {

    private AdminService adminService;
    private UserService userService;
    private BlogService blogService;

    @Autowired
    public AdminController(AdminService _adminService, UserService _userService, BlogService _blogService)
    {
        adminService = _adminService;
        userService = _userService;
        blogService = _blogService;
    }




    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView GetMainPage(Model model) {
        ModelAndView mv = new ModelAndView("admin/admin");
        List<User> userList = adminService.getAllUsers();
        mv.getModelMap().addAttribute("userList", userList );
        return mv;
    }

//    @RequestMapping(method=RequestMethod.POST)
//    public ModelAndView postRegisterPage(@ModelAttribute("user") User user, BindingResult result)
//    {
//        System.out.print(user.getFirstName());
//        ModelAndView mv = new ModelAndView("admin/editUser");
//        return mv;
//
//    }

    @RequestMapping(value="editUser/{userName}", method=RequestMethod.GET)
    public ModelAndView getUserEditPage(@PathVariable("userName") String userName)
    {
        ModelAndView mv = new ModelAndView("admin/editUser");
        UserDetails user = userService.loadUserByUsername(userName);
        List<Role> roles = adminService.getAllRoles();

        mv.getModelMap().addAttribute("user", user );
        mv.getModelMap().addAttribute("roleList", roles );
        return mv;
    }


    /*
    This only returns a Admin page with incorrect URL and doesn't load all users.
    Need to figure out how to return the current page if form is incomplete
     */



    @RequestMapping(value="editUser/{userName}", method=RequestMethod.POST)
    public ModelAndView postUserEditPage(@ModelAttribute("user") User user, BindingResult result)
    {
        ModelAndView mv = new ModelAndView("redirect:/admin");

        if(user.getRole() != null )
        {
            adminService.updateUser(user);
            return mv;
        }

        return null;
    }
}
