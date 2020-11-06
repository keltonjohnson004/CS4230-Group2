package edu.weber.group2.cms.mainPage;

import edu.weber.group2.cms.blogPost.Repository.BlogRepository;
import edu.weber.group2.cms.blogPost.Repository.PermissionRepository;
import edu.weber.group2.cms.blogPost.Repository.TagRepository;
import edu.weber.group2.cms.blogPost.model.ReadBlog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainPageService {


    private final MainPageRepository mainPageRepository;

    public MainPageService(MainPageRepository _mainPageRepository)
    {
        mainPageRepository = _mainPageRepository;
    }


    public List<ReadBlog> getAllBlogs(String search)
    {
        return mainPageRepository.getAllBlogs(search);
    }

}
