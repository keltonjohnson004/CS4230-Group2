package edu.weber.group2.cms.blogPost.Repository;

import edu.weber.group2.cms.blogPost.model.Blog;
import edu.weber.group2.cms.user.repository.UserRowCallbackHandler;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpHeaders;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.Cookie;
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

        Cookie[] cookies = request.getCookies();

        for(Cookie cookie: cookies)
        {
            if("sessionId".equalsIgnoreCase(cookie.getName()))
            {
                Jws<Claims> jws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(cookie.getValue());

                Claims claims = jws.getBody();

                int authorID = Integer.valueOf(claims.get("sub",String.class));
                blog.setAuthorID(authorID);
            }
        }



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
}
