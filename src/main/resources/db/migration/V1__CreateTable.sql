CREATE TABLE UserInfo(
    ID INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    UserName VARCHAR(50) NOT NULL,
    Password VARCHAR(128) NOT NULL,
    Locked BIT NOT NULL,
    Enabled BIT NOT NULL,
    CredentialExpiredOn DATETIME NULL,
    ExpiredOn DATETIME NULL,
    CreatedOn DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ModifiedOn DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=INNODB;

CREATE TABLE Role
(
    ID INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    RoleName VARCHAR(50) NOT NULL
) ENGINE=INNODB;

CREATE TABLE UserToRole
(
    ID INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    UserID INT UNSIGNED NOT NULL,
    RoleID INT UNSIGNED NOT NULL,
    FOREIGN KEY (UserID) REFERENCES UserInfo(ID),
    FOREIGN KEY (RoleID) REFERENCES Role(ID)
) ENGINE=INNODB;

CREATE TABLE Permission
(
    ID INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    PermissionName VARCHAR(50) NOT NULL
) ENGINE=INNODB;

CREATE TABLE RoleToPermission
(
    ID INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    RoleID INT UNSIGNED NOT NULL,
    PermissionID INT UNSIGNED NOT NULL,
    FOREIGN KEY (RoleID) REFERENCES Role(ID),
    FOREIGN KEY (PermissionID) REFERENCES Permission(ID)
) ENGINE=INNODB;

CREATE TABLE Blog
(
    ID INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    BlogTitle VARCHAR(200) NOT NULL,
    BlogBody longtext NOT NULL,
    CreatedOn DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UpdatedOn DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    AuthorID INT UNSIGNED NOT NULL,
    FOREIGN KEY (AuthorID) REFERENCES UserInfo(ID)
) ENGINE=INNODB;

CREATE TABLE BlogToPermission
(
    ID INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    BlogID INT UNSIGNED NOT NULL,
    PermissionID INT UNSIGNED NOT NULL,
    FOREIGN KEY (BlogID) REFERENCES Blog(ID),
    FOREIGN KEY (PermissionID) REFERENCES Permission(ID)
) ENGINE=INNODB;

CREATE TABLE Tag
(
    ID INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    TagName VARCHAR(50)
) ENGINE=INNODB;

CREATE TABLE BlogToTag
(
    ID INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    BlogID INT UNSIGNED NOT NULL,
    TagID INT UNSIGNED NOT NULL,
    FOREIGN KEY (BlogID) REFERENCES Blog(ID),
    FOREIGN KEY (TagID) REFERENCES Tag(ID)
) ENGINE=INNODB;

CREATE TABLE Comment
(
    ID INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    BlogID INT UNSIGNED NOT NULL,
    CommentBody MEDIUMTEXT NOT NULL,
    UserID INT UNSIGNED NOT NULL,
    CreatedOn DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (BlogID) REFERENCES Blog(ID),
    FOREIGN KEY (UserID) REFERENCES UserInfo(ID)
) ENGINE=INNODB;