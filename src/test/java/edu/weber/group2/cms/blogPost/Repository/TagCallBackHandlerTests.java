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
public class TagCallBackHandlerTests {


	@Mock
	ResultSet mockResultSet;

	@InjectMocks
	TagCallBackHandler mockTagCallBackHandler;


	@Test
	public void processRow_VerifyTagListUpdatedCorrectly() throws SQLException {


		when(mockResultSet.getInt("ID")).thenReturn(611);
		when(mockResultSet.getString("TagName")).thenReturn("Tag Name Test");

		mockTagCallBackHandler.processRow(mockResultSet);

		assertNotNull(mockTagCallBackHandler.getTagList());

		assertEquals(611, mockTagCallBackHandler.getTagList().get(0).getId()); 
		assertNotEquals(612, mockTagCallBackHandler.getTagList().get(0).getId()); 

		assertEquals("Tag Name Test", mockTagCallBackHandler.getTagList().get(0).getName()); 
		assertNotEquals("tag Name Test", mockTagCallBackHandler.getTagList().get(0).getName()); 
	}

}
