package edu.weber.group2.cms.user.repository;

import edu.weber.group2.cms.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String USER_ID_KEY = "id";
    private static final String USER_ID_BINDING_KEY = ":" + USER_ID_KEY;
    private static final String USERNAME_KEY = "username";
    private static final String USERNAME_BINDING_KEY = ":" + USERNAME_KEY;
    private static final String FIRST_NAME_KEY = "first_name";
    private static final String FIRST_NAME_BINDING_KEY = ":" + FIRST_NAME_KEY;
    private static final String LAST_NAME_KEY = "last_name";
    private static final String LAST_NAME_BINDING_KEY = ":" + LAST_NAME_KEY;
    private static final String PASSWORD_KEY = "password";
    private static final String PASSWORD_BINDING_KEY = ":" + PASSWORD_KEY;
    private static final String LOCKED_KEY = "locked";
    private static final String LOCKED_BINDING_KEY = ":" + LOCKED_KEY;
    private static final String ENABLED_KEY = "enabled";
    private static final String ENABLED_BINDING_KEY = ":" + ENABLED_KEY;
    private static final String CREDS_EXPIRE_ON_KEY = "creds_expire_on";
    private static final String CREDS_EXPIRE_ON_BINDING_KEY = ":" + CREDS_EXPIRE_ON_KEY;
    private static final String EXPIRED_ON_KEY = "expired_on";
    private static final String EXPIRED_ON_BINDING_KEY = ":" + EXPIRED_ON_KEY;
    private static final String CREATED_ON_KEY = "created_on";
    private static final String CREATED_ON_BINDIND_KEY = ":" + CREATED_ON_KEY;
    private static final String MODIFIED_ON_KEY = "modified_on";
    private static final String MODIFIED_ON_BINDING_KEY = ":" + MODIFIED_ON_KEY;

    private static final List<String> primaryKeys = new ArrayList<>();

    static{
        primaryKeys.add(USER_ID_KEY);
        primaryKeys.add(USER_ID_BINDING_KEY);
    }

    private static final String SELECT_USER_WITH_ROLES_SQL = "SELECT u.ID as userID,"
            + "u.FirstName,"
            + "u.LastName,"
            + "u.UserName,"
            + "u.Password,"
            + "u.Locked,"
            + "u.Enabled,"
            + "u.CredentialExpiredOn,"
            + "u.ExpiredOn,"
            + "u.CreatedOn,"
            + "u.ModifiedOn,"
            + "r.ID as RoleID,"
            + "r.RoleName,"
            + "p.ID as PermissionID,"
            + "p.PermissionName"
            + "FROM UserInfo u"
            + "LEFT JOIN UserToRole ur ON ur.UserID = u.ID"
            + "LEFT JOIN ROLE r on ur.RoleID = r.ID"
            + "LEFT JOIN RoleToPermission rp ON r.ID = rp.RoleID"
            + "LEFT JOIN Permission p ON p.ID = rp.PermissionID"
            + "WHERE 1=1";

    private static final String WHERE_USERNAME_EQUALS_USERNAME = "u.UserName = " + USERNAME_BINDING_KEY ;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public User getUserByUserName(String username)
    {
        String sql = SELECT_USER_WITH_ROLES_SQL + " AND " + WHERE_USERNAME_EQUALS_USERNAME;
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue(USERNAME_KEY, username);
        UserRowCallbackHandler callbackHandler = new UserRowCallbackHandler();
        jdbcTemplate.query(sql, parameterSource, callbackHandler);
        return callbackHandler.getUser();
    }

    @Autowired
    public UserRepository(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }



}
