package edu.weber.group2.cms.blogPost.Repository;

import edu.weber.group2.cms.blogPost.model.Blog;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadBlogCallbackHandler implements RowCallbackHandler {
    Blog blog = new Blog();

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        Blog readBlog = new Blog();

        if (readBlog.getBlogName() == null)
        {
            readBlog.setBlogName(rs.getString("BlogTitle"));
        }
        if(readBlog.getId() == 0)
        {
            readBlog.setId(rs.getInt("ID"));
        }
        if(readBlog.getBlogBody() == null)
        {
            readBlog.setBlogBody(rs.getString("BlogBody"));
        }
        if(readBlog.getAuthorID() == 0)
        {
            readBlog.setAuthorID(rs.getInt("AuthorID"));
        }

        blog = readBlog;
    }

    public Blog getBlog()
    {
        return blog;
    }
}
