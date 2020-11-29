package edu.weber.group2.cms.mainPage;

import edu.weber.group2.cms.blogPost.Repository.TagRepository;
import edu.weber.group2.cms.blogPost.model.ReadBlog;
import edu.weber.group2.cms.blogPost.model.Tag;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class MainPageRepository
{


    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final TagRepository tagRepository;

    public MainPageRepository(NamedParameterJdbcTemplate jdbcTemplate, TagRepository _tagRepository)
    {
        this.jdbcTemplate = jdbcTemplate;
        tagRepository = _tagRepository;
    }


    private String getAllBlogsString = "SELECT * FROM Blog AS b " +
            "LEFT JOIN UserInfo AS u ON b.AuthorID = u.ID " +
            "LEFT JOIN BlogToTag AS t ON t.BlogID = b.ID  " +
            "LEFT JOIN BlogToPermission as p ON p.BlogID = b.ID WHERE 1=1 " ;



    public List<ReadBlog> getAllBlogs(String search, String tag,int pageNo, int pageSize)
    {
        String sql = getAllBlogsString;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if(search!= null) {
            sql = sql + "AND lower(b.blogTitle) like lower(:blogName)";
           parameterSource.addValue("blogName",  "%" + search +"%");
        }
        if(tag != null)
        {
            List<Tag> allTags = tagRepository.getAllTags();
            int tagId = 0;
            for(Tag currTag: allTags)
            {
                if(currTag.getName().equals(tag))
                {
                    tagId = currTag.getId();
                    break;
                }
            }
            sql = sql + "AND t.tagID = :tag";
            parameterSource.addValue("tag", tagId);
        }


        int offset = pageNo * pageSize;
        sql = sql + " LIMIT " + pageSize + " OFFSET " + offset;

        MainPageCallbackHandler callBackHandler = new MainPageCallbackHandler();
        jdbcTemplate.query(sql, parameterSource, callBackHandler);
        return callBackHandler.getblogList();

    }

}
