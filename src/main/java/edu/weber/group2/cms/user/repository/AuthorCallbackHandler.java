package edu.weber.group2.cms.user.repository;

import edu.weber.group2.cms.user.model.User;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorCallbackHandler implements RowCallbackHandler {
    private User author = new User();

    @Override
    public void processRow(ResultSet rs) throws SQLException {
        User readAuthor = new User();
        if(readAuthor.getFirstName() == null)
        {
            readAuthor.setFirstName(rs.getString("FirstName"));
        }
        if(readAuthor.getLastName()== null)
        {
            readAuthor.setLastName(rs.getString("LastName"));
        }
        if(readAuthor.getUserName() == null)
        {
            readAuthor.setUserName(rs.getString("UserName"));
        }
        author = readAuthor;
    }

    public User getAuthor()
    {
        return author;
    }

}
