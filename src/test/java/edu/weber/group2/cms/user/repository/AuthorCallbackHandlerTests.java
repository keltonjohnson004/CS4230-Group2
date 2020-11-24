package edu.weber.group2.cms.user.repository;

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
public class AuthorCallbackHandlerTests {

	@Mock
	ResultSet mockResultSet;

	@InjectMocks
	AuthorCallbackHandler mockAuthorCallbackHandler;


	@Test
	public void processRow_VerifyUserFieldsAreUpdatedCorrectly() throws SQLException {

		when(mockResultSet.getInt("ID")).thenReturn(99);

		when(mockResultSet.getString("FirstName")).thenReturn("Jacob");
		when(mockResultSet.getString("LastName")).thenReturn("Marley");
		when(mockResultSet.getString("UserName")).thenReturn("UserJacob");

		mockAuthorCallbackHandler.processRow(mockResultSet);

		assertNotNull(mockAuthorCallbackHandler.getAuthor());
		assertEquals("Jacob", mockAuthorCallbackHandler.getAuthor().getFirstName()); 
		assertEquals("Marley", mockAuthorCallbackHandler.getAuthor().getLastName()); 
		assertEquals("UserJacob", mockAuthorCallbackHandler.getAuthor().getUserName()); 
		assertEquals(99, mockAuthorCallbackHandler.getAuthor().getId()); 
	}

}
