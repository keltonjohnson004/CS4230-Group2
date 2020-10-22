package edu.weber.group2.cms.blogPost.Repository;

import edu.weber.group2.cms.blogPost.model.Tag;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TagRepository(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }


    private String getTagString = "SELECT * FROM Tag;";

    public List<Tag> getAllTags()
    {
        String sql = getTagString;
        TagCallBackHandler callBackHandler = new TagCallBackHandler();
        jdbcTemplate.query(sql, callBackHandler);
        return callBackHandler.getTagList();

    }
}
