package edu.weber.group2.cms.comments;

import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentCallbackHandler implements RowCallbackHandler {

    List<CommentModel> comments = new ArrayList<>();

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        CommentModel comment = new CommentModel();

        if(comment.getCommentID() == 0)
        {
            comment.setCommentID(rs.getInt("ID"));
        }
        if(comment.getBlogID() == 0)
        {
            comment.setBlogID(rs.getInt("BlogID"));
        }
        if(comment.getCommentBody() == null)
        {
            comment.setCommentBody(rs.getString("CommentBody"));
        }
        if(comment.getCommentorID() == 0)
        {
            comment.setCommentorID(rs.getInt("UserID"));
        }

        comments.add(comment);
    }

    public List<CommentModel> getComments()
    {
        return comments;
    }

}
