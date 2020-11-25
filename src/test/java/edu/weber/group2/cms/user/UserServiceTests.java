package edu.weber.group2.cms.user;

import edu.weber.group2.cms.user.model.User;
import edu.weber.group2.cms.user.UserService;
import edu.weber.group2.cms.user.repository.UserRepository;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {


	@Mock
	private UserService mockUserService;

	@Mock
	private UserRepository mockUserRepository;


	@Test
	public void testMockedObjects() {
		assertNotNull(mockUserService);
		assertNotNull(mockUserRepository);
	}

	@Test
	public void getUserRepository_ReturnsNotNull() {
		when(mockUserService.getUserRepository()).thenReturn(mockUserRepository);
		assertNotNull(mockUserService.getUserRepository());
	}

	@Test
	public void loadUserByUsername_ReturnsNotNull() {
		User user = new User();

		when(mockUserService.loadUserByUsername(anyString())).thenReturn(user);

		assertNotNull(mockUserService.loadUserByUsername(anyString()));
		assertEquals(user, mockUserService.loadUserByUsername(anyString()));
	}

	/* Since loadUserByUsername returns UserDetails (not User), need to figure out another way...
	@Test
	public void loadUserByUsername_CanReturnAcutalUserName() {
		User user = new User();
		user.setUserName("Billy");

		when(mockUserService.loadUserByUsername(user.getUserName())).thenReturn(user);
		assertEquals("Billy", mockUserService.loadUserByUsername(user.getUserName()).getUserName());
	}
	*/

	@Test
	public void getUserByID_ReturnsNotNull() {
		User user = new User();

		when(mockUserService.getUserByID(anyInt())).thenReturn(user);

		assertNotNull(mockUserService.getUserByID(anyInt()));
		assertEquals(user, mockUserService.getUserByID(anyInt()));
	}

	@Test 
	public void getUserByID_CanReturnActualUserId() {
		User user = new User();
		user.setId(457);

		when(mockUserService.getUserByID(anyInt())).thenReturn(user);
		assertEquals(457, mockUserService.getUserByID(anyInt()).getId());

	}

}
