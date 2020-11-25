package edu.weber.group2.cms.mainPage;

import edu.weber.group2.cms.blogPost.BlogService;
import edu.weber.group2.cms.blogPost.model.Blog;
import edu.weber.group2.cms.blogPost.model.ReadBlog;
import edu.weber.group2.cms.blogPost.model.Tag;
import edu.weber.group2.cms.user.model.Permission;
import edu.weber.group2.cms.user.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.swagger.models.parameters.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainPageController {


    private MainPageService mainPageService;
    private BlogService blogService;

    @Autowired
    public MainPageController(MainPageService _mainPageService,BlogService blogService)
    {
        this.mainPageService = _mainPageService;
        this.blogService = blogService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView GetMainPage(Model model, @RequestParam(required = false) String query,
                                    @RequestParam(required = false) String tag,
                                    @RequestParam(defaultValue = "0") Integer pageNo,
                                    @RequestParam(defaultValue = "10") Integer pageSize, Principal principal) {


        List<ReadBlog> blogList = new ArrayList<ReadBlog>();
        if(principal == null)
        {
            blogList = mainPageService.getAllBlogs(query,tag, pageNo, pageSize);
        }
        else {
            blogList = mainPageService.getAllBlogs(query,tag, pageNo, pageSize, principal);
        }
        ModelAndView mv = new ModelAndView("index");
        mv.getModelMap().addAttribute("blogList", blogList );
<<<<<<< HEAD
        mv.getModelMap().addAttribute("page_title", "home page");
=======
        List<Tag> tagList = blogService.getAllTags();
        mv.getModelMap().addAttribute("tagList", tagList );
>>>>>>> 1472332ed8c4b1886281e40477911bfce04454f8
        return mv;
    }
}
