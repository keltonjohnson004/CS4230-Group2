package edu.weber.group2.cms.admin;

import edu.weber.group2.cms.user.model.Permission;
import edu.weber.group2.cms.user.model.Role;
import edu.weber.group2.cms.user.model.User;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminCallbackHandler implements RowCallbackHandler {

    private List<User> userList = new ArrayList<>();

    @Override
    public void processRow(ResultSet rs) throws SQLException {

            User user = new User();

            if(user == null)
            {
                user = new User();
            }

            if(user.getId() == 0)
            {
                user.setId(rs.getInt("ID"));
            }
            if(user.getFirstName() == null)
            {
                user.setFirstName(rs.getString("FirstName"));
            }
            if(user.getLastName() == null)
            {
                user.setLastName(rs.getString("LastName"));
            }
            if(user.getUserName() == null)
            {
                user.setUserName(rs.getString("UserName"));
            }

            userList.add(user);

    }

    public List<User> getUserList(){
        return userList;
    }
}
