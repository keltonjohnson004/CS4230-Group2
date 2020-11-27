package edu.weber.group2.cms.admin;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoleCallbackHandlerTests {

	@Mock
	ResultSet mockResultSet;

	@InjectMocks
	RoleCallbackHandler mockRoleCallbackHandler;


	@Test
	public void processRow_VerifyRoleFieldsAreUpdatedCorrectly() throws SQLException {


		when(mockResultSet.getInt("ID")).thenReturn(392);
		when(mockResultSet.getString("RoleName")).thenReturn("Some Role");

		mockRoleCallbackHandler.processRow(mockResultSet);

		assertNotNull(mockRoleCallbackHandler.getRoles());
		assertEquals("Some Role", mockRoleCallbackHandler.getRoles().get(0).getName()); 
		assertEquals(392, mockRoleCallbackHandler.getRoles().get(0).getId()); 
	}

}
