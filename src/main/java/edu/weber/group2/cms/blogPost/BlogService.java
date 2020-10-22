package edu.weber.group2.cms.blogPost;

import edu.weber.group2.cms.blogPost.Repository.TagRepository;
import edu.weber.group2.cms.blogPost.model.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private final TagRepository tagRepository;

    public BlogService(TagRepository _tagRepository)
    {
        tagRepository = _tagRepository;
    }

    public List<Tag> getAllTags()
    {
        return tagRepository.getAllTags();
    }
}
