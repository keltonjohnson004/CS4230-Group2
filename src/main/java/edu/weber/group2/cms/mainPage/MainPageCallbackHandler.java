package edu.weber.group2.cms.mainPage;

import edu.weber.group2.cms.blogPost.model.ReadBlog;
import edu.weber.group2.cms.blogPost.model.Tag;
import edu.weber.group2.cms.user.model.Permission;
import edu.weber.group2.cms.user.model.Role;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainPageCallbackHandler implements RowCallbackHandler {
    private List<ReadBlog> blogList = new ArrayList<>();

    @Override
    public void processRow(ResultSet rs) throws SQLException {

        try {
            do {

                ReadBlog readBlog = new ReadBlog();

                if(readBlog.getBlogID() == 0)
                {
                    readBlog.setBlogID(rs.getInt("ID"));
                }
                if (readBlog.getBlogName() == null){
                    readBlog.setBlogName(rs.getString("BlogTitle"));
                }
                if(readBlog.getBlogBody() == null)
                {
                    readBlog.setBlogBody(rs.getString("b.BlogBody"));
                }
               if(readBlog.getAuthorFirstName() == null)
               {
                   readBlog.setAuthorFirstName(rs.getString("u.FirstName"));
               }
               if(readBlog.getAuthorLastName() == null)
               {
                   readBlog.setAuthorLastName(rs.getString("u.LastName"));
               }
               if(readBlog.getPermissionID() == 0)
               {
                   readBlog.setPermissionID(rs.getInt("p.PermissionID"));
               }

               List<Integer> tags = new ArrayList<>();

               tags.add(rs.getInt("t.TagID"));
               if(tags != null)
               {
                   readBlog.setTags(tags);
               }



               blogList.add(readBlog);




            }
            while (rs.next());
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<ReadBlog> getblogList()
    {
        return blogList;
    }
}
