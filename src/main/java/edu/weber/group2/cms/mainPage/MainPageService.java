package edu.weber.group2.cms.mainPage;

import edu.weber.group2.cms.blogPost.model.ReadBlog;
import edu.weber.group2.cms.user.model.Permission;
import edu.weber.group2.cms.user.model.Role;
import edu.weber.group2.cms.user.model.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MainPageService {

    private final MainPageRepository mainPageRepository;

    public MainPageService(MainPageRepository _mainPageRepository)
    {
        mainPageRepository = _mainPageRepository;
    }


    public List<ReadBlog> getAllBlogs(String search, String tag, int pageNo, int pageSize, Principal principal)
    {

        User user = ((User)((UsernamePasswordAuthenticationToken) principal).getPrincipal());
        List<ReadBlog> blogList = mainPageRepository.getAllBlogs(search,tag);
        List<ReadBlog> returnBlogList = new ArrayList<>();
        List<Integer> permIds = new ArrayList<>();

        for(Role role:user.getRoles().values())
        {
            for(Permission perm :role.getPermissions().values())
            {
                permIds.add(perm.getId());
            }
        }

        for (ReadBlog blog: blogList)
        {
            boolean shouldAdd = false;
            if(blog.getPermissionID() == 0)
            {
                shouldAdd = true;
            }
            else {
                for (int id : permIds) {
                    if(id == blog.getPermissionID())
                    {
                        shouldAdd = true;
                        break;
                    }
                }
            }
            if(shouldAdd) {
                if (blog.getBlogBody().length() >= 300) {
                    blog.setBlogBody(blog.getBlogBody().substring(0, 300));
                }
                returnBlogList.add(blog);
            }
        }
        return returnBlogList;
    }
    public List<ReadBlog> getAllBlogs(String search, String tag, int pageNo, int pageSize)
    {

        List<ReadBlog> blogList = mainPageRepository.getAllBlogs(search, tag);
        List<ReadBlog> returnBlogList = new ArrayList<>();
        for (ReadBlog blog: blogList)
        {
            if(blog.getPermissionID() == 0) {
                if (blog.getBlogBody().length() >= 300) {
                    blog.setBlogBody(blog.getBlogBody().substring(0, 300));
                }
                returnBlogList.add(blog);
            }
        }
        return returnBlogList;
    }


}
