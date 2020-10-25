package edu.weber.group2.cms.blogPost.Repository;

import edu.weber.group2.cms.blogPost.model.Blog;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class PermissionRepository {


    private String insertPermission = "INSERT INTO BlogToPermission (BlogID, PermissionID) VALUES (:BlogID, :PermissionID)";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PermissionRepository(NamedParameterJdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addPermission(Blog blog)
    {

        Map<String,Object> inputParameters = new HashMap();
        inputParameters.put("BlogID", blog.getId());


        if(blog.getPermission().equalsIgnoreCase("Private"))
        {
            inputParameters.put("PermissionID", 3);
            jdbcTemplate.update(insertPermission, inputParameters);
        }
        else if(blog.getPermission().equalsIgnoreCase("Sensitive"))
        {
            inputParameters.put("PermissionID", 4);
            jdbcTemplate.update(insertPermission, inputParameters);
        }
    }
}
