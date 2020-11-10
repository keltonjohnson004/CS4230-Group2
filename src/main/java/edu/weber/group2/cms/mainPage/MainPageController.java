package edu.weber.group2.cms.mainPage;

import edu.weber.group2.cms.blogPost.BlogService;
import edu.weber.group2.cms.blogPost.model.Blog;
import edu.weber.group2.cms.blogPost.model.ReadBlog;
import edu.weber.group2.cms.blogPost.model.Tag;
import edu.weber.group2.cms.user.model.Permission;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.swagger.models.parameters.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class MainPageController {

    @Autowired
    private HttpServletRequest request;

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    private MainPageService mainPageService;

    @Autowired
    public MainPageController(MainPageService _mainPageService)
    {
        this.mainPageService = _mainPageService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView GetMainPage(Model model, @RequestParam(required = false) String query) {
        List<ReadBlog> blogList = mainPageService.getAllBlogs(query);

        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if ("sessionId".equalsIgnoreCase(cookie.getName())) {
                    Jws<Claims> jws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(cookie.getValue());

                    Claims claims = jws.getBody();

                    int authorID = Integer.valueOf(claims.get("perms", String.class));
                    //List<Permission> perms = List.copyOf(claims.get("perms", Permission.class));
                }
            }
        }
        for (ReadBlog blog: blogList)
        {
            if (blog.getBlogBody().length() >= 100)
            {
                blog.setBlogBody(blog.getBlogBody().substring(0, 300));
            }
        }
        ModelAndView mv = new ModelAndView("index");
        mv.getModelMap().addAttribute("blogList", blogList );
        return mv;
    }
}
