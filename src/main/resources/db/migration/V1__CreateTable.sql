CREATE TABLE UserInfo(
    UserID int identity(1,1) NOT NULL primary key,
    FirstName varchar(50) NOT NULL,
    LastName varchar(50) NOT NULL,
    UserName varchar(50) NOT NULL,
    Password varchar(128) NOT NULL,
    Locked bit NOT NULL,
    Enabled bit NOT NULL,
    CredentialExpiredOn datetime NOT NULL,
    ExpiredOn datetime NOT NULL,
    CreatedOn datetime NOT NULL,
    ModifiedOn datetime NOT NULL
) ENGINE=INNODB

CREATE TABLE Role
(
    RoleID int identity(1,1) NOT NULL primary KEY,
    RoleName varchar(50) NOT NULL
) ENGINE=INNODB

CREATE TABLE UserToRole
(
    UserToRoleID int identity(1,1) NOT NULL primary key,
    UserID int NOT NULL,
    RoleID int NOT NULL,
    FOREIGN KEY (UserID) REFERENCES UserInfo(UserID),
    FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
) ENGINE=INNODB

CREATE TABLE Permission
(
    PermissionID int identity(1,1) NOT NULL PRIMARY KEY,
    PermissionName varchar(50) NOT NULL
) ENGINE=INNODB

CREATE TABLE RoleToPermission
(
    RoleToPermissionID int identity(1,1) NOT NULL PRIMARY KEY,
    RoleID int NOT NULL,
    PermissionID INT NOT NULL,
    FOREIGN KEY (RoleID) REFERENCES Role(RoleID),
    FOREIGN KEY (PermissionID) REFERENCES Permission(PermissionID)
) ENGINE=INNODB

CREATE TABLE BlogPost
(
    BlogPostID int identity(1,1) NOT NULL PRIMARY KEY,
    BlogTitle varchar(200) NOT NULL,
    BlogBody longtext NOT NULL,
    CreatedOn datetime NOT NULL,
    UpdatedOn datetime NOT NULL,
    AuthorID int NOT NULL,
    FOREIGN KEY (AuthorID) REFERENCES UserInfo(UserID)
) ENGINE=INNODB

CREATE TABLE BlogPostToPermission
(
    BlogPostToPermissionID int identity(1,1) NOT NULL PRIMARY KEY,
    BlogPostID int NOT NULL,
    PermissionID int NOT NULL,
    FOREIGN KEY (BlogPostID) REFERENCES BlogPost(BlogPostID),
    FOREIGN KEY (PermissionID) REFERENCES Permission(PermissionID)
) ENGINE=INNODB

CREATE TABLE Tag
(
    TagID int identity(1,1) NOT NULL PRIMARY KEY,
    TagName varchar(50)
) ENGINE=INNODB

CREATE TABLE BlogPostToTag
(
    BlogPostToTagID int identity(1,1) NOT NULL PRIMARY KEY,
    BlogID int NOT NULL,
    TagID int NOT NULL,
    FOREIGN KEY (BlogID) REFERENCES BlogPost(BlogID),
    FOREIGN KEY (TagID) REFERENCES Tag(TagID)
) ENGINE=INNODB

CREATE TABLE Comment
(
    CommentID int identity(1,1) NOT NULL PRIMARY KEY,
    BlogID int NOT NULL,
    CommentBody mediumtext NOT NULL,
    UserID int NOT NULL,
    CreatedOn datetime NOT NULL,
    FOREIGN KEY (BlogID) REFERENCES BlogPost(BlogID),
    FOREIGN KEY (UserID) REFERENCES UserInfo(UserID)
) ENGINE=INNODB