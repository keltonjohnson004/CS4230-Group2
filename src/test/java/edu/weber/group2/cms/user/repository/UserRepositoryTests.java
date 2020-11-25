package edu.weber.group2.cms.user.repository;

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
public class UserRepositoryTests {


	@Mock
	private UserRepository mockUserRepository;


	@Test
	public void testMockedObjects() {
		assertNotNull(mockUserRepository);
	}


	@Test
	public void getUserByUserName_ReturnsNotNull() {
		User user = new User();
		
		when(mockUserRepository.getUserByUserName(anyString())).thenReturn(user);
		assertNotNull(mockUserRepository.getUserByUserName(anyString()));
	}

	@Test
	public void getUserByUserName_CanReturnCorrectUserName() {
		User user = new User();
		user.setUserName("Doug");

		when(mockUserRepository.getUserByUserName(anyString())).thenReturn(user);

		assertEquals("Doug", mockUserRepository.getUserByUserName(user.getUserName()).getUserName());
	}

	@Test
	public void getUserByID_ReturnsNotNull() {
		User user = new User();

		when(mockUserRepository.getUserByID(anyInt())).thenReturn(user);
		assertNotNull(mockUserRepository.getUserByID(anyInt()));
	}

	@Test
	public void getUserByID_CanReturnCorrectUserId() {
		User user = new User();
		user.setId(317);

		when(mockUserRepository.getUserByID(anyInt())).thenReturn(user);

		assertNotNull(mockUserRepository.getUserByID(anyInt()));
		assertEquals(317, mockUserRepository.getUserByID(user.getId()).getId());
	}


}
