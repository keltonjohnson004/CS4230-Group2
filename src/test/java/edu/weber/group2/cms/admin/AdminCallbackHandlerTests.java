package edu.weber.group2.cms.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AdminCallbackHandlerTests {

	@Mock
	ResultSet mockResultSet;

	@InjectMocks
	AdminCallbackHandler mockAdminCallbackHandler;


	@Test
	public void processRow_VerifyUserFieldsAreUpdatedCorrectly() throws SQLException {


		when(mockResultSet.getInt("ID")).thenReturn(9215);

		when(mockResultSet.getString("FirstName")).thenReturn("Morton");
		when(mockResultSet.getString("LastName")).thenReturn("Jensen");
		when(mockResultSet.getString("UserName")).thenReturn("UserMorton");


		mockAdminCallbackHandler.processRow(mockResultSet);

		assertNotNull(mockAdminCallbackHandler.getUserList());
		assertEquals("Morton", mockAdminCallbackHandler.getUserList().get(0).getFirstName()); 
		assertEquals("Jensen", mockAdminCallbackHandler.getUserList().get(0).getLastName()); 
		assertEquals("UserMorton", mockAdminCallbackHandler.getUserList().get(0).getUserName()); 
	}

}
