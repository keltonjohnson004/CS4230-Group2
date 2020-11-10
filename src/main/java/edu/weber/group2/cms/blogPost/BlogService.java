package edu.weber.group2.cms.blogPost;

import edu.weber.group2.cms.blogPost.Repository.BlogRepository;
import edu.weber.group2.cms.blogPost.Repository.PermissionRepository;
import edu.weber.group2.cms.blogPost.Repository.TagRepository;
import edu.weber.group2.cms.blogPost.model.Blog;
import edu.weber.group2.cms.blogPost.model.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

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


    public void addBlogPost(Blog blog)
    {
        blog.setId(blogRepository.addBlog(blog));
        tagRepository.addTag(blog);
        permissionRepository.addPermission(blog);
    }

    public Blog getBlogByID(String id)
    {
        return blogRepository.getBlogByID(id);
    }


}
