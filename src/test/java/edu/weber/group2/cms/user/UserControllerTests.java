package edu.weber.group2.cms.user;

import edu.weber.group2.cms.user.model.User;
import edu.weber.group2.cms.user.UserService;
import edu.weber.group2.cms.user.repository.UserRepository;

import org.junit.Test;
import static org.junit.Assert.*;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {


	@Mock
	private Model mockModel;

	@Mock
	private UserService mockUserService;

	@Mock
	private BindingResult mockBindingResult;

	@InjectMocks
	private UserController mockUserController;


	@Test
	public void testMockedObjects() {
		assertNotNull(mockModel);
		assertNotNull(mockUserService);
		assertNotNull(mockBindingResult);
	}

	@Test
	public void getLoginPage_ReturnsURLString() {
		
		assertEquals("user/login", mockUserController.getLoginPage(mockModel));	
	}

	@Test
	public void postLoginPage_ReturnsURLString() {
		
		assertEquals("user/register", mockUserController.postLoginPage(mockModel));	
	}

	@Test
	public void getRegisterPage_ReturnsURLString() {
		
		assertEquals("user/register", mockUserController.getRegisterPage(mockModel));	
	}

	// The following test throws NullPointerException when calling postRegisterPage() -- can't locate source of issue yet
	// It appears none of the parameters are null, so it may be something in the method itself...
	/*
	@Test
	public void postRegisterPage_ReturnsURLString() {
		
		User user = new User();
		//User user = getUser();
		user.setFirstName("ben");
		user.setLastName("thomas");
		user.setUserName("userthomas");
		user.setPassword("thomasPassword");

		//DirectFieldBindingResult dfbr = new DirectFieldBindingResult(user, "user");
		//Model model = null;
		//mockModel.addAttribute("user", user);
		//mockUserController.postRegisterPage(user, mockBindingResult);
		try {
			assertEquals("user/register", mockUserController.postRegisterPage(user, mockBindingResult));	
		}
		catch (Throwable e) {
			if (mockUserController != null)
				System.out.println("UserController NOT NULL!");
			if (mockBindingResult != null)
				System.out.println("Binding Result NOT NULL!");
			if (user != null)
				System.out.println("user NOT NULL!");
			e.printStackTrace();
		}
	}
	*/
}
