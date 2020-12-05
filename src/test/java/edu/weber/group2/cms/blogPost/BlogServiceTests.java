package edu.weber.group2.cms.blogPost;

import edu.weber.group2.cms.blogPost.model.Blog;
import edu.weber.group2.cms.blogPost.model.Tag;
import edu.weber.group2.cms.blogPost.Repository.TagRepository;
import edu.weber.group2.cms.blogPost.Repository.BlogRepository;
import edu.weber.group2.cms.comments.CommentModel;

import java.util.List;
import java.util.ArrayList;

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
public class BlogServiceTests {



	@Mock
	BlogRepository mockBlogRepository;

	@Mock
	TagRepository mockTagRepository;


	@InjectMocks
	BlogService mockBlogService;



	@Test
	public void getAllTags_CanSuccessfullyReturnTagList() {
		
		List <Tag> tagList = new ArrayList<>();
		Tag tag1 = new Tag();
		tag1.setId(22);
		tag1.setName("Tag1 Name Test");

		Tag tag2 = new Tag();
		tag2.setId(39);
		tag2.setName("Tag2 Name Test");

		tagList.add(tag1);
		tagList.add(tag2);

		when(mockTagRepository.getAllTags()).thenReturn(tagList);
		
		assertEquals("Tag1 Name Test", mockBlogService.getAllTags().get(0).getName());
		assertNotEquals("tag1 Name Test", mockBlogService.getAllTags().get(0).getName());
		assertEquals(22, mockBlogService.getAllTags().get(0).getId());
		assertNotEquals(21, mockBlogService.getAllTags().get(0).getId());

		assertEquals("Tag2 Name Test", mockBlogService.getAllTags().get(1).getName());
		assertNotEquals("tag2 Name Test", mockBlogService.getAllTags().get(1).getName());
		assertEquals(39, mockBlogService.getAllTags().get(1).getId());
		assertNotEquals(38, mockBlogService.getAllTags().get(1).getId());
	}

	@Test
	public void getBlogByID_CanSuccessfullyReturnBlog() {
		
		Blog blog = new Blog();
		blog.setId(242);

		when(mockBlogRepository.getBlogByID(anyString())).thenReturn(blog);
		
		assertEquals(blog, mockBlogService.getBlogByID("242"));
		assertEquals(242, mockBlogService.getBlogByID("242").getId());
		assertNotEquals(243, mockBlogService.getBlogByID("242").getId());
	}

	@Test
	public void getComments_CanSuccessfullyReturnCommentList() {
		
		List <CommentModel> commentList = new ArrayList<>();
		
		CommentModel commentModel1 = new CommentModel();
		commentModel1.setCommentID(987);
		commentModel1.setCommentBody("This is a test for comment 1");

		CommentModel commentModel2 = new CommentModel();
		commentModel2.setCommentID(105);
		commentModel2.setCommentBody("This is a test for comment 2");

		commentList.add(commentModel1);
		commentList.add(commentModel2);

		when(mockBlogRepository.getComments(anyString())).thenReturn(commentList);

		assertEquals(commentList, mockBlogService.getComments(anyString()));

		assertEquals(987, mockBlogService.getComments(anyString()).get(0).getCommentID());
		assertNotEquals(988, mockBlogService.getComments(anyString()).get(0).getCommentID());
		assertEquals("This is a test for comment 1", mockBlogService.getComments(anyString()).get(0).getCommentBody());
		assertNotEquals("this is a test for comment 1", mockBlogService.getComments(anyString()).get(0).getCommentBody());

		assertEquals(105, mockBlogService.getComments(anyString()).get(1).getCommentID());
		assertNotEquals(104, mockBlogService.getComments(anyString()).get(1).getCommentID());
		assertEquals("This is a test for comment 2", mockBlogService.getComments(anyString()).get(1).getCommentBody());
		assertNotEquals("this is a test for comment 2", mockBlogService.getComments(anyString()).get(1).getCommentBody());

	}

	@Test
	public void deleteBlogById_CanSuccessfullyReturnBlog() {
		
		Blog blog = new Blog();
		blog.setId(711);

		when(mockBlogRepository.deleteBlogByID(anyString())).thenReturn(blog);

		assertEquals(blog, mockBlogService.deleteBlogById("711"));
		assertEquals(711, mockBlogService.deleteBlogById("711").getId());
		assertNotEquals(712, mockBlogService.deleteBlogById("711").getId());
	}

}
