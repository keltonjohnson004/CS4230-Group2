package edu.weber.group2.cms.blogPost.Repository;

import edu.weber.group2.cms.blogPost.model.Blog;
import edu.weber.group2.cms.comments.CommentModel;
import edu.weber.group2.cms.comments.CommentCallbackHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BlogRepository {

    public String insertBlog = "INSERT INTO Blog" +
            "(BlogTitle," +
            "BlogBody, " +
            "AuthorID)" +
            " VALUES (" +
            ":BlogTitle, " +
            ":BlogBody, " +
            ":AuthorID)";


    public String getBlogByIDString = "SELECT ID, BlogTitle, BlogBody, AuthorID FROM Blog WHERE ID = :id";

    public String selectBlogID = "SELECT ID FROM Blog WHERE BlogTitle = :BlogTitle AND BlogBody = :BlogBody AND AuthorID = :AuthorID";
    @Autowired
    private HttpServletRequest request;

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public BlogRepository(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addBlog(Blog blog)
    {



        Map<String,Object> inputParameters = new HashMap();
        inputParameters.put("BlogTitle", blog.getBlogName());
        inputParameters.put("BlogBody", blog.getBlogBody());
        inputParameters.put("AuthorID", blog.getAuthorID());



        jdbcTemplate.update(insertBlog, inputParameters);



        String sql = selectBlogID;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("BlogTitle", blog.getBlogName());
        parameterSource.addValue("BlogBody", blog.getBlogBody());
        parameterSource.addValue("AuthorID", blog.getAuthorID());
        BlogCallbackHandler callbackHandler = new BlogCallbackHandler();
        jdbcTemplate.query(sql, parameterSource, callbackHandler);
        List<Integer> test = callbackHandler.getblogList();

        if(test.size() == 1)
        {
            return test.get(0);
        }

        return 0;
    }

    public Blog getBlogByID(String id)
    {
        String sql = getBlogByIDString;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", id);
        ReadBlogCallbackHandler callbackHandler = new ReadBlogCallbackHandler();
        jdbcTemplate.query(sql, parameterSource, callbackHandler);
        Blog blog = callbackHandler.getBlog();
        return blog;

    }

    private String updateString = "UPDATE Blog SET blogTitle = :blogName, blogBody = :blogBody WHERE ID = :blogID";

    public void updateBlog(Blog blog)
    {
        String sql = updateString;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("blogName", blog.getBlogName());
        parameterSource.addValue("blogBody", blog.getBlogBody());
        parameterSource.addValue("blogID", blog.getId());
        jdbcTemplate.update(sql, parameterSource);

    }

    private String getCommentsString = "SELECT ID, UserID, BlogID, CommentBody FROM Comment WHERE BlogID = :blogID ";

    public List<CommentModel> getComments(String blogID)
    {
        String sql = getCommentsString;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("blogID", blogID);
        CommentCallbackHandler callbackHandler = new CommentCallbackHandler();
        jdbcTemplate.query(sql, parameterSource, callbackHandler);
        return callbackHandler.getComments();
    }


    private String addCommentString = "INSERT INTO Comment (BlogID, commentBody, UserID) values (:blogID, :commentBody, :userID)";
    public void addComment(CommentModel comment)
    {
        String sql = addCommentString;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("blogID", comment.getBlogID());
        parameterSource.addValue("commentBody", comment.getCommentBody());
        parameterSource.addValue("userID", comment.getCommentorID());
        jdbcTemplate.update(sql, parameterSource);
    }
}
