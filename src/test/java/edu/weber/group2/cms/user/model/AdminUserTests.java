package edu.weber.group2.cms.user.model;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminUserTests {


	@Test
	public void getId_setId_ReturnsCorrectValues() {
		
		AdminUser adminUser = new AdminUser();
		adminUser.setId(678);

		assertEquals(678, adminUser.getId());
	}

	@Test
	public void getFirstName_setFirstName_ReturnsCorrectValues() {
		
		AdminUser adminUser = new AdminUser();
		adminUser.setFirstName("Samantha");

		assertEquals("Samantha", adminUser.getFirstName());
	}

	@Test
	public void getLastName_setLastName_ReturnsCorrectValues() {
		
		AdminUser adminUser = new AdminUser();
		adminUser.setLastName("Brooks");

		assertEquals("Brooks", adminUser.getLastName());
	}

	@Test
	public void getUserName_setUserName_ReturnsCorrectValues() {
		
		AdminUser adminUser = new AdminUser();
		adminUser.setUserName("UserSamantha");

		assertEquals("UserSamantha", adminUser.getUserName());
	}

}
