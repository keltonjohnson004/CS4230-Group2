INSERT INTO UserInfo (FirstName, LastName, UserName, Password, Locked, Enabled, CredentialExpiredOn, ExpiredOn) values ("Private","User","privateUser","{bcrypt}$2a$10$xLuw8o4NS2SE3Hv1v/yuuul9S/caGrPx53P9XUoopH79ZP5KaTl1W",0,1,null,null);
INSERT INTO UserInfo (FirstName, LastName, UserName, Password, Locked, Enabled, CredentialExpiredOn, ExpiredOn) values ("Sensitive","User","sensitiveUser","{bcrypt}$2a$10$xLuw8o4NS2SE3Hv1v/yuuul9S/caGrPx53P9XUoopH79ZP5KaTl1W",0,1,null,null);
INSERT INTO UserInfo (FirstName, LastName, UserName, Password, Locked, Enabled, CredentialExpiredOn, ExpiredOn) values ("Private","NoComments","privateUserNoComment","{bcrypt}$2a$10$xLuw8o4NS2SE3Hv1v/yuuul9S/caGrPx53P9XUoopH79ZP5KaTl1W",0,1,null,null);
INSERT INTO UserInfo (FirstName, LastName, UserName, Password, Locked, Enabled, CredentialExpiredOn, ExpiredOn) values ("Sensitive","NoComments","sensitiveUserNoComment","{bcrypt}$2a$10$xLuw8o4NS2SE3Hv1v/yuuul9S/caGrPx53P9XUoopH79ZP5KaTl1W",0,1,null,null);
INSERT INTO UserInfo (FirstName, LastName, UserName, Password, Locked, Enabled, CredentialExpiredOn, ExpiredOn) values ("Admin","User","admin","{bcrypt}$2a$10$xLuw8o4NS2SE3Hv1v/yuuul9S/caGrPx53P9XUoopH79ZP5KaTl1W",0,1,null,null);


INSERT INTO Role (RoleName) values ("Private User");
INSERT INTO Role (RoleName) values ("Private User No Comment");
INSERT INTO Role (RoleName) values ("Sensitive User");
INSERT INTO Role (RoleName) values ("Sensitive User No Comment");
INSERT INTO Role (RoleName) values ("Admin");
INSERT INTO Role (RoleName) values ("Creator");

INSERT INTO PERMISSION (PermissionName) values ("Can Comment");
INSERT INTO PERMISSION (PermissionName) values ("Can Create Blogs");
INSERT INTO PERMISSION (PermissionName) values ("Can View Private Blogs");
INSERT INTO PERMISSION (PermissionName) values ("Can View Sensitive Blogs");
INSERT INTO PERMISSION (PermissionName) values ("Can Delete Blogs");
INSERT INTO PERMISSION (PermissionName) values ("Can Edit Users");
INSERT INTO PERMISSION (PermissionName) values ("Can Edit Blogs");

