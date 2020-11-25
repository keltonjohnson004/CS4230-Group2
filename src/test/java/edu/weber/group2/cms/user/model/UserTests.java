package edu.weber.group2.cms.user.model;

import java.util.HashMap;
import java.time.ZonedDateTime;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserTests {


	@Test
	public void setRoles_getRoles_ReturnsCorrectValues() {
		
		Role role = new Role();
		role.setName("Role Test 1");
		role.setId(5);

		HashMap hashMap = new HashMap();
		hashMap.put(372, role);

		User user = new User();
		user.setRoles(hashMap);

		assertTrue(user.getRoles().containsKey(372));
		assertEquals(role, user.getRoles().get(372));
		assertEquals(5, user.getRoles().get(372).getId());
		assertEquals("Role Test 1", user.getRoles().get(372).getName());
	}

	@Test
	public void setRoles_getRoles_IncorrectValuesTest() {
		
		Role role = new Role();
		role.setName("Role Test 2");
		role.setId(5);

		HashMap hashMap = new HashMap();
		hashMap.put(372, role);

		User user = new User();
		user.setRoles(hashMap);

		assertFalse(user.getRoles().containsKey(371));
		assertNotEquals(4, user.getRoles().get(372).getId());
		assertNotEquals("Role Test 3", user.getRoles().get(372).getName());
	}

	@Test
	public void getRoles_NullRolesReturnsEmptyHashMap() {
		
		User user = new User();

		assertTrue(user.getRoles().isEmpty());
	}
	
	@Test
	public void getRoles_NonEmptyRolesVerify() {
		
		Role role = new Role();
		role.setName("Role Test 3");
		role.setId(5);

		HashMap hashMap = new HashMap();
		hashMap.put(372, role);

		User user = new User();
		user.setRoles(hashMap);

		assertFalse(user.getRoles().isEmpty());
	}

	@Test
	public void getId_setId_ReturnsCorrectValues() {
		
		User user = new User();
		user.setId(4026);

		assertEquals(4026, user.getId());
		assertNotEquals(4023, user.getId());
	}
		
	@Test
	public void getFirstName_setFirstName_ReturnsCorrectValues() {
		
		User user = new User();
		user.setFirstName("Lisa");

		assertEquals("Lisa", user.getFirstName());
		assertNotEquals("Liza", user.getFirstName());
	}

	@Test
	public void getLastName_setLastName_ReturnsCorrectValues() {
		
		User user = new User();
		user.setLastName("Holland");

		assertEquals("Holland", user.getLastName());
		assertNotEquals("Holand", user.getLastName());
	}

	@Test
	public void getUserName_setUserName_ReturnsCorrectValues() {
		
		User user = new User();
		user.setUserName("TestUser");

		assertEquals("TestUser", user.getUserName());
		assertNotEquals("testUser", user.getUserName());
	}

	@Test
	public void getPassword_setPassword_ReturnsCorrectValues() {
		
		User user = new User();
		user.setPassword("Password");

		assertEquals("Password", user.getPassword());
		assertNotEquals("password", user.getPassword());
	}


	@Test
	public void isLocked_setLocked_SuccessfullyReturnsTrue() {
		
		User user = new User();
		user.setLocked(true);

		assertTrue(user.isLocked());
	}

	@Test
	public void isLocked_setLocked_SuccessfullyReturnsFalse() {
		
		User user = new User();
		user.setLocked(false);

		assertFalse(user.isLocked());
	}

	@Test
	public void isEnabled_setEnabled_SuccessfullyReturnsTrue() {
		
		User user = new User();
		user.setEnabled(true);

		assertTrue(user.isEnabled());
	}

	@Test
	public void isEnabled_setEnabled_SuccessfullyReturnsFalse() {
		
		User user = new User();
		user.setEnabled(false);

		assertFalse(user.isEnabled());
	}

	@Test
	public void getCredentialExpiredOn_setCredentialExpiredOn_ReturnsCorrectValues() {
		
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		User user = new User();
		user.setCredentialExpiredOn(zonedDateTime);

		assertEquals(zonedDateTime, user.getCredentialExpiredOn());
		assertNotEquals(zonedDateTime.minusDays(1), user.getCredentialExpiredOn());
	}

	@Test
	public void getExpiredOn_setExpiredOn_ReturnsCorrectValues() {
		
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		User user = new User();
		user.setExpiredOn(zonedDateTime);

		assertEquals(zonedDateTime, user.getExpiredOn());
		assertNotEquals(zonedDateTime.minusDays(1), user.getExpiredOn());
	}

	@Test
	public void getCreatedOn_setCreatedOn_ReturnsCorrectValues() {
		
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		User user = new User();
		user.setCreatedOn(zonedDateTime);

		assertEquals(zonedDateTime, user.getCreatedOn());
		assertNotEquals(zonedDateTime.minusDays(1), user.getCreatedOn());
	}

	@Test
	public void getModifiedOn_setModifiedOn_ReturnsCorrectValues() {
		
		ZonedDateTime zonedDateTime = ZonedDateTime.now();
		User user = new User();
		user.setModifiedOn(zonedDateTime);

		assertEquals(zonedDateTime, user.getModifiedOn());
		assertNotEquals(zonedDateTime.minusDays(1), user.getModifiedOn());
	}

	@Test
	public void getRole_setRole_ReturnsCorrectValues() {
		
		User user = new User();
		user.setRole("TestRole");

		assertEquals("TestRole", user.getRole());
	}
}
