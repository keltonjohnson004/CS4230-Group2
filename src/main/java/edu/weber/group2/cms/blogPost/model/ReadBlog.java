package edu.weber.group2.cms.blogPost.model;

import java.util.List;

public class ReadBlog {
    private int BlogID;

    public int getBlogID() {
        return BlogID;
    }

    public void setBlogID(int blogID) {
        BlogID = blogID;
    }

    private String authorFirstName;
    private String authorLastName;
    private int permissionID;
    private String blogName;
    private String blogBody;
    private List<Integer> tags;


    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogBody() {
        return blogBody;
    }

    public void setBlogBody(String blogBody) {
        this.blogBody = blogBody;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

    public int getPermissionID() {
        return permissionID;
    }

    public void setPermissionID(int permissionID) {
        this.permissionID = permissionID;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }
}
