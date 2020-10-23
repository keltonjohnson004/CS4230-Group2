package edu.weber.group2.cms.blogPost;

import edu.weber.group2.cms.blogPost.model.Tag;
import edu.weber.group2.cms.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("blog")
public class BlogController {


    private BlogService blogService;

    @Autowired
    public BlogController(BlogService _blogService)
    {
        this.blogService = _blogService;
    }



    @RequestMapping(value = "postBlog", method = RequestMethod.GET)
    public ModelAndView GetBlogPost(Model model) {
        List<Tag> tagList = blogService.getAllTags();
        ModelAndView mv = new ModelAndView("blog/postBlog");
        mv.getModelMap().addAttribute("tagList", tagList );
        return mv;
    }


    @RequestMapping(value="blog", method = RequestMethod.GET)
    public ModelAndView GetBlog(Model model)
    {
        ModelAndView mv = new ModelAndView("blog/blog");
        return mv;
    }
}
