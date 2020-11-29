package edu.weber.group2.cms.blogPost;

import edu.weber.group2.cms.blogPost.Repository.BlogRepository;
import edu.weber.group2.cms.blogPost.Repository.PermissionRepository;
import edu.weber.group2.cms.blogPost.Repository.TagRepository;
import edu.weber.group2.cms.blogPost.model.Blog;
import edu.weber.group2.cms.blogPost.model.Tag;
import edu.weber.group2.cms.comments.CommentModel;
import edu.weber.group2.cms.user.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class BlogService {

    private final TagRepository tagRepository;
    private final BlogRepository blogRepository;
    private final PermissionRepository permissionRepository;

    public BlogService(TagRepository _tagRepository, BlogRepository _blogRepository, PermissionRepository _permissionRepository)
    {
        tagRepository = _tagRepository;
        blogRepository = _blogRepository;
        permissionRepository = _permissionRepository;
    }



    public List<Tag> getAllTags()
    {
        return tagRepository.getAllTags();
    }


    public void addBlogPost(Blog blog, Principal principal) {
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        blog.setAuthorID(user.getId());
        blog.setId(blogRepository.addBlog(blog));
        if (blog.getTags() != null) {
            tagRepository.addTag(blog);
        }
        if (blog.getPermission() != null) {
            permissionRepository.addPermission(blog);
        }
    }

    public void addBlogPost(Blog blog)
    {
        int storeId = blogRepository.addBlog(blog);
        blog.setId(storeId);
        tagRepository.addTag(blog);
        permissionRepository.addPermission(blog);
    }

    public Blog getBlogByID(String id)
    {
        return blogRepository.getBlogByID(id);
    }


    public void updateBlog(Blog blog)
    {
        blogRepository.updateBlog(blog);
    }

    public List<CommentModel> getComments(String blogID)
    {
        return blogRepository.getComments(blogID);
    }


    public void addComment(CommentModel comment)
    {
        blogRepository.addComment(comment);
    }

    public Blog deleteBlogById(String id)
    {
        return blogRepository.deleteBlogByID(id);
    }
}
