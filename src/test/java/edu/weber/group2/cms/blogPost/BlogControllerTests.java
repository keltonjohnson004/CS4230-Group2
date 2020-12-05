package edu.weber.group2.cms.blogPost;

import edu.weber.group2.cms.blogPost.model.Blog;
import edu.weber.group2.cms.blogPost.model.Tag;

import java.util.List;
import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.junit.MockitoJUnitRunner;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BlogControllerTests {



	@Mock
	Model mockModel;

	@Mock
	Principal mockPrincipal;

	@Mock
	BindingResult mockBindingResult;

	@Mock
	BlogService mockBlogService;

	@InjectMocks
	BlogController mockBlogController;

	
	@Test
	public void GetBlogPost_ReturnsCorrectModelViewAttributes() {
		
		Tag tag1 = new Tag();
		tag1.setId(34);
		tag1.setName("Tag1 Name");

		Tag tag2 = new Tag();
		tag2.setId(84);
		tag2.setName("Tag2 Name");

		List<Tag> tagList1 = new ArrayList<>();
		tagList1.add(tag1);
		tagList1.add(tag2);

		List<Tag> tagList2 = new ArrayList<>();

		when(mockBlogService.getAllTags()).thenReturn(tagList1);

		assertEquals(tagList1, mockBlogController.GetBlogPost(mockModel).getModelMap().getAttribute("tagList"));
		assertNotEquals(tagList2, mockBlogController.GetBlogPost(mockModel).getModelMap().getAttribute("tagList"));
	}

	@Test
	public void PostBlogPost_ReturnsCorrectModelViewName() {
		
		Blog blog = new Blog();

		assertEquals("redirect:../", mockBlogController.PostBlogPost(blog, mockBindingResult, mockPrincipal).getViewName());
	}

	@Test
	public void GetBlog_ReturnsCorrectModelViewName() {
		
		assertEquals("blog/blog", mockBlogController.GetBlog(mockModel).getViewName());
	}

	@Test
	public void GetCreatePost_ReturnsCorrectModelViewName() {
		
		assertEquals("blog/create_post", mockBlogController.GetCreatePost(mockModel).getViewName());
	}

	@Test
	public void GetEditBlog_ReturnsCorrectModelViewAttribute() {
		
		Blog blog1 = new Blog();
		blog1.setId(98);

		Blog blog2 = new Blog();

		when(mockBlogService.getBlogByID(anyString())).thenReturn(blog1);

		assertNotNull(mockBlogController.GetEditBlog("98", mockPrincipal));
		assertEquals(blog1, mockBlogController.GetEditBlog("98", mockPrincipal).getModelMap().getAttribute("blog"));
		assertNotEquals(blog2, mockBlogController.GetEditBlog("98", mockPrincipal).getModelMap().getAttribute("blog"));
	}

	@Test
	public void GetEditBlog_Overloaded_ReturnsCorrectRedirectString() {
		
		Blog blog = new Blog();

		assertEquals("redirect:/", mockBlogController.GetEditBlog(blog, mockBindingResult));
		
	}

	@Test
	public void Getdeleteblog_ReturnsCorrectRedirectString() {
		

		assertEquals("redirect:/", mockBlogController.GetdeleteBlog("BlogID", mockPrincipal));
		
	}



}
