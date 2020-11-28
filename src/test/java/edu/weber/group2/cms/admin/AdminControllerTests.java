package edu.weber.group2.cms.admin;

import edu.weber.group2.cms.user.model.User;
import edu.weber.group2.cms.user.model.Role;
import edu.weber.group2.cms.user.UserService;

import java.util.List;
import java.util.ArrayList;

import org.springframework.ui.Model;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTests {


	@Mock
	private Model mockModel;

	@Mock
	private AdminService mockAdminService;

	@Mock
	private UserService mockUserService;

	@Mock
	private BindingResult mockBindingResult;

	@InjectMocks
	private AdminController mockAdminController;


	@Test
	public void GetMainPage_ReturnsCorrectModelViewAttribute() {

		User user = new User();
		user.setId(35);
		user.setFirstName("Wally");
		
		List<User> userList1 = new ArrayList<>();
		userList1.add(user);

		List<User> userList2 = new ArrayList<>();

		when(mockAdminService.getAllUsers()).thenReturn(userList1);

		assertNotNull(mockAdminController.GetMainPage(mockModel));
		assertEquals(userList1, mockAdminController.GetMainPage(mockModel).getModelMap().getAttribute("userList"));
		assertNotEquals(userList2, mockAdminController.GetMainPage(mockModel).getModelMap().getAttribute("userList"));
	}

	@Test
	public void getUserEditPage_ReturnsCorrectModelViewAttributes() {

		String userName = "Sammy";

		UserDetails user = new User();

		Role role = new Role();
		role.setName("Test Role");
		
		List<Role> roleList1 = new ArrayList<>();
		roleList1.add(role);

		List<Role> roleList2 = new ArrayList<>();

		when(mockUserService.loadUserByUsername(userName)).thenReturn(user);
		when(mockAdminService.getAllRoles()).thenReturn(roleList1);


		assertNotNull(mockAdminController.getUserEditPage(userName));
		assertEquals(user, mockAdminController.getUserEditPage(userName).getModelMap().getAttribute("user"));
		assertEquals(roleList1, mockAdminController.getUserEditPage(userName).getModelMap().getAttribute("roleList"));
		assertNotEquals(roleList1, mockAdminController.GetMainPage(mockModel).getModelMap().getAttribute("roleList"));
	}


	@Test
	public void postUserEditPage_ReturnsCorrectModelViewAttribute() {

		Role role = new Role();
		role.setName("Roly Poly");
		role.setId(72);

		User user1 = new User();
		user1.setRole("Roly Poly");

		User user2 = new User();
		

		assertNotNull(mockAdminController.postUserEditPage(user1, mockBindingResult));
		assertNull(mockAdminController.postUserEditPage(user2, mockBindingResult));
		assertEquals("redirect:/admin", mockAdminController.postUserEditPage(user1, mockBindingResult).getViewName());
	}

}
