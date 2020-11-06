package edu.weber.group2.cms.admin;

import edu.weber.group2.cms.user.model.Role;
import edu.weber.group2.cms.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private AdminRepository adminRepository;


    public AdminService(AdminRepository _adminRepository)
    {
        adminRepository = _adminRepository;
    }
    public List<User> getAllUsers()
    {
        return adminRepository.getAllUsers();
    }
    public List<Role> getAllRoles()
    {
        return adminRepository.getAllRoles();
    }
    public void updateUser(User user)
    {
        adminRepository.updateUser(user);
    }


}
