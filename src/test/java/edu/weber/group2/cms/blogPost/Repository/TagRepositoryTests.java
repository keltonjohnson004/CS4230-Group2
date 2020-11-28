package edu.weber.group2.cms.blogPost.Repository;

import edu.weber.group2.cms.blogPost.model.Blog;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TagRepositoryTests {


	@Mock
	NamedParameterJdbcTemplate mockJdbcTemplate;

	@Mock
	ResultSet mockResultSet;

	@Mock
	TagCallBackHandler mockTagCallBackHandler;

	@InjectMocks
	TagRepository mockTagRepository;


	@Test
	public void getComments_CanReturnNonNullTagList() {
		
		assertNotNull(mockTagRepository.getAllTags()); 
	}

}
