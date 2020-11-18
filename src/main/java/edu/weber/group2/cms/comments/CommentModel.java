package edu.weber.group2.cms.comments;

public class CommentModel {
    private int commentID;
    private String commentBody;
    private int commentorID;
    private int blogID;

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public int getCommentorID() {
        return commentorID;
    }

    public void setCommentorID(int commentorID) {
        this.commentorID = commentorID;
    }

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }
}
