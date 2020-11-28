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
public class BlogCallbackHandlerTests {


	@Mock
	ResultSet mockResultSet;

	@InjectMocks
	BlogCallbackHandler mockBlogCallbackHandler;


	@Test
	public void processRow_VerifyBlogListUpdatedCorrectly() throws SQLException {


		Integer int1 = 0;
		Integer int2 = 1;

		when(mockResultSet.getInt("ID")).thenReturn(int1);

		mockBlogCallbackHandler.processRow(mockResultSet);

		assertNotNull(mockBlogCallbackHandler.getblogList());
		assertEquals(int1, mockBlogCallbackHandler.getblogList().get(0)); 
		assertNotEquals(int2, mockBlogCallbackHandler.getblogList().get(0)); 
	}

}
