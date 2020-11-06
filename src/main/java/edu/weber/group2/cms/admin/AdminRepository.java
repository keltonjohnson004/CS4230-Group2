package edu.weber.group2.cms.admin;

import edu.weber.group2.cms.user.model.Role;
import edu.weber.group2.cms.user.model.User;
import edu.weber.group2.cms.user.repository.UserRowCallbackHandler;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AdminRepository {

    private final String getAllUserString = "SELECT ID, FirstName, LastName, UserName FROM UserInfo WHERE Locked = 0";
    private final String getAllRoleString = "SELECT ID, RoleName FROM Role";
    private final String updateUserString = "UPDATE UserToRole SET RoleID = :roleId WHERE UserID = :userId";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AdminRepository(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getAllUsers()
    {
        String sql = getAllUserString;
        //MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        AdminCallbackHandler callbackHandler = new AdminCallbackHandler();
        jdbcTemplate.query(sql, callbackHandler);
        return callbackHandler.getUserList();
    }

    public List<Role> getAllRoles()
    {
        String sql = getAllRoleString;
        //MapSqlParameterSource paramaterSource= new MapSqlParameterSource();
        RoleCallbackHandler callbackHandler = new RoleCallbackHandler();
        jdbcTemplate.query(sql,callbackHandler);
        return callbackHandler.getRoles();

    }

    public void updateUser(User user)
    {
        Map<String,Object> parameters = new HashMap();
        parameters.put("roleId", user.getRole());
        parameters.put("userId", user.getId());



        jdbcTemplate.update(updateUserString, parameters);
    }
}
