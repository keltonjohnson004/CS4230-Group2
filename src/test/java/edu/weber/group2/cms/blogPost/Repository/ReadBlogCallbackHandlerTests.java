package edu.weber.group2.cms.blogPost.Repository;

import java.util.List;

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
public class ReadBlogCallbackHandlerTests {


	@Mock
	ResultSet mockResultSet;

	@InjectMocks
	ReadBlogCallbackHandler mockReadBlogCallbackHandler;


	@Test
	public void processRow_VerifyBlogFieldsAreUpdatedCorrectly() throws SQLException {


		when(mockResultSet.getInt("ID")).thenReturn(32);
		when(mockResultSet.getInt("AuthorID")).thenReturn(427);

		when(mockResultSet.getString("BlogTitle")).thenReturn("Blog Title Test");
		when(mockResultSet.getString("BlogBody")).thenReturn("Blog Body Test");

		mockReadBlogCallbackHandler.processRow(mockResultSet);

		assertNotNull(mockReadBlogCallbackHandler.getBlog());

		assertEquals(32, mockReadBlogCallbackHandler.getBlog().getId()); 
		assertNotEquals(33, mockReadBlogCallbackHandler.getBlog().getId()); 
		
		assertEquals(427, mockReadBlogCallbackHandler.getBlog().getAuthorID()); 
		assertNotEquals(428, mockReadBlogCallbackHandler.getBlog().getAuthorID()); 

		assertEquals("Blog Title Test", mockReadBlogCallbackHandler.getBlog().getBlogName()); 
		assertNotEquals("blog Title Test", mockReadBlogCallbackHandler.getBlog().getBlogName()); 

		assertEquals("Blog Body Test", mockReadBlogCallbackHandler.getBlog().getBlogBody()); 
		assertNotEquals("blog Body Test", mockReadBlogCallbackHandler.getBlog().getBlogBody()); 
	}

}
