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
public class BlogRepositoryTests {


	@Mock
	NamedParameterJdbcTemplate mockJdbcTemplate;

	@Mock
	ResultSet mockResultSet;

	@Mock
	BlogCallbackHandler mockBlogCallbackHandler;

	@Mock
	ReadBlogCallbackHandler mockReadBlogCallbackHandler;

	@InjectMocks
	BlogRepository mockBlogRepository;


	@Test
	public void addBlog_ReturnsZero() {
		
		Blog blog = new Blog();

		assertEquals(0, mockBlogRepository.addBlog(blog)); 
	}

	@Test
	public void getBlogByID_CanReturnNonNullBlog() {
		
		assertNotNull(mockBlogRepository.getBlogByID("Blog String Id")); 
	}

	@Test
	public void getComments_CanReturnNonNullCommentsList() {
		
		assertNotNull(mockBlogRepository.getComments("Comment String Id")); 
	}

	@Test
	public void deleteBlogByID_CanReturnNonNullBlog() {
		
		assertNotNull(mockBlogRepository.getBlogByID("Blog String Id")); 
	}

}
