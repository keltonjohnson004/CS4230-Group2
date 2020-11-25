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
public class RoleTests {


	@Test
	public void setPermissions_getPermissions_ReturnsCorrectValues() {
		
		Permission permission = new Permission();
		permission.setId(23);
		permission.setName("Permission Test 1");

		HashMap hashMap = new HashMap();
		hashMap.put(522, permission);

		Role role = new Role();
		role.setPermissions(hashMap);

		assertTrue(role.getPermissions().containsKey(522));
		assertEquals(permission, role.getPermissions().get(522));
		assertEquals(23, role.getPermissions().get(522).getId());
		assertEquals("Permission Test 1", role.getPermissions().get(522).getName());
	}

	@Test
	public void setPermissions_getPermissions_IncorrectValuesAssertFalse() {

		Permission permission = new Permission();
		permission.setId(23);
		permission.setName("Permission Test 2");

		HashMap hashMap = new HashMap();
		hashMap.put(522, permission);

		Role role = new Role();
		role.setPermissions(hashMap);

		Permission permission2 = new Permission();

		assertFalse(role.getPermissions().containsKey(521));
		assertNotEquals(permission2, role.getPermissions().get(522));
		assertNotEquals(24, role.getPermissions().get(522).getId());
		assertNotEquals("Permission Test 3", role.getPermissions().get(522).getName());
		
	}

	@Test
	public void getPermissionById_ReturnsCorrectValues() {
		
		Permission permission = new Permission();
		permission.setId(57);
		permission.setName("Permission Test By Id");

		HashMap hashMap = new HashMap();
		hashMap.put(824, permission);

		Role role = new Role();
		role.setPermissions(hashMap);

		assertEquals(permission, role.getPermissionById(824));
		assertEquals(57, role.getPermissionById(824).getId());
		assertEquals("Permission Test By Id", role.getPermissionById(824).getName());
	}

	@Test
	public void getPermissionById_IncorrectValuesAsserFalse() {
		
		Permission permission = new Permission();
		permission.setId(57);
		permission.setName("Permission Test By Id");

		HashMap hashMap = new HashMap();
		hashMap.put(824, permission);

		Role role = new Role();
		role.setPermissions(hashMap);

		Permission permission2 = new Permission();

		assertNotEquals(permission2, role.getPermissionById(824));
		assertNotEquals(58, role.getPermissionById(824).getId());
		assertNotEquals("Permission Test By id", role.getPermissionById(824).getName());
	}

	@Test
	public void getPermissionById_NullPermissionReturnsNull() {

		Role role = new Role();

		assertNull(role.getPermissionById(3));
	}

	@Test
	public void getId_setId_ReturnsCorrectValues() {
		
		Role role = new Role();
		role.setId(13);

		assertEquals(13, role.getId());
	}

	@Test
	public void getName_setName_ReturnsCorrectValues() {
		
		Role role = new Role();
		role.setName("Ralph");

		assertEquals("Ralph", role.getName());
	}

}
