package edu.weber.group2.cms.user;

import java.time.ZonedDateTime;

public class Contact {
    private int Id;
    private String FirstName;
    private String LastName;
    private String UserName;
    private String Password;
    private boolean locked;
    private boolean enabled;
    private ZonedDateTime CredentialExpiredOn;
    private ZonedDateTime ExpiredOn;
    private ZonedDateTime CreatedOn;

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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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

    private ZonedDateTime ModifiedOn;

}
