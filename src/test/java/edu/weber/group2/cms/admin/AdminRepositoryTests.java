package edu.weber.group2.cms.admin;

import edu.weber.group2.cms.user.model.User;
import edu.weber.group2.cms.user.model.Role;
//import edu.weber.group2.cms.user.UserService;
//import edu.weber.group2.cms.user.repository.UserRepository;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminRepositoryTests {


	@Mock
	private AdminRepository mockAdminRepository;

	@Mock
	private AdminCallbackHandler mockAdminCallbackHandler;


	@Test
	public void getAllUsers_ReturnsNotNull() {
		List<User> users = new ArrayList<>();

		when(mockAdminRepository.getAllUsers()).thenReturn(users);
		
		assertNotNull(mockAdminRepository.getAllUsers());
	}

	@Test
	public void getAllRoles_ReturnsNotNull() {
		List<Role> roles = new ArrayList<>();

		when(mockAdminRepository.getAllRoles()).thenReturn(roles);
		
		assertNotNull(mockAdminRepository.getAllRoles());
	}

}
