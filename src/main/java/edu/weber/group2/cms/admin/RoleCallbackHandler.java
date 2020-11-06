package edu.weber.group2.cms.admin;

import edu.weber.group2.cms.user.model.Role;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleCallbackHandler implements RowCallbackHandler {
    List<Role> roles;


    @Override
    public void processRow(ResultSet rs) throws SQLException {
        Role role = new Role();
        if(roles == null)
        {
            roles = new ArrayList<>();
        }
        if(role.getId() == 0)
        {
            role.setId(rs.getInt("ID"));
        }
        if(role.getName() == null)
        {
            role.setName(rs.getString("RoleName"));
        }
        roles.add(role);

    }

    public List<Role> getRoles()
    {
        return roles;
    }
}
