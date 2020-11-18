package edu.weber.group2.cms.mainPage;

import edu.weber.group2.cms.blogPost.model.ReadBlog;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class MainPageRepository
{


    private final NamedParameterJdbcTemplate jdbcTemplate;

    public MainPageRepository(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }


    private String getAllBlogsString = "SELECT * FROM Blog AS b " +
            "LEFT JOIN UserInfo AS u ON b.AuthorID = u.ID " +
            "LEFT JOIN BlogToTag AS t ON t.BlogID = b.ID  " +
            "LEFT JOIN BlogToPermission as p ON p.BlogID = b.ID " ;



    public List<ReadBlog> getAllBlogs(String search)
    {
        String sql = getAllBlogsString;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        if(search!= null) {
            sql = sql + "WHERE lower(b.blogTitle) like lower(:blogName)";
           parameterSource.addValue("blogName",  "%" + search +"%");
        }

        MainPageCallbackHandler callBackHandler = new MainPageCallbackHandler();
        jdbcTemplate.query(sql, parameterSource, callBackHandler);
        return callBackHandler.getblogList();

    }

}
