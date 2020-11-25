package edu.weber.group2.cms.user.repository;

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
public class UserRowCallbackHandlerTests {

	@Mock
	ResultSet mockResultSet;

	@InjectMocks
	UserRowCallbackHandler mockUserRowCallbackHandler;


	@Test
	public void processRow_VerifyUserFieldsAreUpdatedCorrectly() throws SQLException {

		Timestamp ts1 = null;
		Timestamp ts2 = null;
		ts1.valueOf("2020-10-31 23:11:11");
		ts2.valueOf("2020-11-11 11:11:11");

		when(mockResultSet.getInt("UserID")).thenReturn(22);
		when(mockResultSet.getInt("RoleID")).thenReturn(37);
		when(mockResultSet.getInt("PermissionID")).thenReturn(82);

		when(mockResultSet.getString("FirstName")).thenReturn("Morton");
		when(mockResultSet.getString("LastName")).thenReturn("Jensen");
		when(mockResultSet.getString("UserName")).thenReturn("UserMorton");
		when(mockResultSet.getString("Password")).thenReturn("tiger123");

		when(mockResultSet.getBoolean("Locked")).thenReturn(false);
		when(mockResultSet.getBoolean("Enabled")).thenReturn(false);

		when(mockResultSet.getTimestamp("ExpiredOn")).thenReturn(ts1);
		when(mockResultSet.getTimestamp("CreatedOn")).thenReturn(ts2);

		mockUserRowCallbackHandler.processRow(mockResultSet);

		assertNotNull(mockUserRowCallbackHandler.getUser());
		assertEquals("Morton", mockUserRowCallbackHandler.getUser().getFirstName()); 
		assertEquals("Jensen", mockUserRowCallbackHandler.getUser().getLastName()); 
		assertEquals("UserMorton", mockUserRowCallbackHandler.getUser().getUserName()); 
		assertEquals("tiger123", mockUserRowCallbackHandler.getUser().getPassword()); 
		assertEquals(22, mockUserRowCallbackHandler.getUser().getId()); 
	}

}
