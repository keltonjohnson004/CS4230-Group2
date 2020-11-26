package edu.weber.group2.cms.user.model;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PermissionTests {


	@Test
	public void getId_setId_ReturnsCorrectValues() {
		
		Permission permission = new Permission();
		permission.setId(242);

		assertEquals(242, permission.getId());
	}

	@Test
	public void getName_setName_ReturnsCorrectValues() {
		
		Permission permission = new Permission();
		permission.setName("Wendy");

		assertEquals("Wendy", permission.getName());
	}

}
