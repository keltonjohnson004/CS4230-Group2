package edu.weber.group2.cms.blogPost;

import edu.weber.group2.cms.blogPost.model.Blog;
import edu.weber.group2.cms.blogPost.model.Tag;
import edu.weber.group2.cms.comments.CommentModel;
import edu.weber.group2.cms.user.UserService;
import edu.weber.group2.cms.user.model.Permission;
import edu.weber.group2.cms.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("blog")
public class BlogController {


    private BlogService blogService;
    private UserService userService;

    @Autowired
    public BlogController(BlogService _blogService, UserService _userService)
    {
        this.blogService = _blogService;
        this.userService = _userService;
    }


    @RequestMapping(value = "postBlog", method = RequestMethod.GET)
    public ModelAndView GetBlogPost(Model model) {
        List<Tag> tagList = blogService.getAllTags();
        ModelAndView mv = new ModelAndView("blog/postBlog");
        mv.getModelMap().addAttribute("tagList", tagList );
        return mv;
    }

    @RequestMapping(value="postBlog", method= RequestMethod.POST)
    public ModelAndView PostBlogPost(@ModelAttribute("NewBlogPost") Blog blog, BindingResult result, Principal principal)
    {
        blogService.addBlogPost(blog, principal);
        ModelAndView mv = new ModelAndView("blog/blog");
        return mv;
    }

    @RequestMapping(value="blog", method = RequestMethod.GET)
    public ModelAndView GetBlog(Model model)
    {
        ModelAndView mv = new ModelAndView("blog/blog");
        return mv;
    }
    
    @RequestMapping(value="create", method = RequestMethod.GET)
    public ModelAndView GetCreatePost(Model model) {
    	ModelAndView mv = new ModelAndView("blog/create_post");
    	return mv;
    }

    @RequestMapping(value="readBlog/{blogID}", method=RequestMethod.GET)
    public ModelAndView GetReadBlog(@PathVariable("blogID") String blogID, Principal principal)
    {
        ModelAndView mv = new ModelAndView("blog/readBlog");
        Blog blog = blogService.getBlogByID(blogID);
        User author = userService.getUserByID(blog.getAuthorID());
        mv.getModelMap().addAttribute("blog", blog );
        mv.getModelMap().addAttribute("author", author );
        User user;
        if(principal == null)
        {
            user = new User();
        }
        else {
            user = ((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
        }
        mv.getModelMap().addAttribute("user", user);
        boolean userIsAuthor = false;
        if(user.getId() == author.getId())
        {
            userIsAuthor = true;
        }

        mv.getModelMap().addAttribute("userIsAuthor", userIsAuthor);

        boolean userCanComment = false;
        if(user.getId() != 0)
        {
            for(Permission permission : user.getPermissions())
            {
                if(permission.getId() == 1)
                {
                    userCanComment = true;
                    break;
                }
            }
        }
        mv.getModelMap().addAttribute("userCanComment", userCanComment);

        List<CommentModel> comments = blogService.getComments(blogID);

        mv.getModelMap().addAttribute("comments", comments);

        return mv;
    }

    @RequestMapping(value="readBlog/{blogID}", method=RequestMethod.POST)
    public String postReadBlog(@ModelAttribute("NewComment") CommentModel comment, BindingResult result)
    {
       // ModelAndView mv = new ModelAndView("blog/readBlog/" + comment.getBlogID());
        blogService.addComment(comment);
        //mv.getModelMap().addAttribute()
        return "redirect:/blog/readBlog/" + comment.getBlogID();

    }


    @RequestMapping(value="editBlog/{blogID}", method=RequestMethod.GET)
    public ModelAndView GetEditBlog(@PathVariable("blogID") String blogID, Principal principal)
    {
        ModelAndView mv = new ModelAndView("blog/editBlog");
        Blog blog = blogService.getBlogByID(blogID);

        mv.getModelMap().addAttribute("blog", blog );

        return mv;
    }

    @RequestMapping(value="editBlog/*", method=RequestMethod.POST)
    public String GetEditBlog(@ModelAttribute("NewBlogPost") Blog blog, BindingResult result)
    {
        blogService.updateBlog(blog);
        return "redirect:/";
    }




}
