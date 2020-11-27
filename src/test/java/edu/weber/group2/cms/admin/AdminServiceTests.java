package edu.weber.group2.cms.admin;

import edu.weber.group2.cms.user.model.User;
import edu.weber.group2.cms.user.model.Role;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTests {


	@Mock
	private AdminService mockAdminService;


	@Test
	public void getAllUsers_ReturnsNotNull() {
		List<User> users = new ArrayList<>();

		when(mockAdminService.getAllUsers()).thenReturn(users);
		
		assertNotNull(mockAdminService.getAllUsers());
	}

	@Test
	public void getAllRoles_ReturnsNotNull() {
		List<Role> roles = new ArrayList<>();

		when(mockAdminService.getAllRoles()).thenReturn(roles);
		
		assertNotNull(mockAdminService.getAllRoles());
	}

}
