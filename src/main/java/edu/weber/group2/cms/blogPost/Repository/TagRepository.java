package edu.weber.group2.cms.blogPost.Repository;

import edu.weber.group2.cms.blogPost.model.Blog;
import edu.weber.group2.cms.blogPost.model.Tag;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TagRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TagRepository(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }


    private String getTagString = "SELECT * FROM Tag;";

    private String insertTagString = "INSERT INTO BlogToTag (BlogID, TagID) VALUES (:BlogID, :TagID)";


    public List<Tag> getAllTags()
    {
        String sql = getTagString;
        TagCallBackHandler callBackHandler = new TagCallBackHandler();
        jdbcTemplate.query(sql, callBackHandler);
        return callBackHandler.getTagList();
    }

    public void addTag(Blog blog)
    {
        String sql = insertTagString;
        for(String tagName: blog.getTags())
        {
            for(Tag tag: getAllTags())
            {
                if(tagName.equalsIgnoreCase(tag.getName()))
                {
                    Map<String, Object> parameters = new HashMap();
                    parameters.put("BlogID", blog.getId());
                    parameters.put("TagID", tag.getId());
                    jdbcTemplate.update(sql, parameters);
                    break;
                }
            }
        }

    }


}
