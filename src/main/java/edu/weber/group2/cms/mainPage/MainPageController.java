package edu.weber.group2.cms.mainPage;

import edu.weber.group2.cms.blogPost.BlogService;
import edu.weber.group2.cms.blogPost.model.Blog;
import edu.weber.group2.cms.blogPost.model.ReadBlog;
import edu.weber.group2.cms.blogPost.model.Tag;
import io.swagger.models.parameters.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainPageController {


    private MainPageService mainPageService;

    @Autowired
    public MainPageController(MainPageService _mainPageService)
    {
        this.mainPageService = _mainPageService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView GetMainPage(Model model, @RequestParam(required = false) String query) {
        List<ReadBlog> blogList = mainPageService.getAllBlogs(query);



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
