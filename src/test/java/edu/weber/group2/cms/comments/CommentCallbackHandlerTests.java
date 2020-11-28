package edu.weber.group2.cms.comments;

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
public class CommentCallbackHandlerTests {

	@Mock
	ResultSet mockResultSet;

	@InjectMocks
	CommentCallbackHandler mockCommentCallbackHandler;


	@Test
	public void processRow_VerifyRoleFieldsAreUpdatedCorrectly() throws SQLException {


		when(mockResultSet.getInt("ID")).thenReturn(392);
		when(mockResultSet.getInt("BlogID")).thenReturn(81);
		when(mockResultSet.getInt("UserID")).thenReturn(2929);
		when(mockResultSet.getString("CommentBody")).thenReturn("Comment Body Test");

		mockCommentCallbackHandler.processRow(mockResultSet);

		assertNotNull(mockCommentCallbackHandler.getComments());
		assertEquals("Comment Body Test", mockCommentCallbackHandler.getComments().get(0).getCommentBody()); 

		assertEquals(392, mockCommentCallbackHandler.getComments().get(0).getCommentID()); 
		assertNotEquals(391, mockCommentCallbackHandler.getComments().get(0).getCommentID()); 
		
		assertEquals(81, mockCommentCallbackHandler.getComments().get(0).getBlogID()); 
		assertNotEquals(82, mockCommentCallbackHandler.getComments().get(0).getBlogID()); 

		assertEquals(2929, mockCommentCallbackHandler.getComments().get(0).getCommentorID()); 
		assertNotEquals(2928, mockCommentCallbackHandler.getComments().get(0).getCommentorID()); 
	}

}
