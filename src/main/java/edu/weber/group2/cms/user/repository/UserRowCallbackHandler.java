package edu.weber.group2.cms.user.repository;

import edu.weber.group2.cms.user.model.Permission;
import edu.weber.group2.cms.user.model.Role;
import edu.weber.group2.cms.user.model.User;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class UserRowCallbackHandler implements RowCallbackHandler
{

    private User user;
    private boolean processedLocked = false;
    private boolean processedEnabled = false;
    private boolean processedCredentialsExpired = false;
    private boolean processedExpiredOn = false;


    @Override
    public void processRow(ResultSet rs) throws SQLException {
        if(user == null)
        {
            user = new User();
        }
        if(user.getId() == 0)
        {
            user.setId(rs.getInt("UserID"));
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
        if(user.getPassword() == null)
        {
            user.setPassword(rs.getString("Password"));
        }
        if(!processedLocked)
        {
            user.setLocked(rs.getBoolean("Locked"));
            processedLocked = true;
        }
        if(!processedEnabled) {
            user.setEnabled(rs.getBoolean("Enabled"));
            processedEnabled = true;
        }
        if(user.getCredentialExpiredOn() == null && !processedCredentialsExpired)
        {
            user.setCredentialExpiredOn(createZonedDateTime(rs, "CredentialsExpireOn"));
            processedCredentialsExpired = true;
        }
        if(user.getExpiredOn() == null && !processedExpiredOn)
        {
            user.setExpiredOn(createZonedDateTime(rs, "ExpiredOn"));
            processedExpiredOn = true;
        }
        if(user.getCreatedOn() == null)
        {
            user.setCreatedOn(createZonedDateTime(rs, "CreatedOn"));
        }
        if(user.getExpiredOn() == null)
        {
            user.setExpiredOn(createZonedDateTime(rs,"ExpiredOn"));
        }

        int roleID = rs.getInt("RoldID");
        int permissionID = rs.getInt("PermissionID");
        Role currentRole = user.getRoles().get(roleID);

        if(currentRole == null && roleID  != 0)
        {
            currentRole = new Role();
            currentRole.setId(roleID);
            currentRole.setName(rs.getString("RoleName"));
            user.getRoles().put(roleID, currentRole);
        }

        if(currentRole != null)
        {
            Permission currentPermission = currentRole.getPermissionById(permissionID);

            if(currentPermission == null)
            {
                currentPermission = new Permission();
                currentPermission.setId(permissionID);
                currentPermission.setName(rs.getString("PermissionName"));
                currentRole.getPermissions().put(permissionID, currentPermission);
            }

        }

    }

    private ZonedDateTime createZonedDateTime(ResultSet rs, String key) throws SQLException
    {
        Timestamp value = rs.getTimestamp(key);
        if(value!= null)
        {
            return ZonedDateTime.ofInstant(rs.getTimestamp(key).toInstant(), ZoneId.of("UTC"));
        }
        return null;
    }

    public User getUser() {
        if(user == null)
        {
            user = new User();
        }
        return user;
    }

}
