package edu.weber.group2.cms.user.model;

import java.util.HashMap;
import java.util.Map;

public class Role {

    private int id;
    private String name;
    private Map<Integer, Permission> permissions;

    public Permission getPermissionById(int id)
    {
        if(permissions != null)
        {
            return permissions.get(id);
        }
        return null;
    }

    public Map<Integer, Permission> getPermissions()
    {
        if(permissions == null)
        {
            permissions = new HashMap<>();
        }
        return permissions;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPermissions(Map<Integer, Permission> permissions) {
        this.permissions = permissions;
    }
}
