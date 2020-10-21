package edu.weber.group2.cms.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullFields;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Permissions;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;
public class User implements UserDetails {
    private int Id;
    private String FirstName;
    private String LastName;
    private String UserName;
    @JsonIgnore
    private String Password;
    private boolean Locked;
    private boolean Enabled;
    private ZonedDateTime CredentialExpiredOn;
    private ZonedDateTime ExpiredOn;
    private ZonedDateTime CreatedOn;
    private ZonedDateTime ModifiedOn;

    private Map<Integer, Role> roles;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());
    }

    public Set<Permission> getPermissions()
    {
        return Optional.ofNullable(this.roles)
                .orElseGet(HashMap::new)
                .values()
                .stream()
                .filter(role->role.getPermissions() != null && role.getPermissions().size() > 0)
                .flatMap(role->role.getPermissions().values().stream())
                .filter(p->p!= null && p.getName() != null && p.getId() != 0)
                .collect(Collectors.toSet());
    }

    public String getPassword() {
        return Password;
    }

    @Override
    public String getUsername() {
        return UserName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return ExpiredOn == null;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !Locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        if(CredentialExpiredOn != null)
        {
            return ZonedDateTime.now().isAfter(CredentialExpiredOn);
        }
        else
        {
            return true;
        }
    }

    @Override
    public boolean isEnabled()
    {
        return Enabled;
    }



    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }


    public Map<Integer, Role> getRoles()
    {
        if(this.roles == null)
        {
            this.roles =  new HashMap<>();
        }
        return roles;
    }

    public void setRoles(Map<Integer, Role> roles) {
        this.roles = roles;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isLocked() {
        return Locked;
    }


    public void setLocked(boolean locked) {
        this.Locked = locked;
    }

    public void setEnabled(boolean enabled) {
        this.Enabled = enabled;
    }

    public ZonedDateTime getCredentialExpiredOn() {
        return CredentialExpiredOn;
    }

    public void setCredentialExpiredOn(ZonedDateTime credentialExpiredOn) {
        CredentialExpiredOn = credentialExpiredOn;
    }

    public ZonedDateTime getExpiredOn() {
        return ExpiredOn;
    }

    public void setExpiredOn(ZonedDateTime expiredOn) {
        ExpiredOn = expiredOn;
    }

    public ZonedDateTime getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(ZonedDateTime createdOn) {
        CreatedOn = createdOn;
    }

    public ZonedDateTime getModifiedOn() {
        return ModifiedOn;
    }

    public void setModifiedOn(ZonedDateTime modifiedOn) {
        ModifiedOn = modifiedOn;
    }



}
