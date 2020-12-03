package edu.weber.group2.cms.user;

import edu.weber.group2.cms.user.model.User;
import edu.weber.group2.cms.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserService implements UserDetailsService
{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void addUser(User user)
    {
        userRepository.addUser(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUserName(username);

        return user;
    }

    public User getUserByID(int id)
    {
        return userRepository.getUserByID(id);
    }

}
