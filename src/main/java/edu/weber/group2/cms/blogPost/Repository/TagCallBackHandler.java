package edu.weber.group2.cms.blogPost.Repository;

import edu.weber.group2.cms.blogPost.model.Tag;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TagCallBackHandler implements RowCallbackHandler {
    private List<Tag> tagList = new ArrayList<>();

    @Override
    public void processRow(ResultSet rs) throws SQLException {

        try {
            do {

                Tag tag = new Tag();

                if (tag.getId() == 0) {
                    tag.setId(rs.getInt("ID"));
                }
                if (tag.getName() == null) {
                    tag.setName(rs.getString("TagName"));
                }


                tagList.add(tag);


            }
            while (rs.next());
        }
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<Tag> getTagList()
    {
        return tagList;
    }
}
