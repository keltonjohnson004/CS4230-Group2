package edu.weber.group2.cms.blogPost.Repository;

import edu.weber.group2.cms.blogPost.model.Blog;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BlogCallbackHandler implements RowCallbackHandler {
    private List<Integer> blogList = new ArrayList<>();

    @Override
    public void processRow(ResultSet rs) throws SQLException {

        try {
            do {

                int blogID = 0;

                if (blogID == 0) {
                    blogID = rs.getInt("ID");
                }

                blogList.add(blogID);


            }
            while (rs.next());
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Integer> getblogList()
    {
        return blogList;
    }
}
