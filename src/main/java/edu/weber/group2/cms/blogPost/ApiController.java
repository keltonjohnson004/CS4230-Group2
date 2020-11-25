package edu.weber.group2.cms.blogPost;


import edu.weber.group2.cms.blogPost.model.Blog;
import edu.weber.group2.cms.blogPost.model.ReadBlog;
import edu.weber.group2.cms.mainPage.MainPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class ApiController {

    private BlogService blogService;

    @Autowired
    public ApiController(BlogService _blogService)
    {
        blogService = _blogService;
    }



    @RequestMapping(value="getBlogById", method=RequestMethod.GET)
    public Blog getBlog(@RequestParam("blogId") String blogId)
    {
        return blogService.getBlogByID(blogId);
    }

    @RequestMapping(value="deleteBlogById", method=RequestMethod.GET)
    public Blog deleteBlog(@RequestParam("blogId") String blogId)
    {
        return blogService.deleteBlogById(blogId);
    }

    @RequestMapping(value="createBlog", method=RequestMethod.GET)
    public Blog createBlog(@RequestParam("blogTitle") String blogTitle, @RequestParam("blogBody") String blogBody, @RequestParam("AuthorID") int authorID, @RequestParam("tag") String tag, @RequestParam("permission") String permission)
    {
        Blog blog = new Blog();
        blog.setAuthorID(authorID);
        blog.setBlogBody(blogBody);
        blog.setBlogName(blogTitle);
        if(!permission.equals(""))
        {
            blog.setPermission(permission);
        }
        else
        {
            blog.setPermission(null);
        }
        if(!tag.equals("")) {
            List<String> tags = new ArrayList<>();
            tags.add(tag);
        }
        else {
            blog.setTags(null);
        }
        blogService.addBlogPost(blog);
        return blog;
    }


    @RequestMapping(value="updateBlog", method=RequestMethod.GET)
    public Blog updateBlog(@RequestParam("blogId") int blogId, @RequestParam("blogTitle") String blogTitle, @RequestParam("blogBody") String blogBody, @RequestParam("AuthorID") int authorID)
    {
        Blog blog = new Blog();
        blog.setId(blogId);
        blog.setAuthorID(authorID);
        blog.setBlogBody(blogBody);
        blog.setBlogName(blogTitle);

        blogService.updateBlog(blog);
        return blog;
    }





}
