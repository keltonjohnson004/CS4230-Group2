package edu.weber.group2.cms.mainPage;

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
public class MainPageCallbackHandlerTests {

	@Mock
	ResultSet mockResultSet;

	@InjectMocks
	MainPageCallbackHandler mockMainPageCallbackHandler;


	@Test
	public void processRow_VerifyBlogFieldsAreUpdatedCorrectly() throws SQLException {

		Integer myint = 55;

		when(mockResultSet.getInt("ID")).thenReturn(22);
		when(mockResultSet.getInt("p.PermissionID")).thenReturn(37);
		when(mockResultSet.getInt("t.TagID")).thenReturn(55);

		when(mockResultSet.getString("BlogTitle")).thenReturn("Blog Title Test");
		when(mockResultSet.getString("b.BlogBody")).thenReturn("Blog Body Test");
		when(mockResultSet.getString("u.FirstName")).thenReturn("Author First Name Test");
		when(mockResultSet.getString("u.LastName")).thenReturn("Author Last Name Test");

		mockMainPageCallbackHandler.processRow(mockResultSet);

		assertNotNull(mockMainPageCallbackHandler.getblogList());
		assertEquals("Blog Title Test", mockMainPageCallbackHandler.getblogList().get(0).getBlogName()); 
		assertEquals("Blog Body Test", mockMainPageCallbackHandler.getblogList().get(0).getBlogBody()); 
		assertEquals("Author First Name Test", mockMainPageCallbackHandler.getblogList().get(0).getAuthorFirstName()); 
		assertEquals("Author Last Name Test", mockMainPageCallbackHandler.getblogList().get(0).getAuthorLastName()); 
		assertEquals(22, mockMainPageCallbackHandler.getblogList().get(0).getBlogID()); 
		assertEquals(37, mockMainPageCallbackHandler.getblogList().get(0).getPermissionID()); 
		assertEquals(myint, mockMainPageCallbackHandler.getblogList().get(0).getTags().get(0)); 
		
	}

}
